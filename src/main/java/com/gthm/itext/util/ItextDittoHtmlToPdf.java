package com.gthm.itext.util;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


public class ItextDittoHtmlToPdf {

    public static void main(String[] args) {

        String pdfPath = "C:\\Users\\anon\\Pictures\\output-invoide\\invoice_gen_ditto_portrait.pdf";

        try (PdfWriter writer = new PdfWriter(pdfPath);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Title
            Paragraph title = new Paragraph("Invoice Form")
                    .setFontSize(22)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(title);

            // Form Fields (Grid Layout)
            Table formTable = new Table(UnitValue.createPercentArray(2))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);

            formTable.addCell(createLabelCell("First Name:"));
            formTable.addCell(createInputCell());

            formTable.addCell(createLabelCell("Last Name:"));
            formTable.addCell(createInputCell());

            formTable.addCell(createLabelCell("Email:"));
            formTable.addCell(createInputCell());

            formTable.addCell(createLabelCell("Address:"));
            formTable.addCell(createInputCell(2)); // Multi-line address

            document.add(formTable);

            // Table for Items
            Table itemTable = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2}))
                    .useAllAvailableWidth();

            // Table Headers
            addHeaderCell(itemTable, "Item");
            addHeaderCell(itemTable, "Price");
            addHeaderCell(itemTable, "Quantity");
            addHeaderCell(itemTable, "Amount");

            // Sample Data Rows
            for (int i = 1; i <= 100; i++) {
                itemTable.addCell(createCell("Item " + i));
                itemTable.addCell(createCell("$10.00"));
                itemTable.addCell(createCell("1"));
                itemTable.addCell(createCell("$10.00"));
            }

            document.add(itemTable);

            // Total Section
            Paragraph total = new Paragraph("Total: $30.00")
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(14)
                    .setMarginTop(10);
            document.add(total);

            System.out.println("Formatted PDF created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cell createLabelCell(String label) {
        return new Cell().add(new Paragraph(label)
                                 .setFontSize(12)
                                 .setBold()
                                 .setTextAlignment(TextAlignment.RIGHT)
                                 .setMarginRight(5))
                         .setBorder(null)
                         .setPadding(5);
    }

    private static Cell createInputCell() {
        return createInputCell(1);
    }

    private static Cell createInputCell(int rows) {
        return new Cell(rows, 1).add(new Paragraph("__________________________")
                                        .setFontSize(12)
                                        .setTextAlignment(TextAlignment.LEFT))
                                .setBorder(null)
                                .setPadding(5);
    }

    private static void addHeaderCell(Table table, String content) {
        table.addHeaderCell(new Cell().add(new Paragraph(content)
                .setBold()
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5)));
    }

    private static Cell createCell(String content) {
        return new Cell().add(new Paragraph(content)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5));
    }
}
