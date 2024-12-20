package com.voufinal.event.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.voufinal.event.dto.ResponseDto;
import com.voufinal.event.dto.AddBrandRequestDto;
import com.voufinal.event.dto.AddGameRequestDto;
import com.voufinal.event.dto.AddItemRequestDto;
import com.voufinal.event.dto.AddVoucherRequestDto;
import com.voufinal.event.dto.EventDto;
import com.voufinal.event.dto.EventId_GameIdsDto;
import com.voufinal.event.dto.EventId_ItemIdsDto;
import com.voufinal.event.dto.EventId_VoucherIdsDto;
import com.voufinal.event.dto.EventRegistrationInfoDto;
import com.voufinal.event.dto.EventVoucherAndAdditionQuantityDto;
import com.voufinal.event.dto.EventWithBrandActiveStatusDto;
import com.voufinal.event.dto.GameDto;
import com.voufinal.event.dto.ReturnGameDto;
import com.voufinal.event.dto.ReturnVoucherDto;
import com.voufinal.event.service.IEventService;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/events", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EventController {

    // @Autowired
    // private KafkaTemplate<String, EventSessionInfo> kafkaTemplate;
    private final IEventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> eventDtos = eventService.fetchAllEvents();
        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/inProgress")
    public ResponseEntity<List<EventDto>> getEventsInProgress() {
        List<EventDto> eventDtos = eventService.fetchEventsInProgress();
        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/date/{specificDate}")
    public ResponseEntity<List<EventDto>> getEventsByDate(@PathVariable LocalDateTime specificDate) {
        List<EventDto> eventDtos = eventService.fetchEventsByDate(specificDate);
        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable String id) {
        EventDto eventDto = eventService.fetchEventById(id);
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<EventDto>> getEventsByIds(@PathVariable List<String> ids) {
        List<EventDto> eventDtos = eventService.fetchEventsByIds(ids);
        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/games/event/{eventId}")
    public ResponseEntity<List<ReturnGameDto>> getGamesByEvent(@PathVariable String eventId) {
        List<ReturnGameDto> gameDtos = eventService.fetchGamesByEvent(eventId);
        return ResponseEntity.ok(gameDtos);
    }

    @GetMapping("/vouchers/event/{eventId}")
    public ResponseEntity<List<ReturnVoucherDto>> getVouchersByEvent(@PathVariable String eventId) {
        List<ReturnVoucherDto> voucherDtos = eventService.fetchVouchersByEvent(eventId);
        return ResponseEntity.ok(voucherDtos);
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEventWithSessionInfo(@RequestBody EventRegistrationInfoDto eventRegistrationInfoDto) {
        return eventService.createEventWithSessionInfo(eventRegistrationInfoDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateEvent(@RequestBody EventDto eventDto) {
        eventService.updateEvent(eventDto);
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Event updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Event deleted successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<List<EventWithBrandActiveStatusDto>> getEventsByBrand(@PathVariable String brandId) {
        List<EventWithBrandActiveStatusDto> eventDtos = eventService.fetchEventsByBrand(brandId);
        return ResponseEntity.ok(eventDtos);
    }

    // @GetMapping("/brands")
    // public ResponseEntity<List<BrandWithEventActiveStatusDto>> getBrandsByEvent(@RequestParam("eventId") String eventId) {
    //     List<BrandWithEventActiveStatusDto> eventDtos = eventService.fetchBrandsByEvent(eventId);
    //     return ResponseEntity.ok(eventDtos);
    // }

    @PostMapping("/brands")
    public ResponseEntity<List<EventWithBrandActiveStatusDto>> getEventsByBrands(@RequestBody List<String> brandIds) {
        List<EventWithBrandActiveStatusDto> eventDtos = eventService.fetchEventsByBrands(brandIds);
        return ResponseEntity.ok(eventDtos);
    }

    @PostMapping("/events_brands/brands")
    public ResponseEntity<ResponseDto> addBrandsToEvent(@RequestBody AddBrandRequestDto addBrandsRequestDto) {
        eventService.addBrandsToEvent(addBrandsRequestDto.getEventId(), addBrandsRequestDto.getBrandIds());
        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Brands added to event successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/brands")
    public ResponseEntity<ResponseDto> removeBrandsFromEvent(@RequestBody AddBrandRequestDto addBrandsRequestDto) {
        eventService.removeBrandsFromEvent(addBrandsRequestDto.getEventId(), addBrandsRequestDto.getBrandIds());
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Brands removed from event successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/vouchers")
    public ResponseEntity<ResponseDto> addVouchersToEvent(@RequestBody AddVoucherRequestDto addVouchersRequestDto) {
        eventService.addVouchersToEvent(addVouchersRequestDto.getEventId(), addVouchersRequestDto.getVoucherId_Quantities());
        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Vouchers added to event successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/vouchers")
    public ResponseEntity<ResponseDto> removeVouchersFromEvent(@RequestBody EventId_VoucherIdsDto eventId_VoucherId) {
        eventService.removeVouchersFromEvent(eventId_VoucherId.getEventId(), eventId_VoucherId.getVoucherIds());
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Vouchers removed from event successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/items")
    public ResponseEntity<ResponseDto> addItemsToEvent(@RequestBody AddItemRequestDto addItemsRequestDto) {
        eventService.addItemsToEvent(addItemsRequestDto.getEventId(), addItemsRequestDto.getItemId_Quantities());
        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Items added to event successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/items")
    public ResponseEntity<ResponseDto> removeItemsFromEvent(@RequestBody EventId_ItemIdsDto eventId_ItemIds) {
        eventService.removeItemsFromEvent(eventId_ItemIds.getEventId(), eventId_ItemIds.getItemIds());
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Items removed from event successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/games")
    public ResponseEntity<ResponseDto> addGamesToEvent(@RequestBody AddGameRequestDto addGamesRequestDto) {
        eventService.addGamesToEvent(addGamesRequestDto.getEventId(), addGamesRequestDto.getGameId_StartTimes());
        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Games added to event successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/games")
    public ResponseEntity<ResponseDto> removeGamesFromEvent(@RequestBody EventId_GameIdsDto eventId_GameIds) {
        eventService.removeGamesFromEvent(eventId_GameIds.getEventId(), eventId_GameIds.getGameIds());
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Games removed from event successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // update events_vouchers table
    @PutMapping("/events_vouchers")
    public ResponseEntity<ResponseDto> addQuantityToEventVoucher(@RequestBody EventVoucherAndAdditionQuantityDto eventVoucherAndAdditionQuantityDto) {
        eventService.updateEventVoucher(eventVoucherAndAdditionQuantityDto.getEventId(), eventVoucherAndAdditionQuantityDto.getVoucherId(), eventVoucherAndAdditionQuantityDto.getAdditionalQuantity());
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Event voucher updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}