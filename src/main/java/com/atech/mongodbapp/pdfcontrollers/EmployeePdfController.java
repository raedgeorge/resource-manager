package com.atech.mongodbapp.pdfcontrollers;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.pdf.EmployeePdfGenerator;
import com.atech.mongodbapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/print/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=employees_report_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Employee> employeeList = employeeService.findAll();

        EmployeePdfGenerator generator = new EmployeePdfGenerator(employeeList);
        generator.exportPdf(response);
    }
}
