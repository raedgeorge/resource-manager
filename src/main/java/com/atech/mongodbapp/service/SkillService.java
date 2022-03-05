package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill save(Skill skill);
}
