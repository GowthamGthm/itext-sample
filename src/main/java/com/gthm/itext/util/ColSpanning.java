package com.gthm.itext.util;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;


public class ColSpanning {

    public static void main(String[] args) throws Exception {

        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\spanning_cells_example.pdf";

        // Create a PDF writer and document
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Create a table with 6 columns
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        // Adding some cells before the spanned cell
        table.addCell("Column 1");
        table.addCell("Column 2");

        // Create a cell that spans across 4 columns
        Cell spanningCell = new Cell(1, 4)
                .add(new Paragraph("PRODUCT DESCRIPTION: 15PC HARD ANODIZED COOKWARE SET"))
                .setBold()
                .setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.YELLOW);

        table.addCell(spanningCell);

        // Add some more rows to the table
        table.addCell("More Data 1");
        table.addCell("More Data 2");
        table.addCell("More Data 3");
        table.addCell("More Data 4");
        table.addCell("More Data 5");
        table.addCell("More Data 6");

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();
        System.out.println("PDF created: " + dest);
    }
}
