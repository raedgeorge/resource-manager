package com.atech.mongodbapp.pdf;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.entity.Holiday;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class EmployeeReportGenerator {

    private Employee employee;
    private List<Holiday> holidayList;

    public EmployeeReportGenerator(Employee employee, List<Holiday> holidayList) {
        this.employee = employee;
        this.holidayList = holidayList;
    }

    public void exportToPdf(HttpServletResponse response) throws Exception{

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph reportTitle = new Paragraph(employee.getFirstName().concat(" ").concat(employee.getLastName()));
        reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
        reportTitle.setSpacingAfter(15f);
        document.add(reportTitle);

        Paragraph dateTime = new Paragraph(String.valueOf(LocalDate.now()));
        dateTime.setAlignment(Paragraph.ALIGN_LEFT);
        dateTime.setSpacingAfter(30f);
        document.add(dateTime);

        Paragraph email = new Paragraph("Email Address : " + employee.getEmail());
        email.setAlignment(Paragraph.ALIGN_LEFT);
        email.setSpacingAfter(10f);

        Paragraph jobTitle = new Paragraph("Job Title : " + employee.getPosition());
        jobTitle.setAlignment(Paragraph.ALIGN_LEFT);
        jobTitle.setSpacingAfter(10f);

        Paragraph birthDate = new Paragraph("Birth Date : " + employee.getBirthDate());
        birthDate.setAlignment(Paragraph.ALIGN_LEFT);
        birthDate.setSpacingAfter(80f);

        byte[] image = new byte[employee.getImage().length];

        int i = 0;
        for (byte temp : employee.getImage()){
            image[i++] = temp;
        }

        document.add(email);
        document.add(jobTitle);
        document.add(birthDate);

        Image imageNew = Image.getInstance(image);
        imageNew.setAlignment(Paragraph.ALIGN_CENTER);

        imageNew.setAbsolutePosition(400, 600);
        document.add(imageNew);

        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(20f);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{4f, 4f, 4f});
        table.setSpacingAfter(10f);

        writeTableHeader(table);
        writeTableData(table);

        document.add(new Paragraph("Holiday List For " + employee.getFirstName()));
        document.add(table);

        document.addCreationDate();
        Paragraph reportEnd = new Paragraph("End Of Report");
        reportEnd.setAlignment(Paragraph.ALIGN_CENTER);
        reportEnd.setSpacingBefore(20f);
        document.add(reportEnd);

        document.close();

    }

    public void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("End Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Info", font));
        table.addCell(cell);

        }

        private void writeTableData(PdfPTable table){

            for (Holiday temp : holidayList ){
                table.addCell(String.valueOf(temp.getStartDate()));
                table.addCell(String.valueOf(temp.getEndDate()));
                table.addCell(temp.getInfo());
            }

        }
}
