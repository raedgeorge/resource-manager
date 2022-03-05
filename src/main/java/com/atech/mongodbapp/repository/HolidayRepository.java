package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Holiday;
import org.springframework.data.repository.CrudRepository;

public interface HolidayRepository extends CrudRepository<Holiday, String> {
}
