package hello.dto.event;

import java.util.List;

public class EventDto {
    private String title;
    private Integer placeId;
    private Integer creatorLogin;
    private String timeIntervalType;
    private Integer duration;
    private String eventDateTime;
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

    public Integer getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(Integer creatorLogin) {
        this.creatorLogin = creatorLogin;
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

    public List<String> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<String> eventUsers) {
        this.eventUsers = eventUsers;
    }
}
