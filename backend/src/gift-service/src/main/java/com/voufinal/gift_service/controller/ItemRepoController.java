package com.voufinal.gift_service.controller;

import com.voufinal.gift_service.common.ApiResponse;
import com.voufinal.gift_service.common.NotFoundResponse;
import com.voufinal.gift_service.common.SuccessResponse;
import org.springframework.http.HttpStatus;
import com.voufinal.gift_service.dto.AwardDto;
import com.voufinal.gift_service.exception.InternalServerError;
import com.voufinal.gift_service.model.Item;
import com.voufinal.gift_service.model.ItemRepo;
import com.voufinal.gift_service.service.ItemRepoService;
import com.voufinal.gift_service.service.ItemService;
import com.voufinal.gift_service.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item-repos")
@CrossOrigin
public class ItemRepoController {
    @Autowired
    ItemRepoService itemRepoService;
    @Autowired
    ItemService itemService;
    @Autowired
    VoucherService voucherService;

    @GetMapping("/{id_user}")
    public ResponseEntity<?> getRewardsByIdUser(@PathVariable Long id_user) {
        try {
            List<ItemRepo> itemRepoList = itemRepoService.getItemRepoListByIdUser(id_user);
            List<AwardDto> rewards = new ArrayList<>();

            for (ItemRepo itemRepo : itemRepoList) {
                Item item = itemService.getItemById(itemRepo.getIdItem());
                if (item != null) {
                    rewards.add(new AwardDto(item, itemRepo));
                }
            }

            return ResponseEntity.ok(rewards);
        } catch (Exception e) {
            System.out.println("Error in getRewardsByIdUser: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("")
    public ResponseEntity<ApiResponse> getItemRepo(@RequestParam(value = "id_user") Long id_user) {
        try {
            List<ItemRepo> itemRepoList = itemRepoService.getItemRepoListByIdUser(id_user);
            List<AwardDto> rewards = new ArrayList<>();

            if (itemRepoList.toArray().length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("User's inventory not found"));
            }

            for (ItemRepo itemRepo : itemRepoList) {
                Item item = itemService.getItemById(itemRepo.getIdItem());
                if (item != null) {
                    rewards.add(new AwardDto(item, itemRepo));
                }
            }


            return ResponseEntity.ok(new SuccessResponse("Successfully accessed the inventory!", HttpStatus.OK, rewards));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("System error while trying to load the player's inventory!"));
        }
    }

    @PutMapping("/{id_item_repo}")
    public ResponseEntity<AwardDto> incrementAmountByIdItemRepo(@PathVariable Long id_item_repo, @RequestParam(value = "amount", required = false) Long updatedAmount) {
        try {
            int recordNumber;
            if (updatedAmount != null) {
                recordNumber = itemRepoService.incrementAmountCoinByIdItemRepo(id_item_repo, updatedAmount);
            } else {
                recordNumber = itemRepoService.incrementAmountByIdItemRepo(id_item_repo);
            }
            if (recordNumber != 1) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            ItemRepo updatedItemRepo = itemRepoService.getItemRepoByIdItemRepo(id_item_repo);
            if (updatedItemRepo == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            Item itemInfo = itemService.getItemById(updatedItemRepo.getIdItem());
            return ResponseEntity.ok(new AwardDto(itemInfo, updatedItemRepo));
        } catch (Exception e) {
            System.out.println("Error in incrementAmountByIdItemRepo controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id_user}")
    public ResponseEntity<?> createItemRepo(@PathVariable Long id_user) {
        List<Item> itemList = itemService.getAllItems();
        try {
            List<ItemRepo> itemRepoList = itemRepoService.createItemRepos(id_user, itemList);
            return ResponseEntity.ok(itemRepoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
