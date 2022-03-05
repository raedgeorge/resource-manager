package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.Skill;
import com.atech.mongodbapp.service.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skill")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String addSkills(Model model){

        model.addAttribute("skill", new Skill());
        model.addAttribute("saved", service.findAll());

        return "skill/skillAddForm";
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute("skill") Skill skill){

        service.save(skill);
        return "redirect:/skill/add";
    }
}
