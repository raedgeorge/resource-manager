package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Holiday;

import java.util.List;

public interface HolidayService {

    List<Holiday> findAll();

    Holiday save(Holiday holiday);

    void delete(Holiday holiday);
}
