package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.entity.Holiday;
import com.atech.mongodbapp.service.EmployeeService;
import com.atech.mongodbapp.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HolidayController {

    private final HolidayService holidayService;
    private final EmployeeService employeeService;

    public HolidayController(HolidayService holidayService, EmployeeService employeeService) {
        this.holidayService = holidayService;
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{empId}/holiday/add")
    public String addNewHoliday(@PathVariable String empId, Model model){

        model.addAttribute("employee", employeeService.findById(empId));
        model.addAttribute("holiday", new Holiday());
        return "holiday/addHoliday";
    }

    @PostMapping("/employee/{empId}/holiday/save")
    public String saveOrUpdate(@PathVariable String empId, @ModelAttribute("holiday")Holiday holiday){

        Employee employee = employeeService.findById(empId);
        holiday.setEmployee(employee);
        holidayService.save(holiday);
        employee.getHolidaysList().add(holiday);
        employeeService.save(employee, empId);
        return "redirect:/employees/"+ empId + "/details";
    }

    @GetMapping("/employee/{empId}/holiday/{holidayId}/delete")
    public String deleteHoliday(@PathVariable String empId,
                                @PathVariable String holidayId){

        Employee employee = employeeService.findById(empId);
        Holiday holiday = holidayService.findHolidayById(holidayId);
        employee.getHolidaysList().remove(holiday);
        holidayService.delete(holiday);
        employeeService.save(employee, empId);

        return "redirect:/employees/" + empId + "/details";
    }
}
