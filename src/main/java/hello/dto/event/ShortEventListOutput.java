package hello.dto.event;

import java.util.List;

public class ShortEventListOutput {
    private List<ShortEventDto> eventDtoList;

    public ShortEventListOutput(List<ShortEventDto> eventDtoList) {
        this.eventDtoList = eventDtoList;
    }

    public List<ShortEventDto> getEventDtoList() {
        return eventDtoList;
    }

    public void setEventDtoList(List<ShortEventDto> eventDtoList) {
        this.eventDtoList = eventDtoList;
    }
}
