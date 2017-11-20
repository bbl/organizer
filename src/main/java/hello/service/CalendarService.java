package hello.service;

import hello.dto.calendar.CalendarDto;
import hello.dto.calendar.UpdateCalendarDto;
import hello.model.Calendar;

import java.util.List;

public interface CalendarService {
    void addCalendar(CalendarDto calendarDto);

    List<CalendarDto> getAllCalendar(Long id);

    CalendarDto getCalendarById(Long id);

    void updateCalendar(UpdateCalendarDto input);

    void deleteCalendar(Long id);
}
