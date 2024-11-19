package com.gthm.itext.events;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfHeaderWithPageNumber {

    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {

        try {
            PdfReader reader = new PdfReader(new FileInputStream(src));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            int totalPages = reader.getNumberOfPages();

            for (int i = 1; i <= totalPages; i++) {
                PdfContentByte overContent = stamper.getOverContent(i);
                addHeader(overContent, i, totalPages);
            }

            stamper.close();
            reader.close();
            System.out.println("Header added successfully!");

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void addHeader(PdfContentByte canvas, int pageNumber, int totalPages) {
        try {
            // Load the font
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, false);
            canvas.beginText();
            canvas.setFontAndSize(bf, 10);

            // Calculate the header position in landscape mode
            float pageWidth = PageSize.A4.getHeight(); // A4 Landscape width
            float yPosition = PageSize.A4.getWidth() - 20; // 20 units from top in landscape mode

            // Define column positions
            float x1 = 20;  // "Sold to:" position
            float x2 = pageWidth / 4;  // Invoice number position
            float x3 = pageWidth / 2;  // Date position
            float x4 = (3 * pageWidth) / 4;  // Page number position

            // Add header content
            canvas.setTextMatrix(x1, yPosition);
            canvas.showText("Sold to:");

            canvas.setTextMatrix(x2, yPosition);
            canvas.showText("Invoice No.: 12345");

            canvas.setTextMatrix(x3, yPosition);
            canvas.showText("Date: 2024-11-19");

            canvas.setTextMatrix(x4, yPosition);
            canvas.showText("PAGE " + pageNumber + " of " + totalPages);

            canvas.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}