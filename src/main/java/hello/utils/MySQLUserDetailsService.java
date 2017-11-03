package hello.utils;

import hello.model.User;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MySQLUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            User client = users.findByLogin(username);
            loadedUser = new org.springframework.security.core.userdetails.User(
                    client.getLogin(), client.getPassword(),
                    DummyAuthority.getAuth());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }

    static class DummyAuthority implements GrantedAuthority
    {
        static Collection<GrantedAuthority> getAuth()
        {
            List<GrantedAuthority> res = new ArrayList<>(1);
            res.add(new DummyAuthority());
            return res;
        }

        @Override
        public String getAuthority() {
            return "USER";
        }
    }
}
