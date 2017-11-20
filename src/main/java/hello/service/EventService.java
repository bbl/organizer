package hello.service;

import hello.dto.event.CreateEventInput;
import hello.dto.event.EventDto;
import hello.model.Event;
import hello.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    void addEvent(CreateEventInput createEventInput);

    void updateEvent(CreateEventInput createEventInput);

    void deleteEvent(Long id);

    List<EventDto> getUserEventsBetween(Long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    List<EventDto> getEventsByCreatorId(Long creatorId);

    List<EventDto> getEventsLogginedUser();

    List<EventDto> getCurrentUserEventsOnMonth(String date);

    List<EventDto> getCurrentUserEventsOnDay(String date);

    EventDto getShortEventInfo(Long id);

    List<EventDto> getCurrentUserEventsOnMonthAndByCalendarId(String calendarId, String date);

    List<EventDto> getCurrentUserEventsOnDayAndByCalendarId(String calendarId, String date);

    void sendResponse(String eventId, String status);
}
