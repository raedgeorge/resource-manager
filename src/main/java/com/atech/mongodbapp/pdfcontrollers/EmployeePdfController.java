package com.atech.mongodbapp.pdfcontrollers;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.entity.Holiday;
import com.atech.mongodbapp.pdf.EmployeePdfGenerator;
import com.atech.mongodbapp.pdf.EmployeeReportGenerator;
import com.atech.mongodbapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeePdfController {

    private final EmployeeService employeeService;

    public EmployeePdfController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{empId}/print/pdf")
    public void employeePdf(@PathVariable String empId, HttpServletResponse response) throws Exception{

        response.setContentType("application/pdf");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss");
        String currentDate = format.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDate + ".pdf";
        response.setHeader(headerKey, headerValue);

        Employee employee = employeeService.findById(empId);

        List<Holiday> holidayList = employee.getHolidaysList();

        EmployeeReportGenerator generator = new EmployeeReportGenerator(employee, holidayList);
        generator.exportToPdf(response);

    }

    @GetMapping("/employees/print/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_report_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Employee> employeeList = employeeService.findAll();

        EmployeePdfGenerator generator = new EmployeePdfGenerator(employeeList);
        generator.exportPdf(response);
    }
}
