package com.voufinal.event_service.service;

import com.voufinal.event_service.dto.ListEventDto;
import com.voufinal.event_service.entity.CreateRequestEvent;
import com.voufinal.event_service.exception.NotFoundException;
import com.voufinal.event_service.model.Event;
import com.voufinal.event_service.model.BrandsCooperation;
import com.voufinal.event_service.model.FavouriteEvent;
import com.voufinal.event_service.repository.BrandsCooperationRepository;
import com.voufinal.event_service.repository.EventRepository;
import com.voufinal.event_service.repository.FavouriteEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private QuizService quizService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private BrandsCooperationRepository brandsCooperationRepository;
    @Autowired
    private FavouriteEventRepository favouriteEventRepository;
    @Autowired
    private BrandClient brandClient;

    public List<Event> getAllEvents() throws Exception{
        try {
            return eventRepository.findAll();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<ListEventDto> getAllEventOfBrand(Long brandId){
        List<Long> eventIds = brandsCooperationRepository.findAllByIdBrand(brandId)
                .stream()
                .map(BrandsCooperation::getIdEvent)
                .collect(Collectors.toList());

        List<Event> events =  eventRepository.findAllById(eventIds);
        return events.stream()
                .map(event -> new ListEventDto(event, brandClient.getBrandLogo(event.getCreatedBy()).orElse(null)))  // Convert each Event to ListEventDTO using the constructor
                .collect(Collectors.toList());
    }
    public List<ListEventDto> getAllEventActive(){
        Timestamp currentTimestamp = Timestamp.from(Instant.now());;
        List<Event> events = eventRepository.findActiveEvents(currentTimestamp);
        System.out.println("Danh sách events: " + events);
        System.out.println("Vào getAllEventActive và đã tìm xong");

        return events.stream()
                .map(event -> new ListEventDto(event, brandClient.getBrandLogo(event.getCreatedBy()).orElse(null)))  // Convert each Event to ListEventDTO using the constructor
                .collect(Collectors.toList());

    }
//    public EventDetailDTO getAnEvent(Long eventId)throws Exception{
//        try {
//            Event event = eventRepository.findByIdEvent(eventId);
//            GameInfoDTO gameInfoDTO = quizService.getGameInfo(event.getIdEvent());
//            InventoryDetailDTO inventoryDetailDTO = inventoryService.getInventoryInfo(event.getIdEvent());
//            List<BrandsCooperation> brandsCooperations = brandsCooperationRepository.findAllByIdEvent(event.getIdEvent());
//
//            EventDetailDTO eventDetailDTO = new EventDetailDTO(
//                    event.getIdEvent(),
//                    event.getEventName(),
//                    event.getNumberOfVouchers(),
//                    event.getImageUrl(),
//                    event.getStartDate(),
//                    event.getEndDate(),
//                    brandsCooperations,
//                    gameInfoDTO,
//                    inventoryDetailDTO
//            );
//            return eventDetailDTO;
//        }
//        catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    public Event createEvent(CreateRequestEvent request) throws Exception{
        Event newEvent = new Event();
        newEvent.setEventName(request.getEventName());
        newEvent.setNumberOfVouchers(request.getNumberOfVouchers());
        newEvent.setStartDate(request.getStartDate());
        newEvent.setEndDate(request.getEndDate());
        newEvent.setCreatedBy(request.getCreatedBy());
        newEvent.setDeletedDate(null);
        newEvent.setRemainingVouchers(request.getNumberOfVouchers());
        try {
            return eventRepository.save(newEvent);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Event findEventById(Long id) throws Exception {
        try {
            return eventRepository.findByIdEvent(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Event updateEventById(Long id, CreateRequestEvent request) throws Exception {
        try {
            Event event = eventRepository.findByIdEvent(id);
            if (event == null)
                throw new NotFoundException("Event not found");
            else {
                Optional.ofNullable(request.getEventName()).ifPresent(action -> event.setEventName(request.getEventName()));
                Optional.ofNullable(request.getEndDate()).ifPresent(action -> event.setEndDate(request.getEndDate()));
                Optional.ofNullable(request.getStartDate()).ifPresent(action -> event.setStartDate(request.getStartDate()));
                Optional.ofNullable(request.getNumberOfVouchers()).ifPresent(action -> event.setNumberOfVouchers(request.getNumberOfVouchers()));
                eventRepository.save(event);
            }
            return event;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteEventById(Long id) throws Exception {
        try {
            Event event = eventRepository.findByIdEvent(id);
            List<FavouriteEvent> favouriteEvents = favouriteEventRepository.findAllByIdEvent(id);
            if (favouriteEvents.toArray().length == 0) {
                return false;
            }
            if (event == null)
                throw new NotFoundException("Event not found");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            favouriteEvents.forEach(fEvent -> {
                fEvent.setDeletedDate(now);
                favouriteEventRepository.save(fEvent);
            });

            event.setDeletedDate(now);
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean uploadEventImage(Event event, String bannerUrl) throws Exception {
        try {
            event.setImageUrl(bannerUrl);
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int updateRemainingVouchers(Long eventId) throws Exception {
        try {
            return eventRepository.decreaseEventRemainingVoucherByIdEvent(eventId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}