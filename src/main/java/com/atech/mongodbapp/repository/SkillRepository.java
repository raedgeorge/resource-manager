package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, String> {
}
