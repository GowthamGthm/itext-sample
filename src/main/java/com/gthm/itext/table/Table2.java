//package com.gthm.itext.table;
//
//import com.gthm.itext.util.TableUtil;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.properties.TextAlignment;
//import com.itextpdf.layout.properties.VerticalAlignment;
//
//public class Table2 {
//
//    public static void main(String[] args) throws Exception {
//
//        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\table2.pdf";
//
//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
//        Document document = new Document(pdfDoc);
//
//        // Table with 14 columns based on provided structure
////        float[] columnWidths = {6, 10, 3, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 4};
//        float[] columnWidths = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
//        Table table = new Table(columnWidths);
//
//        // Title Row
//        table.addCell(new Cell(1, 14)
//                .add(TableUtil.getTableHeader("Invoice Packing List"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setBold());
//
//        // Row 2: Header Row (Main Headers)
//        table.addCell(new Cell(2, 2)
//                .add(TableUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE));
//
//        table.addCell(new Cell(2, 3)
//                .add(TableUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION " +
//                        "\n AS PER PO"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE));
//
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("WHSE PACK \n (PCS)"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("VNDR PACK \n (PCS)"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("VNDR PACK \n TYPE"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE));
//
//        table.addCell(new Cell(1, 4)
//                .add(TableUtil.getTableHeader("Weight (KG)"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("Pack Price \n (USD)"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE));
//
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("Amount in \n USD"))
//                .setTextAlignment(TextAlignment.CENTER)
//                .setVerticalAlignment(VerticalAlignment.MIDDLE));
//
//        // Row 3: Sub Headers for Weights
//        table.addCell(new Cell(2, 1)
//                .add(TableUtil.getTableHeader("Net VNDR \n PACK"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(1, 1)
//                .add(TableUtil.getTableHeader("Net Total"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(1, 1)
//                .add(TableUtil.getTableHeader("Gross Total"))
//                .setTextAlignment(TextAlignment.CENTER));
//
//        // Row 3: Sub Headers for WHSE PACK, VNDR PACK
//        table.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("WHSE PACK"))
//                                    .setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("Total Units"))
//                                    .setTextAlignment(TextAlignment.CENTER));
//
//        table.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("VNDR PACK"))
//                                    .setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("Total VNDR PACKS"))
//                                    .setTextAlignment(TextAlignment.CENTER));
//
//        // Populate full PDF structure
//        document.add(table);
//        document.close();
//
//        System.out.println("PDF Table Generated Successfully!");
//    }
//}
//
