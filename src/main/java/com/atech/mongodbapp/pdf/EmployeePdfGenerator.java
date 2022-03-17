package com.atech.mongodbapp.pdf;

import com.atech.mongodbapp.entity.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class EmployeePdfGenerator {

    private List<Employee> employeeList;

    public EmployeePdfGenerator(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Employee Id",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){

        for(Employee temp : employeeList ){
            table.addCell(temp.getId());
            table.addCell(temp.getFirstName());
            table.addCell(temp.getLastName());
            table.addCell(temp.getEmail());
        }
    }

    public void exportPdf(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.GRAY);

        Paragraph reportTitle = new Paragraph("ATech Employees", font);
        reportTitle.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph mainHeader = new Paragraph("User Generated Report", font);
        mainHeader.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(reportTitle);
        document.add(new Paragraph(String.valueOf(LocalDate.now()), font));
        document.add(mainHeader);

        document.addAuthor("Atech Automatic Report");
        document.bottom(5f);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3f, 3.5f, 3.0f, 4.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
