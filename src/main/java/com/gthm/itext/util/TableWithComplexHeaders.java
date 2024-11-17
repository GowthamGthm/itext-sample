package com.gthm.itext.util;

import com.gthm.itext.events.HeaderEventHandler;
import com.gthm.itext.model.Dummy;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableWithComplexHeaders {

    public static void main(String[] args) {

        String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
        String dest = directory + "table_with_headers.pdf";


        try {
            // Initialize PDF writer and document
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());
            Document document = new Document(pdf);


            HeaderEventHandler headerEventHandler = new HeaderEventHandler("WALMART INC",
                    "355670", "10/06/2024");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, headerEventHandler);

            // Create a table with 7 columns
            int NO_OF_COLUMNS = 7;
            Table table = new Table(NO_OF_COLUMNS);

            // Row 1 headers
            table.addHeaderCell(new Cell(2, 1).add(new Paragraph("Header 1 (2 rows)")));
            table.addHeaderCell(new Cell(1, 4).add(new Paragraph("Header 2 (4 columns)")));
            table.addHeaderCell(new Cell(2, 1).add(new Paragraph("Header 3 (2 rows)")));
            table.addHeaderCell(new Cell(2, 1).add(new Paragraph("Header 4 (2 rows)")));

            // Row 2 headers (sub-headers for Header 2)
            table.addHeaderCell(new Cell().add(new Paragraph("Subheader 2.1")));
            table.addHeaderCell(new Cell().add(new Paragraph("Subheader 2.2")));
            table.addHeaderCell(new Cell().add(new Paragraph("Subheader 2.3")));
            table.addHeaderCell(new Cell().add(new Paragraph("Subheader 2.4")));

            // Add sample data rows
            List<Dummy> list = Dummy.getInstances(100);
            List<Cell[]> rows = new ArrayList<>();

            for (int i = 1; i <= list.size(); i++) {
//                for (int j = 1; j <= table.getNumberOfColumns(); j++) {
                    Dummy dummy = list.get(i -1);
                    Cell[] row = new Cell[NO_OF_COLUMNS];
                    row[0] = new Cell().add(new Paragraph(dummy.getColumn1() + "-" + dummy.getColumn1()));
                    row[1] = new Cell().add(new Paragraph(dummy.getColumn2() + "-" + dummy.getColumn2()));
                    row[2] = new Cell().add(new Paragraph(dummy.getColumn3() + "-" + dummy.getColumn3()));
                    row[3] = new Cell().add(new Paragraph(dummy.getColumn4() + "-" + dummy.getColumn4()));
                    row[4] = new Cell().add(new Paragraph(dummy.getColumn5() + "-" + dummy.getColumn5()));
                    row[5] = new Cell().add(new Paragraph(dummy.getColumn6() + "-" + dummy.getColumn6()));
                    row[6] = new Cell().add(new Paragraph(dummy.getColumn7() + "-" + dummy.getColumn7()));
                    rows.add(row);
//                }
            }

            for(Cell[] row :rows) {
                for(Cell cell: row) {
                    table.addCell(cell);
                }
            }

            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            String outputFilePath =  directory + File.separator + "pdf-headers-rebuilt.pdf";
            updateHeaders(headerEventHandler, dest, outputFilePath);



            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateHeaders(HeaderEventHandler headerEventHandler, String sourcePdfPath , String targetPdfPath) throws IOException {

        PdfReader reader = new PdfReader(sourcePdfPath);
        PdfWriter finalWriter = new PdfWriter(targetPdfPath);
        PdfDocument updatedPdf = new PdfDocument(reader, finalWriter);

        int totalPageCount = updatedPdf.getNumberOfPages();

        // Update the header on each page
        for (int pageNumber = 1; pageNumber <= totalPageCount; pageNumber++) {
            PdfPage page = updatedPdf.getPage(pageNumber);
            PdfCanvas canvas = new PdfCanvas(page);
            headerEventHandler.updatePageHeaders(page, pageNumber, totalPageCount);
        }

        updatedPdf.close();

    }
}
