package hello.model;

import hello.model.enums.TimeIntervalType;

import javax.persistence.Embeddable;

@Embeddable
public class Duration {
    private TimeIntervalType timeIntervalType;
    private Integer duration;

    public Duration() {
    }

    public Duration(TimeIntervalType timeIntervalType, Integer duration) {
        this.timeIntervalType = timeIntervalType;
        this.duration = duration;
    }

    public TimeIntervalType getTimeIntervalType() {
        return timeIntervalType;
    }

    public void setTimeIntervalType(TimeIntervalType timeIntervalType) {
        this.timeIntervalType = timeIntervalType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
