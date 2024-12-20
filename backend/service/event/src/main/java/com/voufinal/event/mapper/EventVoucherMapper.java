package com.voufinal.event.mapper;

import com.voufinal.event.dto.EventVoucherDto;
import com.voufinal.event.entity.EventVoucher;

import org.springframework.stereotype.Service;

@Service
public class EventVoucherMapper {

    // Convert EventVoucher to EventVoucherDto
    public static EventVoucherDto toDto(EventVoucher eventVoucher) {
        if (eventVoucher == null) {
            return null;
        }

        EventVoucherDto eventVoucherDto = new EventVoucherDto();
        eventVoucherDto.setEvent(EventMapper.toDto(eventVoucher.getEvent()));
        eventVoucherDto.setVoucher(VoucherMapper.toDto(eventVoucher.getVoucher()));

        return eventVoucherDto;
    }

    // Convert EventVoucherDto to EventVoucher
    public static EventVoucher toEntity(EventVoucherDto dto) {
        if (dto == null) {
            return null;
        }

        EventVoucher eventVoucher = new EventVoucher();
        eventVoucher.setEvent(EventMapper.toEntity(dto.getEvent()));
        eventVoucher.setVoucher(VoucherMapper.toEntity(dto.getVoucher()));

        return eventVoucher;
    }
}