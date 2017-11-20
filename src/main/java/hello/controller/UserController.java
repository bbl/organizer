package hello.controller;

import hello.dto.user.UserDto;
import hello.model.User;
import hello.service.SecurityService;
import hello.service.UserService;
import hello.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registration(UserDto userDto, BindingResult bindingResult, Model model) {
        //userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(new User(userDto.getLogin(), userDto.getPassword()));

        return "redirect:/calendar";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(UserDto userDto) {
        //todo validation
        boolean autologin = securityService.autologin(userDto.getLogin(), userDto.getPassword());
        if (!autologin){
            //todo login error message
        }
        return "redirect:/calendar";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String transToLogin(@RequestParam(required = false) String logout) {
        return "redirect:/calendar";
    }

    /*@ModelAttribute("userForm")
    private UserDto getUserDto(@RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String confirmedPassword){
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setConfirmedPassword(confirmedPassword);
        return userDto;
    }*/
}