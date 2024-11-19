package com.gthm.itext.lowagie.table;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.border.Border;
import javax.swing.text.StyleConstants;

public class HeaderEventHandler extends PdfPageEventHelper {

    private final String soldTo;
    private final String invoiceNumber;
    private final String date;
    private int totalPageCount = 0;

    public HeaderEventHandler(String soldTo, String invoiceNumber, String date) {
        this.soldTo = soldTo;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }





        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            super.onEndPage(writer, document);

            // Get the current page size
            Rectangle pageSize = document.getPageSize();
            int pageNumber = writer.getPageNumber();

            if (pageSize != null) {
                updatePageHeaders(writer, pageNumber, totalPageCount, pageSize);
            }
        }

    public void updatePageHeaders(PdfWriter writer, int pageNumber, int totalPageCount, Rectangle pageSize) {
        if (pageSize == null || pageNumber == 1) {
            System.out.println("Page size is null, skipping headers");
            return;
        }

        System.out.println("===================== processing the headers for the pageNumber: " + pageNumber);

        // Calculate margins
        float leftMargin = 20;
        float rightMargin = 20;
        float topMargin = pageSize.getTop() - 30;
        float width = pageSize.getWidth() - leftMargin - rightMargin;
        float height = 30;

        // Create the header table with 4 columns
        PdfPTable headerTable = new PdfPTable(4);

        // Set the table width to the calculated width
        headerTable.setTotalWidth(width);  // Set total width to ensure it's greater than zero
        headerTable.setLockedWidth(true);  // Lock the width to the calculated value

        headerTable.setWidthPercentage(100);
        headerTable.addCell(createHeaderCell("SOLD TO: " + soldTo, true));
        headerTable.addCell(createHeaderCell("INVOICE #: " + invoiceNumber, true));
        headerTable.addCell(createHeaderCell("DATE: " + date, true));

        String pageCount = totalPageCount > 0 ? totalPageCount + "" : "##";
        headerTable.addCell(createHeaderCell("PAGE " + pageNumber + " of " + pageCount, false));




        // Position the header table on the page
        headerTable.writeSelectedRows(0, -1, leftMargin, topMargin, writer.getDirectContentUnder());
    }


        private PdfPCell createHeaderCell(String content, boolean bold) {

        Font font = new Font(Font.FontFamily.HELVETICA, 10);
        if (bold) {
            font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        }

        Paragraph paragraph = new Paragraph(content);
        paragraph.setFont(font);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell();
        cell.addElement(paragraph);
        cell.setBorder(Rectangle.NO_BORDER);

       return cell;
    }




}