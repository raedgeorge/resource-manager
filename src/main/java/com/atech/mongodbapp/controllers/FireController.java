package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.products.Fire;
import com.atech.mongodbapp.service.products.FireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/fire")
public class FireController {

    public static final int PAGE_SIZE = 5;
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("/list")
    public String listAllItems(Model model){

        return getPaginated(1,"description","asc", model);

//        model.addAttribute("fireList", fireService.findAll());
//        return "fire/fireList";
    }

    @GetMapping("/new")
    public String addNewItem(Model model){

        model.addAttribute("fire", new Fire());
        return "fire/new-item-form";
    }

    @PostMapping("/saveItem")
    public String saveOrUpdate(@ModelAttribute("fire")Fire fire, @RequestParam("id") String id){

        fireService.save(fire, id);
        return "redirect:/fire/list";
    }

    @GetMapping("/{itemId}/delete")
    public String deleteFireItem(@PathVariable String itemId){

        fireService.delete(fireService.findById(itemId));
        return "redirect:/fire/list";
    }

    @GetMapping("/{itemId}/update")
    public String updateFireItem(@PathVariable String itemId, Model model){

        model.addAttribute("fire", fireService.findById(itemId));
        return "fire/new-item-form";
    }

    @GetMapping("/search")
    public String searchItem(@RequestParam("name")String str, Model model){

        List<Fire> fireList = new ArrayList<>();

        if (!str.trim().equals("")) {
            fireService.findAll()
                    .forEach(fire -> {
                        if (fire.getTybeNumber().toLowerCase().contains(str) ||
                            fire.getDescription().toLowerCase().contains(str)) {

                            log.error("inside if statement");
                            fireList.add(fire);
                        }
                    });
        }
        else {
            fireList.addAll(fireService.findAll());
        }

        model.addAttribute("fireList", fireList);

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", fireList.size() / PAGE_SIZE);
        model.addAttribute("totalElements", fireList.stream().count());

        return "fire/fireList";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable int pageNo,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDirection") String sortDirection,
                               Model model){

        Page<Fire> page = fireService.findPaginated(pageNo, PAGE_SIZE, sortField, sortDirection);
        List<Fire> fireList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("fireList", fireList);

        return "fire/fireList";
    }

}
