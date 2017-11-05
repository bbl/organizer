package hello.utils;

import hello.model.User;

public interface UserService {
    void save(User user);

    User findByLogin(String username);
}