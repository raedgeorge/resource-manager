package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Holiday;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HolidayService {

    List<Holiday> findAll();

    Holiday save(Holiday holiday);

    Holiday findHolidayById(String id);

    void delete(Holiday holiday);

    Page<Holiday> findPaginated(int pageNo, int pageSize);
}
