package hello.repository;


import org.springframework.data.repository.CrudRepository;
import hello.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}