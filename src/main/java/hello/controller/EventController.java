package hello.controller;

import hello.converter.EventMapper;
import hello.dto.RestResponse;
import hello.dto.event.CreateEventInput;
import hello.dto.event.EventDto;
import hello.dto.event.EventDtoListOutput;
import hello.dto.event.ShortEventDto;
import hello.dto.event.ShortEventListOutput;
import hello.service.EventService;
import hello.utils.RestResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventMapper eventMapper;

    /**
     * REST Controller methods. It's need for ajax requests.
     * Data input - data output (not a view).
     */

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addEvent(@RequestBody CreateEventInput input) {
        eventService.addEvent(input);
        return RestResponseUtil.withReturnCodeOk();
    }

    @RequestMapping(value = "/event/update", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateEvent(@RequestBody CreateEventInput input) {
        eventService.updateEvent(input);
        return RestResponseUtil.withReturnCodeOk();
    }

    @RequestMapping(value = "/event/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResponse deleteEvent(@RequestParam("id") Long id) {
        eventService.deleteEvent(id);
        return RestResponseUtil.withReturnCodeOk();
    }

    @RequestMapping(value = "/rest/event/{eventId}/response/{status}", method = RequestMethod.POST)
    public void sendEventStatusResponse(@PathVariable String eventId, @PathVariable String status) {
        eventService.sendResponse(eventId, status);
    }

    @RequestMapping(value = "/rest/event/get/monthly", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDtoListOutput> getMonthlyEvents(@RequestParam("currentDate") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnMonth(date);
        return RestResponseUtil.withReturnCodeOk(new EventDtoListOutput(eventDtoList));
    }

    @RequestMapping(value = "/rest/event/get/daily", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDtoListOutput> getDailyEvents(@RequestParam("currentDate") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnDay(date);
        return RestResponseUtil.withReturnCodeOk(new EventDtoListOutput(eventDtoList));
    }

    @RequestMapping(value = "/rest/event/get", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDto> getDailyEvents(@RequestParam("id") Long id) {
        EventDto eventInfo = eventService.getShortEventInfo(id);
        return RestResponseUtil.withReturnCodeOk(eventInfo);
    }

    /**
     * For API
     */

    @RequestMapping(value = "/rest/calendar/{id}/event/get/monthly", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDtoListOutput> getCalendarMonthlyEvents(@PathVariable("id") String calendarId,
                                                                     @RequestParam("currentDate") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnMonthAndByCalendarId(calendarId, date);
        return RestResponseUtil.withReturnCodeOk(new EventDtoListOutput(eventDtoList));
    }

    @RequestMapping(value = "/rest/calendar/{id}/event/get/daily", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDtoListOutput> getCalendarDailyEvents(@PathVariable("id") String calendarId,
                                                                   @RequestParam("currentDate") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnDayAndByCalendarId(calendarId, date);
        return RestResponseUtil.withReturnCodeOk(new EventDtoListOutput(eventDtoList));
    }

    @RequestMapping(value = "/rest/event/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<ShortEventDto> getCalendarEventInfo(@PathVariable("id") Long id) {
        EventDto eventInfo = eventService.getShortEventInfo(id);
        return RestResponseUtil.withReturnCodeOk(eventMapper.toShortEventDto(eventInfo));
    }

    @RequestMapping(value = "/rest/event/get/{id}/full", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<EventDto> getCalendarEventFullInfo(@PathVariable("id") Long id) {
        EventDto eventInfo = eventService.getShortEventInfo(id);
        return RestResponseUtil.withReturnCodeOk(eventInfo);
    }

    @RequestMapping(value = "/rest/calendar/{id}/events/date/{date}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<ShortEventListOutput> getCalendarEventsByCurrentDate(@PathVariable("id") String calendarId,
                                                                             @PathVariable("date") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnMonthAndByCalendarId(calendarId, date);
        List<ShortEventDto> shortEventDtoList = eventMapper.toShortEventDtoListFromEventDtoList(eventDtoList);
        return RestResponseUtil.withReturnCodeOk(new ShortEventListOutput(shortEventDtoList));
    }

    @RequestMapping(value = "/rest/calendar/{id}/events/date/{date}/full", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<EventDtoListOutput> getCalendarEventsByCurrentDateFull(@PathVariable("id") String calendarId,
                                                                               @PathVariable("date") String date) {
        List<EventDto> eventDtoList = eventService.getCurrentUserEventsOnDayAndByCalendarId(calendarId, date);
        return RestResponseUtil.withReturnCodeOk(new EventDtoListOutput(eventDtoList));
    }
}
