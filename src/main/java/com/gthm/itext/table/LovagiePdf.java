//package com.gthm.itext.table;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//
//import java.io.FileOutputStream;
//
//public class LovagiePdf {
//
//    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
//    private static String PATH = directory + "invoice-lowa.pdf";
//
//    public static void main(String[] args) {
//
//        try {
//
//            // Document setup
//            Document document = new Document(PageSize.A4.rotate());
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
//            document.open();
//
//            // Title for the document
//            Paragraph title = new Paragraph("Invoice", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//
//            // Creating the table with 4 columns
//            PdfPTable table = new PdfPTable(4);
//            table.setWidthPercentage(100);
//
//            // Adding table header
//            table.addCell("Item Description");
//            table.addCell("Quantity");
//            table.addCell("Unit Price");
//            table.addCell("Total");
//
//            // Adding data with rowspan and colspan
//            PdfPCell cell;
//
//            // Row 1
//            table.addCell("Product 1");
//            table.addCell("2");
//            table.addCell("$10");
//            table.addCell("$20");
//
//            // Row 2 with rowspan
//            table.addCell("Product 2");
//            cell = new PdfPCell(new Phrase("Special offer"));
//            cell.setRowspan(2); // Rowspan for two rows
//            table.addCell(cell);
//            table.addCell("$5");
//            table.addCell("$25");
//
//            // Row 3 with rowspan
//            table.addCell("Product 3");
//            table.addCell("$15");
//            table.addCell("$30");
//
//            // Row 4 with colspan
//            table.addCell("Subtotal");
//            table.addCell(""); // Empty cell for visual spacing
//            table.addCell("Subtotal");
//            table.addCell("$75");
//
//            // Adding the table to the document
//            document.add(table);
//
//            // Closing the document
//            document.close();
//            System.out.println("PDF created successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
