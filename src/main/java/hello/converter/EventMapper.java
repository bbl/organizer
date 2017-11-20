package hello.converter;

import hello.dto.event.EventDto;
import hello.dto.event.ShortEventDto;
import hello.model.Event;
import hello.model.EventUser;
import hello.utils.DateTimeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mappings({
            @Mapping(target = "creatorLogin", source = "event.creator.login"),
            @Mapping(target = "timeIntervalType", source = "event.duration.timeIntervalType"),
            @Mapping(target = "duration", source = "event.duration.duration"),
            @Mapping(target = "eventDateTime", dateFormat = DateTimeConstants.DD_MM_YYYY_HH_MM_SS)
    })
    EventDto toEventDto(Event event);

    List<EventDto> toEventDtoList(List<Event> eventList);

    List<String> toEventUsersDtoFormat(List<EventUser> eventUsers);

    default String toEventUserDtoFormat(EventUser eventUser) {
        return eventUser.getUser().getLogin();
    }

    ShortEventDto toShortEventDto(Event event);

    List<ShortEventDto> toShortEventDtoList(List<Event> eventList);

    ShortEventDto toShortEventDto(EventDto eventDto);

    List<ShortEventDto> toShortEventDtoListFromEventDtoList(List<EventDto> eventDtoList);

}
