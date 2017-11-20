package hello.converter;

import hello.dto.calendar.CalendarDto;
import hello.model.Calendar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    List<CalendarDto> toCalendarDtoList(List<Calendar> calendarList);

    CalendarDto toCalendarDto(Calendar calendar);
}
