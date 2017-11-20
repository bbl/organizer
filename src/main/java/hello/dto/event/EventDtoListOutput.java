package hello.dto.event;

import java.util.List;

public class EventDtoListOutput {
    private List<EventDto> eventDtoList;

    public EventDtoListOutput(List<EventDto> eventDtoList) {
        this.eventDtoList = eventDtoList;
    }

    public List<EventDto> getEventDtoList() {
        return eventDtoList;
    }

    public void setEventDtoList(List<EventDto> eventDtoList) {
        this.eventDtoList = eventDtoList;
    }
}
