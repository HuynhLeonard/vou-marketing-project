package com.voufinal.event.service;

import com.voufinal.event.dto.AddUserRequestDto;
import com.voufinal.event.dto.BrandDto;
import com.voufinal.event.dto.EventDto;
import com.voufinal.event.dto.EventWithBrandActiveStatusDto;
import com.voufinal.event.dto.EventRegistrationInfoDto;
import com.voufinal.event.dto.GameDto;
import com.voufinal.event.dto.ItemDto;
import com.voufinal.event.dto.ReturnGameDto;
import com.voufinal.event.dto.ReturnVoucherDto;
import com.voufinal.event.dto.VoucherDto;
import com.voufinal.event.mapper.GameMapper;
import com.voufinal.event.model.EventSessionInfo;
import com.voufinal.event.model.Notifcation_Event_Created_Data;
import com.voufinal.event.model.NotificationInfo;
import com.voufinal.event.entity.*;
import com.voufinal.event.mapper.BrandMapper;
import com.voufinal.event.mapper.EventMapper;
import com.voufinal.event.repository.*;
import com.voufinal.event.common.EventIntermediateTableStatus;
import com.voufinal.event.common.GameId_StartTime;
import com.voufinal.event.common.ItemId_Quantity;
import com.voufinal.event.common.VoucherId_Quantity;
import com.voufinal.event.common.VoucherId_Quantity_ItemIds_Quantities;
import com.voufinal.event.client.GameServiceClient;
import com.voufinal.event.client.NotificationServiceClient;
import com.voufinal.event.client.UserServiceClient;
import com.voufinal.event.dto.ResponseDto;
import com.voufinal.event.exception.NotFoundException;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements IEventService {

    private static final Logger                 log = LoggerFactory.getLogger(EventService.class);
    
    private final EventRepository               eventsRepository;
    private final BrandRepository               brandRepository;
    private final VoucherRepository             voucherRepository;
    private final ItemRepository                itemRepository;
    private final EventBrandRepository          eventBrandRepository;
    private final EventVoucherRepository        eventVoucherRepository;
    private final EventItemRepository           eventItemRepository;
    private final EventGameRepository           eventGameRepository;

    private final IVoucherService              voucherService;
    private final IItemService                 itemService;

    private final GameServiceClient            gamesServiceClient;
    private final UserServiceClient            usersServiceClient;
    private final NotificationServiceClient    notificationsServiceClient;

    
    // Properties props = new Properties();

    // KafkaProducer<String, EventSessionInfo> producer = new KafkaProducer<>(props);
    @Autowired
    private KafkaTemplate<String, EventSessionInfo> kafkaTemplateEventSessionInfo;

    @Autowired
    private KafkaTemplate<String, Notifcation_Event_Created_Data> kafkaTemplateNotificationInfo;

    @Override
    public List<EventDto> fetchAllEvents() {
        return eventsRepository.findAll().stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> fetchEventsInProgress() {
        return eventsRepository.findAll().stream()
                .filter(event -> event.getStartDate().isBefore(LocalDateTime.now()) && event.getEndDate().isAfter(LocalDateTime.now()))
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> fetchEventsByDate(LocalDateTime specificDate) {
        return eventsRepository.findAll().stream()
                .filter(event -> event.getStartDate().isBefore(specificDate) && event.getEndDate().isAfter(specificDate))
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    // @Override
    // public List<EventDto> fetchEventsByVoucher() {
    //     return eventsRepository.findAll().stream()
    //             .sorted(Comparator.comparing(Event::getNumberOfVoucher).reversed())
    //             .map(EventMapper::toDto)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public List<EventDto> fetchEventsByItem() {
    //     return eventsRepository.findAll().stream()
    //             .sorted(Comparator.comparing(Event::getName))
    //             .map(EventMapper::toDto)
    //             .collect(Collectors.toList());
    // }

    @Override
    public List<EventWithBrandActiveStatusDto> fetchEventsByBrand(String brandId) {
        List<EventBrand> eventBrands = eventBrandRepository.findByBrand(brandId);

        return eventBrands.stream()
                .map(eventBrand -> {
                    Event event = eventBrand.getEvent();
                    return new EventWithBrandActiveStatusDto(
                            event.getId().toString(),
                            event.getName(),
                            event.getImage(),
                            event.getNumberOfVoucher(),
                            event.getStartDate(),
                            event.getEndDate(),
                            eventBrand.getActiveStatus()
                    );
                })
                .collect(Collectors.toList());
    }

    // @Override
    // public List<BrandWithEventActiveStatusDto> fetchBrandsByEvent(String eventId) {
    //     List<EventBrand> eventBrands = eventBrandRepository.findByEvent(eventId);
    // }

    @Override
    public List<EventWithBrandActiveStatusDto> fetchEventsByBrands(List<String> brandIds) {
        List<EventBrand> eventBrands = eventBrandRepository.findByBrands(brandIds);

        return eventBrands.stream()
                .filter(eventBrand -> brandIds.contains(eventBrand.getBrand_id()))
                .map(eventBrand -> {
                    Event event = eventBrand.getEvent();
                    return new EventWithBrandActiveStatusDto(
                            event.getId().toString(),
                            event.getName(),
                            event.getImage(),
                            event.getNumberOfVoucher(),
                            event.getStartDate(),
                            event.getEndDate(),
                            eventBrand.getActiveStatus()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public EventDto fetchEventById(String id) {
        Event event = eventsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event", "id", id)
        );
        return EventMapper.toDto(event);
    }

    @Override
    public List<ReturnGameDto> fetchGamesByEvent(String eventId) {
        List<EventGame> eventGames = eventGameRepository.findByEvent(eventId);
        List<ReturnGameDto> returnGameDtos = new ArrayList<>();

        for (EventGame eventGame : eventGames) {
            GameDto gameDto = gamesServiceClient.getGameById(eventGame.getGame_id());
            returnGameDtos.add(new ReturnGameDto(gameDto.getId(),
                                    gameDto.getName(),
                                    gameDto.getImage(),
                                    gameDto.getType(),
                                    gameDto.isItemSwappable(),
                                    gameDto.getInstruction(),
                                    eventGame.getStartTime()));
        }

        return returnGameDtos;
    }

    @Override
    public List<ReturnVoucherDto> fetchVouchersByEvent(String eventId) {
        List<EventVoucher> eventVouchers = eventVoucherRepository.findByEvent(eventId);
        List<ReturnVoucherDto> returnVoucherDtos = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (EventVoucher eventVoucher : eventVouchers) {
            VoucherDto voucherDto = voucherService.fetchVoucherById(eventVoucher.getVoucher().getId());
            String formatExpiredDateTime = voucherDto.getExpiredDate().format(format); 
            returnVoucherDtos.add(new ReturnVoucherDto(voucherDto.getId(),
                                    voucherDto.getBrand_id(),
                                    voucherDto.getVoucherCode(),
                                    voucherDto.getQrCode(),
                                    voucherDto.getImage(),
                                    voucherDto.getValue(),
                                    voucherDto.getDescription(),
                                    formatExpiredDateTime,
                                    voucherDto.getStatus(),
                                    voucherDto.getUnitValue(),
                                    eventVoucher.getNumberOfVoucher()));
        }

        return returnVoucherDtos;
    }

    @Override
    public List<EventDto> fetchEventsByIds(List<String> ids) {
        List<Event> events = eventsRepository.findByIds(ids);
        return events.stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = EventMapper.toEntity(eventDto);
        Event createdEvent = eventsRepository.save(event);
        return EventMapper.toDto(createdEvent);
    }

    @Override
    public boolean updateEvent(EventDto eventDto) {
        try {
            Event event = eventsRepository.findById(eventDto.getId()).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventDto.getId())
            );

            event.setName(eventDto.getName());
            event.setStartDate(eventDto.getStartDate());
            event.setEndDate(eventDto.getEndDate());
            event.setNumberOfVoucher(eventDto.getNumberOfVoucher());
            event.setImage(eventDto.getImage());

            eventsRepository.save(event);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteEvent(String id) {
        try {
            Event event = eventsRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Event", "id", id)
            );

            eventsRepository.delete(event);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addBrandsToEvent(String eventId, List<String> brandIds) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (String brandId : brandIds) {
                Brand brand = brandRepository.findById(brandId).orElseThrow(
                        () -> new NotFoundException("Brand", "id", brandId)
                );

                EventBrand eventBrand = eventBrandRepository.findByEventAndBrand(eventId, brandId);
                if (eventBrand != null) {
                    eventBrand.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventBrandRepository.save(eventBrand);
                }
                else {
                    eventBrand = new EventBrand();
                    eventBrand.setEvent(event);
                    eventBrand.setBrand_id(brandId);
                    eventBrand.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventBrandRepository.save(eventBrand);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addBrandsByEmailsToEvent(String eventId, List<BrandDto> brands) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            if (brands == null || brands.isEmpty()) {
                log.info("BRAND IS NULL");
                return false;
            } else {
                for (BrandDto _brandDto : brands) {
                    Brand _brandEntity = BrandMapper.toEntity(_brandDto);
                    EventBrand eventBrand = eventBrandRepository.findByEventAndBrand(eventId, _brandEntity.getId());
                    if (eventBrand != null) {
                        eventBrand.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                        eventBrandRepository.save(eventBrand);
                    }
                    else {
                        eventBrand = new EventBrand();
                        eventBrand.setEvent(event);
                        eventBrand.setBrand_id(_brandEntity.getId());
                        eventBrand.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                        eventBrandRepository.save(eventBrand);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean removeBrandsFromEvent(String eventId, List<String> brandIds) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (String brandId : brandIds) {
                Brand brand = brandRepository.findById(brandId).orElseThrow(
                        () -> new NotFoundException("Brand", "id", brandId)
                );

                EventBrand eventBrand = eventBrandRepository.findByEventAndBrand(eventId, brandId);
                if (eventBrand != null) {
                    eventBrand.setActiveStatus(EventIntermediateTableStatus.INACTIVE);
                    eventBrandRepository.save(eventBrand);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addVouchersToEvent(String eventId, List<VoucherId_Quantity> voucherIds_quantities) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (VoucherId_Quantity voucherIds_quantity : voucherIds_quantities) {
                Voucher voucher = voucherRepository.findById(voucherIds_quantity.getVoucherId()).orElseThrow(
                        () -> new NotFoundException("Voucher", "id", voucherIds_quantity.getVoucherId())
                );

                EventVoucher eventVoucher = eventVoucherRepository.findByEventAndVoucher(eventId, voucherIds_quantity.getVoucherId());
                if (eventVoucher != null) {
                    eventVoucher.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventVoucher.setNumberOfVoucher(voucherIds_quantity.getQuantity());
                    eventVoucherRepository.save(eventVoucher);
                }
                else {
                    eventVoucher = new EventVoucher();
                    eventVoucher.setEvent(event);
                    eventVoucher.setVoucher(voucher);
                    eventVoucher.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventVoucher.setNumberOfVoucher(voucherIds_quantity.getQuantity());
                    eventVoucherRepository.save(eventVoucher);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean removeVouchersFromEvent(String eventId, List<String> voucherIds) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (String voucherId : voucherIds) {
                Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(
                        () -> new NotFoundException("Voucher", "id", voucherId)
                );

                EventVoucher eventVoucher = eventVoucherRepository.findByEventAndVoucher(eventId, voucherId);
                if (eventVoucher != null) {
                    eventVoucher.setActiveStatus(EventIntermediateTableStatus.INACTIVE);
                    eventVoucherRepository.save(eventVoucher);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addItemsToEvent(String eventId, List<ItemId_Quantity> itemIds_quantities) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (ItemId_Quantity itemIds_quantity : itemIds_quantities) {
                Item item = itemRepository.findById(itemIds_quantity.getItemId()).orElseThrow(
                    () -> new NotFoundException("Item", "id", itemIds_quantity.getItemId())
                );

                EventItem eventItem = eventItemRepository.findByEventAndItem(eventId, itemIds_quantity.getItemId());
                if (eventItem != null) {
                    eventItem.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventItem.setNumberOfItem(itemIds_quantity.getQuantity());
                    eventItemRepository.save(eventItem);
                }
                else {
                    eventItem = new EventItem();
                    eventItem.setEvent(event);
                    eventItem.setItem(item);
                    eventItem.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventItem.setNumberOfItem(itemIds_quantity.getQuantity());
                    eventItemRepository.save(eventItem);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean removeItemsFromEvent(String eventId, List<String> itemIds) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (String itemId : itemIds) {
                Item item = itemRepository.findById(itemId).orElseThrow(
                        () -> new NotFoundException("Item", "id", itemId)
                );

                EventItem eventItem = eventItemRepository.findByEventAndItem(eventId, itemId);
                if (eventItem != null) {
                    eventItem.setActiveStatus(EventIntermediateTableStatus.INACTIVE);
                    eventItemRepository.save(eventItem);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public boolean addGamesToEvent(String eventId, List<GameId_StartTime> listGameId_StartTime) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (GameId_StartTime gameId_startTime : listGameId_StartTime) {
                GameDto gameDto = gamesServiceClient.getGameById(gameId_startTime.getGameId());
                Game game = GameMapper.toEntity(gameDto);
                if (game == null) {
                    throw new NotFoundException("Game", "id", gameId_startTime.getGameId().toString());
                }
                
                EventGame eventGame = eventGameRepository.findByEventAndGame(eventId, gameId_startTime.getGameId());
                
                if (eventGame != null) {
                    eventGame.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventGame.setStartTime(gameId_startTime.getStartTime());
                    eventGameRepository.save(eventGame);
                }
                else {
                    eventGame = new EventGame();
                    eventGame.setEvent(event);
                    eventGame.setGame_id(gameId_startTime.getGameId());
                    eventGame.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                    eventGame.setStartTime(gameId_startTime.getStartTime());
                    eventGameRepository.save(eventGame);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public boolean removeGamesFromEvent(String eventId, List<Long> gameIds) {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            for (Long gameId : gameIds) {
                GameDto gameDto = gamesServiceClient.getGameById(gameId);
                Game game = GameMapper.toEntity(gameDto);
                if (game == null) {
                    throw new NotFoundException("Game", "id", gameId.toString());
                }

                EventGame eventGame = eventGameRepository.findByEventAndGame(eventId, gameId);
                if (eventGame != null) {
                    eventGame.setActiveStatus(EventIntermediateTableStatus.INACTIVE);
                    eventGameRepository.save(eventGame);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public ResponseEntity<ResponseDto> createEventWithSessionInfo(EventRegistrationInfoDto eventRegistrationInfoDto) {
        VoucherDto                  existVoucher;
        ItemDto                     existItem;
        String                      voucherId;
        String                      itemId;
        EventDto                    existEvent                      = eventRegistrationInfoDto.getEvent();
        List<VoucherId_Quantity>    listVoucherIdQuantity           = new ArrayList<>();
        List<ItemId_Quantity>       listItemIdQuantity              = new ArrayList<>();
        String                      regexSplitStringLocalDateTime   = "T";
        Integer                     sumNumberOfVoucher              = 0;
        EventDto                    eventDto                        = new EventDto();
        String                      eventId                         = null;
        if (existEvent != null)
        {
                                    eventDto                        = this.createEvent(existEvent);
                                    eventId                         = eventDto.getId();
        }
        List<BrandDto>              brands;

        if (eventId == null) {
            ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Event creation failed.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        } else {
            brands = usersServiceClient.getBrandsByEmails(eventRegistrationInfoDto.getEmails());
            if (brands == null || brands.isEmpty()) {
                ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "There is no related brands at all.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }

            if (!this.addBrandsByEmailsToEvent(eventId, brands)) {
                ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Brand addition failed.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }

            for (VoucherId_Quantity_ItemIds_Quantities listVoucher_Items : eventRegistrationInfoDto.getListVoucher_Items()) {
                voucherId       = listVoucher_Items.getVoucherId();
                existVoucher    = voucherService.fetchVoucherById(voucherId);

                if (existVoucher == null) {
                    ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Voucher creation failed.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                } else {
                    listVoucherIdQuantity.add(new VoucherId_Quantity(voucherId, listVoucher_Items.getQuantityOfVoucher()));
                    listItemIdQuantity.clear();

                    for (ItemId_Quantity itemId_Quantity : listVoucher_Items.getItemIds_quantities()) {
                        itemId      = itemId_Quantity.getItemId();
                        existItem   = itemService.fetchItemById(itemId);

                        if (existItem == null) {
                            ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Item creation failed.");
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                        } else {
                            listItemIdQuantity.add(new ItemId_Quantity(itemId, itemId_Quantity.getQuantity()));
                        }
                    }
                    if (listItemIdQuantity.size() > 0) {
                        if (!voucherService.addVoucherItemConversion(voucherId, listItemIdQuantity)) {
                            ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Voucher-Item conversion failed.");
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                        }
                        if (!this.addItemsToEvent(eventId, listItemIdQuantity)) {
                            ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Voucher addition failed.");
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                        }

                        sumNumberOfVoucher += listVoucher_Items.getQuantityOfVoucher();
                    }
                }
            }

            if (!this.addVouchersToEvent(eventId, listVoucherIdQuantity)) {
                ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Voucher addition failed.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            
            if (this.addGamesToEvent(eventId, eventRegistrationInfoDto.getListGameId_StartTime())) {

                // Update sum(number of voucher)
                existEvent.setId(eventId);
                existEvent.setNumberOfVoucher(sumNumberOfVoucher);
                if (!this.updateEvent(existEvent)) {
                    ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Sum of number of voucher for this event updating failed.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                }

                // Send list of games to Kafka - topic = event-session
                try {
                    for (GameId_StartTime gameId_StartTime : eventRegistrationInfoDto.getListGameId_StartTime()) {
                        EventSessionInfo eventSessionInfo = new EventSessionInfo(
                            eventId,
                            gameId_StartTime.getGameId().toString(),
                            gameId_StartTime.getGameType(),
                            eventRegistrationInfoDto.getEvent().getStartDate().toString().split(regexSplitStringLocalDateTime)[0],
                            eventRegistrationInfoDto.getEvent().getEndDate().toString().split(regexSplitStringLocalDateTime)[0],
                            gameId_StartTime.getStartTime().toString(),
                            gameId_StartTime.getStartTime().plusMinutes(30).toString()
                        );
                        
                        log.info("EventSessionInfo: " + eventSessionInfo.toString());
                        kafkaTemplateEventSessionInfo.send("event-session", eventSessionInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // wait for session response
                // Send notification to related brands
                try {
                    // get list of brandIds from brands
                    List<String> brandIds = new ArrayList<>();
                    for (BrandDto brand : brands) {
                        brandIds.add(brand.getId());
                    }

                    NotificationInfo notificationInfo = new NotificationInfo("You've been invited to " + eventRegistrationInfoDto.getEvent().getName() + " event!", brands.get(0).getBrandName() + " invited you to join!", eventRegistrationInfoDto.getEvent().getImage());
                    Notifcation_Event_Created_Data notification_Event_Created_Data = new Notifcation_Event_Created_Data(notificationInfo, brandIds);
                    String notificationId = notificationsServiceClient.addUsersToNotification(new AddUserRequestDto(notificationInfo, brandIds));
                    System.out.println("NotificationId: " + notificationId);
                    kafkaTemplateNotificationInfo.send("event-notification", notification_Event_Created_Data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                ResponseDto res = new ResponseDto(HttpStatus.BAD_REQUEST, "Game addition failed.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
        }

        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Event created successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Override
    public boolean updateEventVoucher(String eventId, String voucherId, int additionalQuantity)
    {
        try {
            Event event = eventsRepository.findById(eventId).orElseThrow(
                    () -> new NotFoundException("Event", "id", eventId)
            );

            Voucher voucher = voucherRepository.findById(voucherId).orElseThrow(
                    () -> new NotFoundException("Voucher", "id", voucherId)
            );

            EventVoucher eventVoucher = eventVoucherRepository.findByEventAndVoucher(eventId, voucherId);
            if (eventVoucher != null) {
                eventVoucher.setNumberOfVoucher(Math.max(0, eventVoucher.getNumberOfVoucher() + additionalQuantity));
                eventVoucherRepository.save(eventVoucher);
            }
            else {
                eventVoucher = new EventVoucher();
                eventVoucher.setEvent(event);
                eventVoucher.setVoucher(voucher);
                eventVoucher.setActiveStatus(EventIntermediateTableStatus.ACTIVE);
                eventVoucher.setNumberOfVoucher(Math.max(0, additionalQuantity));
                eventVoucherRepository.save(eventVoucher);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}