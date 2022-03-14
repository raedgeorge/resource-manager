package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.products.CCTV;
import com.atech.mongodbapp.service.products.CCTVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cctv")
public class CCTVController {

    public static final int PAGE_SIZE = 5;
    private final CCTVService cctvService;

    public CCTVController(CCTVService cctvService) {
        this.cctvService = cctvService;
    }

    @GetMapping("/list")
    public String cctvList(Model model){

        return getPaginated(1, model);
//        model.addAttribute("cctvList", cctvService.findAll());
//        return "cctv/cctvList";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable int pageNo, Model model){

        Page<CCTV> page = cctvService.findPaginated(pageNo, PAGE_SIZE);
        List<CCTV> cctvList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("cctvList", cctvList);
        return "cctv/cctvList";

    }

    @GetMapping("/new")
    public String addNewItem(Model model){

        model.addAttribute("cctv", new CCTV());
        return "cctv/new-item-form";
    }

    @PostMapping("/saveItem")
    public String saveOrUpdate(@ModelAttribute("cctv") CCTV cctv, @RequestParam("id") String id){

        cctvService.save(cctv, id);
        return "redirect:/cctv/list";
    }

    @GetMapping("/{itemId}/update")
    public String updateItem(@PathVariable String itemId, Model model){

        model.addAttribute("cctv", cctvService.findById(itemId));
        return "cctv/new-item-form";
    }

    @GetMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable String itemId){

        cctvService.delete(cctvService.findById(itemId));
        return "redirect:/cctv/list";
    }

    @PostMapping("/search")
    public String searchItem(Model model, @ModelAttribute("name")String name){

        log.error("inside cctv controller search");
        model.addAttribute("cctvList", cctvService.searchByAnyString(name));

        return "cctv/cctvList";
    }

}
