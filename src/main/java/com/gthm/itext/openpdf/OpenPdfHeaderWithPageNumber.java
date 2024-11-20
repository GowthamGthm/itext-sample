package com.gthm.itext.openpdf;


import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OpenPdfHeaderWithPageNumber {

    public static void manipulatePdf(String src, String dest,String soldTo, String invoiceNumber, String soldDate) throws IOException, DocumentException {

        try {
            PdfReader reader = new PdfReader(new FileInputStream(src));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            int totalPages = reader.getNumberOfPages();

            for (int i = 1; i <= totalPages; i++) {
                PdfContentByte overContent = stamper.getOverContent(i);

                if( i != 1) {
                    addHeader(overContent, i, totalPages , soldTo , invoiceNumber, soldDate);
                }
            }

            stamper.close();
            reader.close();
            System.out.println("Header added successfully!");

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void addHeader(PdfContentByte canvas, int pageNumber, int totalPages, String soldTo,
                                  String invoiceNumber, String soldDate) {
        try {
            // Load the font
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, false);
            canvas.beginText();
            canvas.setFontAndSize(bf, 10);

            // Calculate the header position in landscape mode
            float pageWidth = PageSize.A4.getHeight(); // A4 Landscape width
            float yPosition = PageSize.A4.getWidth() - 20; // 20 units from top in landscape mode

            float x1 = 20;  // "Sold to:" position
            float x2 = pageWidth / 4;  // Invoice number position
            float x3 = pageWidth / 2;  // Date position
            float x4 = (3 * pageWidth) / 4;  // Page number position

            // Add header content
            canvas.setTextMatrix(x1, yPosition);
            canvas.showText("SOLD TO: " + soldTo);

            canvas.setTextMatrix(x2, yPosition);
            canvas.showText("INVOICE # " + invoiceNumber);

            canvas.setTextMatrix(x3, yPosition);
            canvas.showText("DATE: " + soldDate);

            canvas.setTextMatrix(x4, yPosition);
            canvas.showText("PAGE " + pageNumber + " OF " + totalPages);

            canvas.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}