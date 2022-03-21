package com.atech.mongodbapp.pdf;

import com.atech.mongodbapp.entity.products.Fire;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;

public class FirePdfGenerator {

    private final List<Fire> fireList;

    public FirePdfGenerator(List<Fire> fireList) {
        this.fireList = fireList;
    }

    public void exportToPdf(HttpServletResponse response) throws Exception{

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);

        Paragraph reportTitle = new Paragraph("Fire Products Summary", font);
        reportTitle.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph mainHeader = new Paragraph("User Generated Report", font);
        mainHeader.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(reportTitle);
        reportTitle.setSpacingAfter(2f);
        document.add(mainHeader);
        document.bottom(5f);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{5f,5f, 2f, 2f, 2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setStyle(Font.ITALIC);

        cell.setPhrase(new Phrase("Product Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Brand", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){

        for (Fire temp : fireList){
            table.addCell(temp.getId());
            table.addCell(temp.getDescription());
            table.addCell(temp.getTybeNumber());
            table.addCell(String.valueOf(temp.getBrands()));
            table.addCell(String.valueOf(temp.getQuantity()));
        }

    }
}
