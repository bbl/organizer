package hello.model;

import hello.converter.LocalDateTimeConverter;
import hello.model.enums.TimeIntervalType;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(name = "place_id")
    private Integer placeId;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Embedded
    private Duration duration;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "event_date")
    private LocalDateTime eventDateTime;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "creation_date")
    private LocalDateTime creationDateTime;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "last_modification_date")
    private LocalDateTime lastModificationDateTime;

    @Column(name = "notify_duration")
    private int notifyDuration;

    /*@ManyToMany
    @JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> members;*/

    @ManyToMany
    @JoinTable(name = "calendar_event", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"))
    private Set<Calendar> calendars;

    @OneToMany(mappedBy = "event")
    private Set<EventUser> eventUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getLastModificationDateTime() {
        return lastModificationDateTime;
    }

    public void setLastModificationDateTime(LocalDateTime lastModificationDateTime) {
        this.lastModificationDateTime = lastModificationDateTime;
    }

    public int getNotifyDuration() {
        return notifyDuration;
    }

    public void setNotifyDuration(int notifyDuration) {
        this.notifyDuration = notifyDuration;
    }

    /*public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }*/

    public Set<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(Set<Calendar> calendars) {
        this.calendars = calendars;
    }

    public Set<EventUser> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(Set<EventUser> eventUsers) {
        this.eventUsers = eventUsers;
    }
}
