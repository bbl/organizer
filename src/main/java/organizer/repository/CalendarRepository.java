package organizer.repository;

import org.springframework.data.repository.CrudRepository;

import organizer.model.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Long> {
}
