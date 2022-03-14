package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HolidayRepository extends MongoRepository<Holiday, String> {
}
