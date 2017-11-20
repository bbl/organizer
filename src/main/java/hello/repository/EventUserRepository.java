package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.model.EventUser;

@Repository
public interface EventUserRepository extends JpaRepository<EventUser, Long> {
}
