package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Skill;
import com.atech.mongodbapp.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> findAll() {

        List<Skill> skillList = new ArrayList<>();
        skillRepository.findAll().forEach(skillList::add);
        return skillList;
    }

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }
}
