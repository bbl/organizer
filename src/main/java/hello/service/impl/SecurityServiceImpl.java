package hello.service.impl;

import hello.model.User;
import hello.service.SecurityService;
import hello.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }
        return null;
    }

    @Override
    public boolean autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (bCryptPasswordEncoder.encode(password).equals(userDetails.getPassword())){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.debug(String.format("Auto login %s successfully!", username));
                return true;
            }
        }
        return false;
    }

    @Override
    public User getLogginedUser() {
        String username = findLoggedInUsername();
        return userService.findByLogin(username);
    }
}