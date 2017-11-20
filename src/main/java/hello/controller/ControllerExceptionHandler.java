package hello.controller;

import hello.dto.ErrorInfo;
import hello.exception.HttpUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpUnauthorizedException.class)
    @ResponseBody
    ErrorInfo handleHttpUnauthorizedException(HttpUnauthorizedException ex) {
        return new ErrorInfo(ex.getReturnCode(), ex.getReturnMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    ModelAndView handleUsernameNotFoundException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("returnCode", "400");
        modelAndView.addObject("returnMessage", ex.getMessage());
        return modelAndView;
    }
}
