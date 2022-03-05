package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Holiday;
import com.atech.mongodbapp.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayServiceImpl implements  HolidayService{

    private final HolidayRepository holidayRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<Holiday> findAll() {

        List<Holiday> holidays = new ArrayList<>();
        holidayRepository.findAll().forEach(holidays::add);
        return holidays;
    }

    @Override
    public Holiday save(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    @Override
    public void delete(Holiday holiday) {
        holidayRepository.delete(holiday);
    }
}
