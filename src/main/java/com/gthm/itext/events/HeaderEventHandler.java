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
        int pageNumber = docEvent.getDocument().getPageNumber(page);

        if (page != null) {
            updatePageHeaders(page, pageNumber, totalPageCount);
        }

    }

    public void updatePageHeaders(PdfPage page, int pageNumber, int totalPageCount) {
        if (page == null || pageNumber <= 1) {
            System.out.println("Pgae null or first page, hence skipped headers");
            return;
        }

        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle pageSize = page.getPageSize();

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{2, 2, 2, 2}))
                .useAllAvailableWidth();

        headerTable.addCell(createHeaderCell("SOLD TO: " + soldTo));
        headerTable.addCell(createHeaderCell("INVOICE #: " + invoiceNumber));
        headerTable.addCell(createHeaderCell("DATE: " + date));
        headerTable.addCell(createHeaderCell("PAGE " + pageNumber +
                " of " + (totalPageCount > 0 ? totalPageCount : "##")));

        float headerY = pageSize.getTop() - 30;
        float width = pageSize.getWidth();
        float height = 30;

        Canvas canvas = new Canvas(pdfCanvas, new Rectangle(0, headerY, width, height));
        canvas.add(headerTable);
        canvas.close();
    }

    private Cell createHeaderCell(String content) {

        return new Cell().add(new Paragraph(content))
                         .setBorder(Border.NO_BORDER)
                         .setTextAlignment(TextAlignment.LEFT)
                         .setBackgroundColor(ColorConstants.WHITE)
                         .setBold();

    }

}