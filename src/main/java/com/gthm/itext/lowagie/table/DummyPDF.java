package com.gthm.itext.lowagie.table;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class DummyPDF {

    static String folder = "C:\\Users\\anon\\Pictures\\output-invoide\\";
    static String path = folder + "InvoiceExample.pdf";


    public static void main(String[] args) {
        try {
            // Document setup
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));

            // Open the document for writing
            document.open();

            // Title of the Invoice
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Line separator
            document.add(new Chunk(Chunk.NEWLINE));

            // Invoice Information (e.g., company info)
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Paragraph companyInfo = new Paragraph("Company Name: XYZ Corporation\n" +
                    "Address: 1234 Elm Street, City, Country\n" +
                    "Phone: (123) 456-7890", bodyFont);
            companyInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(companyInfo);

            // Customer Info
            document.add(new Chunk(Chunk.NEWLINE));
            Paragraph customerInfo = new Paragraph("Bill To:\n" +
                    "Customer Name: John Doe\n" +
                    "Address: 5678 Oak Avenue, City, Country\n" +
                    "Phone: (987) 654-3210", bodyFont);
            customerInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(customerInfo);

            // Table for the items purchased
            document.add(new Chunk(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(4); // 4 columns

            // Table headers
            table.addCell("Item");
            table.addCell("Description");
            table.addCell("Unit Price");
            table.addCell("Quantity");

            // Table data (example items)
            table.addCell("Item 1");
            table.addCell("Product description goes here.");
            table.addCell("$10.00");
            table.addCell("2");

            table.addCell("Item 2");
            table.addCell("Another product description.");
            table.addCell("$15.00");
            table.addCell("1");

            table.addCell("Item 3");
            table.addCell("Yet another product.");
            table.addCell("$7.00");
            table.addCell("5");

            // Add table to document
            document.add(table);

            // Total Calculation (Simple example, just sum up prices)
            document.add(new Chunk(Chunk.NEWLINE));
            Paragraph total = new Paragraph("Total: $85.00", bodyFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            // Closing the document
            document.close();

            System.out.println("Invoice PDF created successfully.");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}