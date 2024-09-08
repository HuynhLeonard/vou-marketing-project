package com.voufinal.gift_service.controller;

import com.voufinal.gift_service.common.*;
import com.voufinal.gift_service.dto.*;
import com.voufinal.gift_service.entity.request.RewardVoucherRequest;
import org.springframework.http.HttpStatus;
import com.voufinal.gift_service.entity.UserVoucher;
import com.voufinal.gift_service.exception.InternalServerError;
import com.voufinal.gift_service.exception.NotFoundException;
import com.voufinal.gift_service.model.Voucher;
import com.voufinal.gift_service.repository.ItemRepository;
import com.voufinal.gift_service.repository.VoucherRepository;
import com.voufinal.gift_service.service.CloudinaryService;
import com.voufinal.gift_service.service.VoucherRepoService;
import com.voufinal.gift_service.service.VoucherService;
import com.voufinal.gift_service.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.voufinal.gift_service.entity.request.CreateVoucherRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private VoucherRepoService voucherRepoService;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getVouchers(@RequestParam("type") String type) {
        if (!Arrays.asList("online", "offline").contains(type)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequest("Voucher type does not exist"));
        }
        try {
            List<Voucher> vouchers = voucherService.getListOnlineOrOfflineVoucher(type);
            if (vouchers == null) {
                return ResponseEntity.internalServerError().body(new InternalServerError("System error while retrieving the list of " + type + " vouchers"));
            }
            if (vouchers.toArray().length == 0) {
                return ResponseEntity.ok(new SuccessResponse("Currently, there are no " + type + " vouchers", HttpStatus.OK, null));
            }
            return ResponseEntity.ok(new SuccessResponse("Successfully accessed the list of " + type + " vouchers", HttpStatus.OK, vouchers));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("System error while accessing the list of " + type + " vouchers"));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse>  createVoucher(@RequestBody InventoryDto inventoryDTO) {
        try {
            CreateVoucherRequest request;
            if (inventoryDTO.getGameType().equals("shake-game")) {
                request = new CreateVoucherRequest(
                        inventoryDTO.getVoucher_code(),
                        null,
                        inventoryDTO.getVoucher_name(),
                        null,
                        inventoryDTO.getExpiration_date(),
                        inventoryDTO.getVoucher_price(),
                        inventoryDTO.getVoucher_description(),
                        inventoryDTO.getVoucher_type(),
                        inventoryDTO.getItems(),
                        inventoryDTO.getAim_coin(),
                        inventoryDTO.getEvent_id()
                );
            }
            else {
                request = new CreateVoucherRequest(
                        inventoryDTO.getVoucher_code(),
                        null,
                        inventoryDTO.getVoucher_name(),
                        null,
                        inventoryDTO.getExpiration_date(),
                        inventoryDTO.getVoucher_price(),
                        inventoryDTO.getVoucher_description(),
                        inventoryDTO.getVoucher_type(),
                        inventoryDTO.getEvent_id()
                );
            }
            int result = voucherService.createVoucher(request);
            ApiResponse response = new CreatedResponse("Voucher created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse> getVoucher(@PathVariable("code") String code) {
        if (code == null) {
            return ResponseEntity.badRequest().body(new BadRequest("Invalid voucher code"));
        }
        try {
            Voucher voucher = voucherService.findVoucherByCode(code);
            if (voucher == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("No voucher found with the corresponding code"));
            }
            return ResponseEntity.ok(new SuccessResponse("Successfully accessed voucher information!" , HttpStatus.OK, voucher));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("System error while trying to access voucher information!"));
        }
    }

    @PutMapping("/info")
    public ResponseEntity<ApiResponse> updateVoucher(@RequestBody InventoryDto inventoryDTO, @RequestParam String code) {
        try {
            CreateVoucherRequest request;
            if (inventoryDTO.getGameType().equals("shake-game")) {
                request = new CreateVoucherRequest(
                        inventoryDTO.getVoucher_code(),
                        null,
                        inventoryDTO.getVoucher_name(),
                        null,
                        inventoryDTO.getExpiration_date(),
                        inventoryDTO.getVoucher_price(),
                        inventoryDTO.getVoucher_description(),
                        inventoryDTO.getVoucher_type(),
                        inventoryDTO.getItems(),
                        inventoryDTO.getAim_coin(),
                        inventoryDTO.getEvent_id()
                );
            }
            else {
                request = new CreateVoucherRequest(
                        inventoryDTO.getVoucher_code(),
                        null,
                        inventoryDTO.getVoucher_name(),
                        null,
                        inventoryDTO.getExpiration_date(),
                        inventoryDTO.getVoucher_price(),
                        inventoryDTO.getVoucher_description(),
                        inventoryDTO.getVoucher_type(),
                        inventoryDTO.getEvent_id()
                );
            }

            Integer result = voucherService.updateVoucherByCode(code, request);

            ApiResponse response = new SuccessResponse("Voucher updated successfully", 200);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Server error while updating voucher"));
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse> deleteVoucherById(@PathVariable String code) {
        try {
            Integer result = voucherService.deleteVoucherByCode(code);
            ApiResponse response = new SuccessResponse("Voucher deleted successfully", HttpStatus.OK, result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Server error while deleting voucher"));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse> getUserVouchers(@PathVariable Long id, @RequestParam String type) {
        if (type == null || !Arrays.asList("online", "offline").contains(type)) {
            return ResponseEntity.badRequest().body(new BadRequest("Invalid voucher type!"));
        }
        try {
            List<UserVoucher> userVouchers = voucherService.getVouchersByUserId(id, type);
            ApiResponse response = new SuccessResponse("Successfully accessed the " + type + " vouchers list!", HttpStatus.OK, userVouchers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NotFoundException notFoundE) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("No vouchers found for the user"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @PostMapping("{code}/users/{id}")
    public ResponseEntity<ApiResponse> exchangeVoucherByItem(@PathVariable("code") String code, @PathVariable("id") Long id) {
        try {
            boolean result = voucherRepoService.exchangeItem(code, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Voucher successfully exchanged", 201));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    private void addItemToList(Long itemId, List<Item> items) {
        if (itemId != null) {
            Optional<Item> item = itemRepository.findById(itemId);
            item.ifPresent(items::add);
        }
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<InventoryDetailDto> getInventoryInfo(@PathVariable Long eventId) {
        Voucher voucher = voucherRepository.findByIdEvent(eventId);
        List<Item> items = new ArrayList<>();
        addItemToList(voucher.getIdItem1(), items);
        addItemToList(voucher.getIdItem2(), items);
        addItemToList(voucher.getIdItem3(), items);
        addItemToList(voucher.getIdItem4(), items);
        addItemToList(voucher.getIdItem5(), items);
        List<ItemDetailDto> itemDetailDTOS = items.stream()
                .map(ItemDetailDto::new)
                .collect(Collectors.toList());

        InventoryDetailDto result = new InventoryDetailDto(
                voucher.getType(),
                voucher.getCode(),
                voucher.getDescription(),
                voucher.getVoucherName(),
                voucher.getVoucherPrice(),
                voucher.getImageUrl(),
                voucher.getQrCode(),
                voucher.getAimCoin(),
                voucher.getExpirationDate(),
                itemDetailDTOS,
                voucher.getIdEvent()
        );

        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public ResponseEntity<InventoryImageUrlDto> uploadInventoryImages(
            @RequestParam("id_event") Long id_event,
            @ModelAttribute InventoryImageDto inventoryImages
    ) {
        if (!isImageFile(inventoryImages.getQrImg()) || !isImageFile(inventoryImages.getVoucherImg())) {
            return ResponseEntity.badRequest().build();
        }
        Voucher existVoucher;
        try {
            existVoucher = voucherService.findVoucherByIdEvent(id_event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (existVoucher == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Map qrImgUrlInfo = cloudinaryService.uploadFile(inventoryImages.getQrImg(), "qr_code");
            String qrImgUrl = (String) qrImgUrlInfo.get("url");
            Map voucherImgUrlInfo = cloudinaryService.uploadFile(inventoryImages.getVoucherImg(), "voucher");
            String voucherImgUrl = (String) voucherImgUrlInfo.get("url");

            if (qrImgUrl == null || voucherImgUrl == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            boolean isUploaded = voucherService.uploadInventoryImages(existVoucher, qrImgUrl, voucherImgUrl);
            if (!isUploaded) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(new InventoryImageUrlDto(qrImgUrl, voucherImgUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean isImageFile(MultipartFile file) {
        return file != null && file.getContentType() != null && file.getContentType().startsWith("image/");
    }

    @GetMapping("/exists/{voucherCode}")
    public ResponseEntity<Boolean> checkVoucherExists(@PathVariable String voucherCode) {
        // Check if the voucher exists in the repository
        boolean exists = voucherRepository.existsByCode(voucherCode.toUpperCase());
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/gifts")
    public ResponseEntity<ApiResponse> rewardVouchersForWinners(@RequestBody RewardVoucherRequest request) {
        try {
            voucherRepoService.rewardVoucherQuizGame(request.getWinnerIds(), request.getVoucherCode());
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Reward has been sent to the player's voucher inventory", 200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }
}
