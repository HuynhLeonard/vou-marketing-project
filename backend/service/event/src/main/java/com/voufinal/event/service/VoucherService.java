package com.voufinal.event.service;

import com.voufinal.event.dto.BrandDto;
import com.voufinal.event.dto.VoucherDto;
import com.voufinal.event.entity.*;
import com.voufinal.event.mapper.BrandMapper;
import com.voufinal.event.mapper.VoucherMapper;
import com.voufinal.event.repository.*;
import com.voufinal.event.client.UserServiceClient;
import com.voufinal.event.common.EventIntermediateTableStatus;
import com.voufinal.event.common.ItemId_Quantity;
import com.voufinal.event.exception.NotFoundException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoucherService implements IVoucherService {
    
    private final VoucherRepository     voucherRepository;
    private final ItemRepository        itemRepository;
    private final VoucherItemRepository voucherItemRepository;
    private final BrandRepository       brandRepository;
    private final UserServiceClient    usersServiceClient;

    @Override
    public List<VoucherDto> fetchAllVouchers() {
        List<Voucher> vouchers = voucherRepository.findAll();
        return vouchers.stream().map(VoucherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public VoucherDto fetchVoucherById(String id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Voucher", "id", id)
        );
        return VoucherMapper.toDto(voucher);
    }

    @Override
    public List<VoucherDto> fetchVouchersByIds(List<String> ids) {
        List<Voucher> vouchers = voucherRepository.findByIds(ids);
        return vouchers.stream().map(VoucherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VoucherDto> fetchVouchersByBrand(String brandId) {
        List<Voucher> vouchers = voucherRepository.findByBrand(brandId);
        return vouchers.stream().map(VoucherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VoucherDto> fetchVouchersByBrands(List<String> brandIds) {
        List<Voucher> vouchers = voucherRepository.findByBrands(brandIds);
        return vouchers.stream().map(VoucherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public VoucherDto createVoucher(VoucherDto voucherDto) {
        
        BrandDto brandDto = usersServiceClient.getBrand(voucherDto.getBrand_id());

        Voucher voucher = VoucherMapper.toEntity(voucherDto);
        voucher.setBrand_id(brandDto.getId());
        voucherRepository.save(voucher);
        return VoucherMapper.toDto(voucher);
    }

    @Override
    public boolean updateVoucher(VoucherDto voucherDto) {
        try {
            Voucher voucher = voucherRepository.findById(voucherDto.getId()).orElseThrow(
                    () -> new NotFoundException("Voucher", "id", voucherDto.getId())
            );

            Voucher updatedVoucher = VoucherMapper.toEntity(voucherDto);
            updatedVoucher.setId(voucher.getId());
            voucherRepository.save(updatedVoucher);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteVoucher (String id) {
        try {
            Voucher voucher = voucherRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Voucher", "id", id)
            );

            voucherRepository.delete(voucher);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addVoucherItemConversion (String voucherId, List<ItemId_Quantity> itemIds_quantities) {
        try {
            Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(
                    () -> new NotFoundException("Voucher", "id", voucherId)
            );

            for (ItemId_Quantity itemIds_quantity : itemIds_quantities) {
                Item item = itemRepository.findById(itemIds_quantity.getItemId()).orElseThrow(
                        () -> new NotFoundException("Item", "id", itemIds_quantity.getItemId())
                );

                VoucherItem voucherItem = voucherItemRepository.findByVoucherAndItem(voucherId, itemIds_quantity.getItemId());
                if (voucherItem != null) {
                    voucherItem.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    voucherItem.setNumberOfItem(itemIds_quantity.getQuantity());
                    voucherItemRepository.save(voucherItem);
                }
                else {
                    voucherItem = new VoucherItem();
                    voucherItem.setVoucher(voucher);
                    voucherItem.setItem(item);
                    voucherItem.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    voucherItem.setNumberOfItem(itemIds_quantity.getQuantity());
                    voucherItemRepository.save(voucherItem);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}