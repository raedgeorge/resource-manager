package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.service.EmployeeService;
import com.atech.mongodbapp.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final HolidayService holidayService;

    public EmployeeController(EmployeeService employeeService, HolidayService holidayService) {
        this.employeeService = employeeService;
        this.holidayService = holidayService;
    }

    @GetMapping("/list")
    public String employeeList(Model model){

        model.addAttribute("employees", employeeService.findAll());
        return "employees/employees-list";
    }

    @GetMapping("/new")
    public String initAddForm(Model model){

        model.addAttribute("employee", new Employee());
        return "employees/new-Employee-Form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(
            @RequestParam("id") String id,
            @Valid @ModelAttribute("employee")Employee employee, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            return "employees/new-Employee-Form";
        }

        employeeService.save(employee, id);
        return "redirect:/employees/list";
    }

    @GetMapping("/{id}/update")
    public String initUpdateForm(
            Model model,
            @PathVariable String id){

        model.addAttribute("employee", employeeService.findById(id));
        return "employees/new-Employee-Form";
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable String id){

        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

    @GetMapping("/{id}/details")
    public String showDetails(Model model, @PathVariable String id){

        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("holidays", employee.getHolidaysList() );

        return "employees/details";
    }
}
