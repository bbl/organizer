package hello.repository;

import org.springframework.data.repository.CrudRepository;

import hello.model.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Long> {
}
