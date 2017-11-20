package hello.service.impl;

import hello.converter.EventMapper;
import hello.dto.event.CreateEventInput;
import hello.dto.event.EventDto;
import hello.model.Duration;
import hello.model.Event;
import hello.model.EventUser;
import hello.model.User;
import hello.model.enums.EventStatus;
import hello.model.enums.TimeIntervalType;
import hello.repository.EventRepository;
import hello.repository.EventUserRepository;
import hello.service.EventService;
import hello.service.SecurityService;
import hello.service.UserService;
import hello.utils.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;

import static hello.utils.DateTimeConstants.DD_MM_YYYY_HH_MM_SS_FORMATTER;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventUserRepository eventUserRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventMapper eventMapper;

    @Override
    @Transactional
    public void addEvent(CreateEventInput createEventInput) {
        String loggedInUsername = securityService.findLoggedInUsername();
        User authorizedUser = userService.findByLogin(loggedInUsername);
        Event event = toEvent(createEventInput);
        event.setCreator(authorizedUser);
        eventRepository.save(event);

        ArrayList<EventUser> collect = createEventInput.getEventUsers().stream()
                .map(userService::findByLogin)
                .filter(Objects::nonNull)
                .collect(Collector.of(ArrayList::new,
                        (list, el) -> {
                            EventUser eventUser = new EventUser();
                            eventUser.setEvent(event);
                            eventUser.setUser(el);
                            list.add(eventUser);
                        },
                        (list, list2) -> {
                            list.addAll(list2);
                            return list;
                        }));

        eventUserRepository.save(collect);
    }

    @Override
    @Transactional
    public void updateEvent(CreateEventInput createEventInput) {
        addEvent(createEventInput);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.delete(id);
    }

    @Override
    public List<EventDto> getUserEventsBetween(Long userId, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<Event> eventList =
                eventRepository.findAllByCreatorIdAndEventDateTimeBetween(userId, dateStart, dateEnd);
        return eventMapper.toEventDtoList(eventList);
    }

    @Override
    public List<EventDto> getEventsByCreatorId(Long creatorId) {
        List<Event> eventList = eventRepository.findByCreatorId(creatorId);
        return eventMapper.toEventDtoList(eventList);
    }

    @Override
    public List<EventDto> getEventsLogginedUser() {
        User user = securityService.getLogginedUser();
        return getEventsByCreatorId(user.getId());
    }

    @Override
    public List<EventDto> getCurrentUserEventsOnMonth(String currentDate) {
        LocalDate date = LocalDate.parse(currentDate, DateTimeConstants.DD_MM_YYYY_FORMATTER);
        LocalDate startLocalDate = date.withDayOfMonth(1);
        LocalDate endLocalDate = date.plusMonths(1).withDayOfMonth(1).minusDays(1);
        LocalDateTime start = LocalDateTime.of(startLocalDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(endLocalDate, LocalTime.MAX);
        User user = securityService.getLogginedUser();
        return getUserEventsBetween(user.getId(), start, end);
    }

    @Override
    public List<EventDto> getCurrentUserEventsOnDay(String currentDate) {
        LocalDate date = LocalDate.parse(currentDate, DateTimeConstants.DD_MM_YYYY_FORMATTER);
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        User user = securityService.getLogginedUser();
        return getUserEventsBetween(user.getId(), start, end);
    }

    @Override
    public EventDto getShortEventInfo(Long id) {
        return eventMapper.toEventDto(eventRepository.findOne(id));
    }

    @Override
    public List<EventDto> getCurrentUserEventsOnMonthAndByCalendarId(String calendarId, String currentDate) {
        LocalDate date = LocalDate.parse(currentDate, DateTimeConstants.DD_MM_YYYY_FORMATTER);
        LocalDate startLocalDate = date.withDayOfMonth(1);
        LocalDate endLocalDate = date.plusMonths(1).withDayOfMonth(1).minusDays(1);
        LocalDateTime start = LocalDateTime.of(startLocalDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(endLocalDate, LocalTime.MAX);
        User user = securityService.getLogginedUser();
        return eventMapper.toEventDtoList(
                eventRepository.findAllByCalendarsContainingAndCreatorIdAndEventDateTimeBetween
                        (Long.parseLong(calendarId), user.getId(), start, end));
    }

    @Override
    public List<EventDto> getCurrentUserEventsOnDayAndByCalendarId(String calendarId, String currentDate) {
        LocalDate date = LocalDate.parse(currentDate, DateTimeConstants.DD_MM_YYYY_FORMATTER);
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        User user = securityService.getLogginedUser();
        return eventMapper.toEventDtoList(
                eventRepository.findAllByCalendarsContainingAndCreatorIdAndEventDateTimeBetween
                        (Long.parseLong(calendarId), user.getId(), start, end));
    }

    @Override
    public void sendResponse(String eventId, String status){
        EventUser eventUser = eventUserRepository.findOne(Long.parseLong(eventId));
        eventUser.setEventStatus(EventStatus.valueOf(status));
        eventUserRepository.save(eventUser);
    }

    private Event toEvent(CreateEventInput input) {
        Event event = new Event();
        event.setTitle(input.getTitle());
        event.setPlaceId(input.getPlaceId());
        event.setEventDateTime(LocalDateTime.parse(input.getEventDateTime(), DD_MM_YYYY_HH_MM_SS_FORMATTER));
        event.setDuration(new Duration(TimeIntervalType.valueOf(input.getTimeIntervalType()), input.getDuration()));
        event.setCreationDateTime(LocalDateTime.now());
        event.setLastModificationDateTime(LocalDateTime.now());
        return event;
    }
}
