package com.voufinal.gift_service.service;

import com.voufinal.gift_service.constant.HttpStatus;
import com.voufinal.gift_service.entity.request.CreateItemRequest;
import com.voufinal.gift_service.exception.NotFoundException;
import com.voufinal.gift_service.model.Item;
import com.voufinal.gift_service.model.Voucher;
import com.voufinal.gift_service.repository.ItemRepository;
import com.voufinal.gift_service.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private VoucherRepository voucherRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public int createItem(CreateItemRequest createItemRequest) throws Exception {
        Item item = new Item();
        item.setItemName(createItemRequest.getItemName());
        item.setDescription(createItemRequest.getDescription());
        item.setIdEvent(createItemRequest.getIdEvent());
        item.setImageUrl(createItemRequest.getImageUrl());
        itemRepository.save(item);
        return HttpStatus.CREATED;
    }

    public Item getItemById(Long id) {
        return itemRepository.findItemByIdItem(id);
    }

    public Integer updateItemById(Long id, CreateItemRequest createItemRequest) {
        Item itemFound = itemRepository.findItemByIdItem(id);
        if (itemFound == null) {
            throw new NotFoundException("No item found with the given id");
        } else {


            if (createItemRequest.getImageUrl() != null) {
                itemFound.setImageUrl(createItemRequest.getImageUrl());
            }
            if (createItemRequest.getItemName() != null) {
                itemFound.setItemName(createItemRequest.getItemName());
            }
            if (createItemRequest.getIdEvent() != null) {
                itemFound.setIdEvent(createItemRequest.getIdEvent());
            }
        }
        itemRepository.save(itemFound);
        return HttpStatus.OK;
    }

    // TODO: Delete from itemrepo first
    public Integer deleteItemById(Long id) {
        Item itemFound = itemRepository.findItemByIdItem(id);
        if (itemFound == null) {
            throw new NotFoundException("No item found to delete");
        }
        itemRepository.delete(itemFound);
        return HttpStatus.OK;
    }

    public List<Item> getListItemsOfVoucher(String code) {
        List<Item> items = new ArrayList<>();
        Voucher voucher = voucherRepository.findByCode(code);

        if (voucher == null) {
            throw new NotFoundException("No voucher found for the given code");
        }
        Optional.ofNullable(voucher.getIdItem1()).ifPresent(action -> {
            items.add(itemRepository.findItemByIdItem(voucher.getIdItem1()));
        });
        Optional.ofNullable(voucher.getIdItem2()).ifPresent(action -> {
            items.add(itemRepository.findItemByIdItem(voucher.getIdItem2()));
        });
        Optional.ofNullable(voucher.getIdItem3()).ifPresent(action -> {
            items.add(itemRepository.findItemByIdItem(voucher.getIdItem3()));
        });
        Optional.ofNullable(voucher.getIdItem4()).ifPresent(action -> {
            items.add(itemRepository.findItemByIdItem(voucher.getIdItem4()));
        });
        Optional.ofNullable(voucher.getIdItem5()).ifPresent(action -> {
            items.add(itemRepository.findItemByIdItem(voucher.getIdItem5()));
        });

        return items;
    }

    public Set<Item> getListItemsOfEvent(Long eventId) {
        List<Voucher> vouchersOfEvent = voucherRepository.findVouchersByIdEvent(eventId);
        Set<Item> items = new HashSet<>();

        if (vouchersOfEvent.isEmpty()) {
            throw new NotFoundException("No items found for the given event");
        }

        for(Voucher voucher : vouchersOfEvent) {
            items.addAll(getListItemsOfVoucher(voucher.getCode()));
        }

        return items;
    }
}
