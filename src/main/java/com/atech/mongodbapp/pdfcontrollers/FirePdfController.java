package com.atech.mongodbapp.pdfcontrollers;

import com.atech.mongodbapp.entity.products.Fire;
import com.atech.mongodbapp.pdf.FirePdfGenerator;
import com.atech.mongodbapp.service.products.FireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FirePdfController {

    private final FireService fireService;

    public FirePdfController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("/fire/print/pdf")
    public void generatePdfReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy:HH:mm:ss");

        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=fire_report_"+ currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Fire> fireList = fireService.findAll();

        FirePdfGenerator generator = new FirePdfGenerator(fireList);
        generator.exportToPdf(response);

    }
}
