package com.gthm.itext.events;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class HeaderEventHandler implements IEventHandler {

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
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        int pageNumber = docEvent.getDocument()
                                 .getPageNumber(page);

        if (page != null) {
            updatePageHeaders(page, pageNumber, totalPageCount);
        }

    }

    public void updatePageHeaders(PdfPage page, int pageNumber, int totalPageCount) {
        if (page == null || pageNumber <= 1) {
            System.out.println("Page null or first page, hence skipped headers");
            return;
        }

        System.out.println("===================== processing the headers for the pageNumber: " + pageNumber);

        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle pageSize = page.getPageSize();

        float leftMargin = 20;
        float rightMargin = 20;

        Table headerTable =
                new Table(UnitValue.createPercentArray(new float[]{1, 1, 1, 1})).useAllAvailableWidth();

        String pageCount = totalPageCount > 0 ? totalPageCount + "" : "##";

        headerTable.addCell(createHeaderCell("SOLD TO: " + soldTo, true));
        headerTable.addCell(createHeaderCell("INVOICE #: " + invoiceNumber, true));
        headerTable.addCell(createHeaderCell("DATE: " + date, true));
        headerTable.addCell(createHeaderCell("PAGE " + pageNumber + " of " + pageCount, false));

        float headerY = pageSize.getTop() - 30;
        float width = pageSize.getWidth() - leftMargin - rightMargin;
        float height = 30;

        // Adjust Rectangle for margins
        Canvas canvas = new Canvas(pdfCanvas, new Rectangle(leftMargin, headerY, width, height));
        canvas.add(headerTable);
        canvas.close();
    }

    private Cell createHeaderCell(String content, boolean bold) {
        Paragraph paragraph = new Paragraph(content).setFontSize(10)
                                                    .setTextAlignment(TextAlignment.CENTER);

        if (bold) {
            paragraph.setBold();
        }

        return new Cell().add(paragraph)
                         .setBorder(Border.NO_BORDER)
                         .setBackgroundColor(ColorConstants.WHITE);
    }

}