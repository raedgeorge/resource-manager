package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {
}
