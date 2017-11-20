package hello.controller;

import hello.dto.calendar.CalendarDto;
import hello.dto.calendar.UpdateCalendarDto;
import hello.model.User;
import hello.service.CalendarService;
import hello.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService calendarService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String calendar(Model model) {
        User logginedUser = securityService.getLogginedUser();
        if (logginedUser != null) {
            model.addAttribute("calendars", calendarService.getAllCalendar(logginedUser.getId()));
        }
        return "calendar";
    }

    @RequestMapping(value = "/calendar/add", method = RequestMethod.POST,
            consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewCalendar(CalendarDto calendarDto) {
        calendarService.addCalendar(calendarDto);
        return "redirect:/calendar";
    }

    @RequestMapping(value = "/calendar/update", method = RequestMethod.POST,
            consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateCalendar(@RequestBody UpdateCalendarDto updateCalendarDto) {
        calendarService.updateCalendar(updateCalendarDto);
        return "redirect:/calendar";
    }

    @RequestMapping(value = "/calendar/delete", method = RequestMethod.POST,
            consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteCalendar(@RequestParam("id") Long id) {
        calendarService.deleteCalendar(id);
        return "redirect:/calendar";
    }

    /**
     * REST Controller methods. It's need for ajax requests.
     * Data input - data output (not a view).
     */

    @RequestMapping(value = "/rest/calendar/all", method = RequestMethod.POST)
    @ResponseBody
    public List<CalendarDto> getAllCalendars() {
        User logginedUser = securityService.getLogginedUser();
        if (logginedUser != null) {
            return calendarService.getAllCalendar(logginedUser.getId());
        }
        return Collections.emptyList();
    }

    @RequestMapping(value = "/rest/calendar", method = RequestMethod.POST)
    @ResponseBody
    public CalendarDto getCalendar(@RequestParam("id") Long id) {
        return calendarService.getCalendarById(id);
    }


}