package com.gthm.itext.util;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;

public class OneRowMultipleParagraph {
    public static void main(String[] args) {
        try {
            String PATH = "C:\\Users\\anon\\Pictures\\output-invoide\\multiple.pdf";

            PdfWriter writer = new PdfWriter(new File(PATH));
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf, PageSize.A4);

            // Create outer table with 3 columns
            float[] columnWidthsOuter = {1, 3, 3};
            Table outerTable = new Table(columnWidthsOuter);
            outerTable.setWidth(UnitValue.createPercentValue(100));

            // Create inner table with 2 columns
            float[] columnWidthsInner = {1, 1};

            Table innerTable1 = new Table(columnWidthsInner);
            innerTable1.setWidth(UnitValue.createPercentValue(100));

            // Add rows to inner table
            innerTable1.addCell("Dateeeeeee:");
            innerTable1.addCell("12-12-2024");
            innerTable1.addCell("City");
            innerTable1.addCell("Bangalore");

            Table innerTable2 = new Table(columnWidthsInner);
            innerTable2.setWidth(UnitValue.createPercentValue(100));

            // Add rows to inner table
            innerTable2.addCell("Invoiceeeeee#");
            innerTable2.addCell("1234");
            innerTable2.addCell("COuntry");
            innerTable2.addCell("CN");

            // Create a cell for the outer table and insert the inner table
            Cell cellWithTable1 = new Cell().add(innerTable1);
            cellWithTable1.setTextAlignment(TextAlignment.CENTER);

            Cell cellWithTable2 = new Cell().add(innerTable2);
            cellWithTable2.setTextAlignment(TextAlignment.CENTER);

            // Add cells to outer table
            outerTable.addCell("Outer Cell 1");
            outerTable.addCell(cellWithTable1);
            outerTable.addCell(cellWithTable2);

            // Add the outer table to the document
            document.add(outerTable);

            // Close the document
            document.close();





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
