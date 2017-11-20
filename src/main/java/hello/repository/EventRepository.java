package hello.repository;

import hello.model.Event;
import hello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByCreatorIdAndEventDateTimeBetween(Long creatorId, LocalDateTime start, LocalDateTime end);

    List<Event> findByCreatorId(Long creatorId);

    /*@Query("SELECT e FROM Event e " +
            "INNER JOIN EventUser eu ON e.id = eu.event.id " +
            "INNER JOIN User u ON eu.user.id = u.id " +
            "INNER JOIN calendar_event ce ON ce.event_id = e.id " +
            "INNER JOIN calendars c ON ce.calendar_id = c.id " +
            "WHERE c.id = :calendarId AND u.id = :userId " +
            "   AND e.eventDateTime BETWEEN :startDate AND :endDate")
    List<Event> findAllByCalendarIdAndByCreatorIdAndDateInterval(@Param("calendarId") Long calendarId,
                                                                 @Param("userId") Long userId,
                                                                 @Param("startDate") LocalDateTime start,
                                                                 @Param("endDate") LocalDateTime end);*/

    List<Event> findAllByCalendarsContainingAndCreatorIdAndEventDateTimeBetween(Long calendarId,
                                                                         Long userId,
                                                                         LocalDateTime start,
                                                                         LocalDateTime end);
}
