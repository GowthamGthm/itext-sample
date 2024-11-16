package com.gthm.itext.util;

import com.gthm.itext.usefulUtils.TableUtil;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class TableWithComplexHeaders2 {

    public static void main(String[] args) {

        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\table_with_headers.pdf";


        try {
            // Initialize PDF writer and document
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());
            Document document = new Document(pdf);

            // Create a table with 7 columns
            float[] columnWidths = { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100));

            // Add header with a single span across the entire row
//            table.addHeaderCell(new Cell(1, 7).add(new Paragraph("Complex Table Header"))
//                                              .setBold());

            // Row 1 headers
            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("SHIPPING MARKS, PO#, PO Type\n" + "& Dept#\n")));
            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("ASSORTMENT/ITEM #, COMMERCIAL BRAND\n" + "& DETAILED DESCRIPTION AS PER PO\n")));
            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("WHSE\n" + "PACK\n" + "(PCS)")));
            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("VNDR\n" + "PACK\n" + "(PCS)")));
            table.addHeaderCell(new Cell(1, 2).add(TableUtil.getTableHeader("TOTAL VNDR\n" + "PACKS\n")));
            table.addHeaderCell(new Cell().add(TableUtil.getTableHeader("VNDR PACK TYPE")));

            table.addHeaderCell(new Cell(1, 2).add(TableUtil.getTableHeader("TOTAL\n" + "UNITS")));

            table.addHeaderCell(new Cell(1, 4).add(TableUtil.getTableHeader("WEIGHT (KG)")));
            table.addHeaderCell(new Cell(1,4).add(TableUtil.getTableHeader("NET VNDR\n" + "PACK")));
            table.addHeaderCell(new Cell(1,4).add(TableUtil.getTableHeader("NET TOTAL")));
            table.addHeaderCell(new Cell(1,4).add(TableUtil.getTableHeader("GROSS\n" + "VNDR PACK\n")));
            table.addHeaderCell(new Cell(1,4).add(TableUtil.getTableHeader("GROSS TOTAL\n")));

            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("PACK PRICE\n" + "(USD)\n")));
            table.addHeaderCell(new Cell(1, 1).add(TableUtil.getTableHeader("AMOUNT IN USD")));

            // Row 2 headers (sub-headers for Header 2)




            // Add sample data rows
            for (int i = 1; i <= 30; i++) {
                for (int j = 1; j <= 14; j++) {
                    table.addCell(new Cell().add(new Paragraph("Row " + i + " Col " + j)));
                }
            }

            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
