package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Holiday;
import com.atech.mongodbapp.repository.HolidayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Holiday findHolidayById(String id) {
        return holidayRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Holiday holiday) {
        holidayRepository.delete(holiday);
    }

    @Override
    public Page<Holiday> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return holidayRepository.findAll(pageable);
    }
}
