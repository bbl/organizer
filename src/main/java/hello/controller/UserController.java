package hello.controller;

import hello.model.User;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository users;

    @RequestMapping(path="/all")
    public @ResponseBody List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        users.findAll().forEach(result::add);
        return result;
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam String login, @RequestParam String password)
    {
        users.save(new User(login, password));
        return "Saved!";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id)
    {
        users.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id)
    {
        return users.findOne(id);
    }
}
