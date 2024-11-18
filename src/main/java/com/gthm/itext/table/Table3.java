package com.gthm.itext.table;

import com.gthm.itext.util.TableUtil;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

public class Table3 {

    public static void main(String[] args) throws Exception {

        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\table30.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDoc);


        float[] columnWidths = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell(new Cell(2,2).add(TableUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,3).add(TableUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION " +
                     "\n AS PER PO")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,1).add(TableUtil.getTableHeader("WHSE PACK \n (PCS)")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,1).add(TableUtil.getTableHeader("VNDR PACK \n (PCS)")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        // 5th row
        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("TOTAL VNDR PACKS")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,0).add(TableUtil.getTableHeader("Total Units")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        // 7th row
        table.addCell(new Cell(0,4).add(TableUtil.getTableHeader("WEIGHT (KG)")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,0).add(TableUtil.getTableHeader("PACK PRICE \n (USD)")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(2,0).add(TableUtil.getTableHeader("AMOUNT IN \n USD")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

//         bottom ones
        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("VNDR PACK TYPE")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("NET VNDR \n PACK")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("NET TOTAL")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("GROSS VNDR PACK")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        table.addCell(new Cell(0,0).add(TableUtil.getTableHeader("GROSS TOTAL")))
             .setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);

        // Populate full PDF structure
        document.add(table);
        document.close();

        System.out.println("PDF Table Generated Successfully!");
    }
}

