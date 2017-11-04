package hello.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import hello.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}