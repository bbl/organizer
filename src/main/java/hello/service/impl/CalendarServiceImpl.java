package hello.service.impl;

import hello.converter.CalendarMapper;
import hello.dto.calendar.CalendarDto;
import hello.dto.calendar.UpdateCalendarDto;
import hello.model.Calendar;
import hello.model.User;
import hello.repository.CalendarRepository;
import hello.service.CalendarService;
import hello.service.SecurityService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private CalendarMapper calendarMapper;

    @Override
    public void addCalendar(CalendarDto calendarDto) {
        //TODO validation if necessary
        User user = userService.findByLogin(securityService.findLoggedInUsername());
        Calendar calendar = toCalendar(calendarDto);
        calendar.setOwnerId(user.getId());
        calendarRepository.save(calendar);
    }

    @Override
    public List<CalendarDto> getAllCalendar(Long id) {
        return calendarMapper.toCalendarDtoList(calendarRepository.findAllByOwnerId(id));
    }

    @Override
    public CalendarDto getCalendarById(Long id) {
        return calendarMapper.toCalendarDto(calendarRepository.findOne(id));
    }

    @Override
    public void updateCalendar(UpdateCalendarDto input) {
        //TODO validation if necessary
        Calendar calendar = calendarRepository.findOne(input.getId());
        calendar.setName(input.getName());
        calendar.setDescription(input.getDescription());
        calendar.setLastModificationDate(LocalDateTime.now());
        calendarRepository.save(calendar);
    }

    @Override
    public void deleteCalendar(Long id) {
        calendarRepository.delete(id);
    }

    private Calendar toCalendar(CalendarDto calendarDto) {
        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());
        calendar.setPrivate(calendarDto.getPrivate());
        calendar.setCreationDate(LocalDateTime.now());
        return calendar;
    }
}
