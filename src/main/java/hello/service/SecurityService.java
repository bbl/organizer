package hello.service;

import hello.model.User;

public interface SecurityService {
    String findLoggedInUsername();

    boolean autologin(String username, String password);

    User getLogginedUser();
}