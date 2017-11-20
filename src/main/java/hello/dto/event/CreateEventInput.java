package hello.dto.event;

import java.util.List;

public class CreateEventInput {

    private String title;
    private Integer placeId;
    private String timeIntervalType;
    private Integer duration;
    private String eventDateTime;
    private Integer notifyDuration;
    private List<String> eventUsers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getTimeIntervalType() {
        return timeIntervalType;
    }

    public void setTimeIntervalType(String timeIntervalType) {
        this.timeIntervalType = timeIntervalType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Integer getNotifyDuration() {
        return notifyDuration;
    }

    public void setNotifyDuration(Integer notifyDuration) {
        this.notifyDuration = notifyDuration;
    }

    public List<String> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<String> eventUsers) {
        this.eventUsers = eventUsers;
    }
}
