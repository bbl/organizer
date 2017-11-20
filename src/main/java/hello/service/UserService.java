package hello.service;

import hello.model.User;

public interface UserService {
    void save(User user);

    User findByLogin(String username);

    void add(User user);

    void update(User user);

}