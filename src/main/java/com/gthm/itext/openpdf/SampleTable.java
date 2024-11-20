package com.gthm.itext.openpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SampleTable {

    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
    private static String PATH = directory + "open-table-1.pdf";

    public static void main(String[] args) throws FileNotFoundException {


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
        PdfDocument pdfDoc = new PdfDocument();
        pdfDoc.addWriter(writer);

        document.open();

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };


        PdfPTable invoiceTableHeader = new PdfPTable(columnWidths);
        invoiceTableHeader.setWidthPercentage(100);


        insertHeader(invoiceTableHeader);

        document.add(invoiceTableHeader);

        document.close();

    }

    private static void insertHeader(PdfPTable invoiceTableHeader) {
        PdfPCell a = getParagraph("A" , 2, 2 );
        invoiceTableHeader.addCell(a);

        PdfPCell b =getParagraph("2" , 2 ,4);
        invoiceTableHeader.addCell(b);

        PdfPCell c = new PdfPCell();
        c.setRowspan(2);
        c.setColspan(1);
        c.addElement(new Paragraph("3"));
        invoiceTableHeader.addCell(c);

        PdfPCell d = new PdfPCell();
        d.setRowspan(2);
        d.setColspan(1);
        d.addElement(new Paragraph("4"));
        invoiceTableHeader.addCell(d);

        PdfPCell e = new PdfPCell();
        e.setRowspan(2);
        e.setColspan(1);
        e.addElement(new Paragraph("5"));
        invoiceTableHeader.addCell(e);


        // 5th row
        PdfPCell f = new PdfPCell();
        f.setRowspan(1);
        f.setColspan(1);
        Paragraph fg = new Paragraph("6");
        f.addElement(fg);
        invoiceTableHeader.addCell(f);

        PdfPCell g = new PdfPCell();
        g.setRowspan(2);
        g.setColspan(1);


        invoiceTableHeader.addCell(g);


        // 7th row
        PdfPCell h = new PdfPCell();
        h.setRowspan(1);
        h.setColspan(4);
        h.addElement(new Paragraph("8"));
        invoiceTableHeader.addCell(h);

        PdfPCell i = new PdfPCell();
        i.setRowspan(2);
        i.setColspan(1);
        i.addElement(new Paragraph("9"));
        invoiceTableHeader.addCell(i);

        PdfPCell j = new PdfPCell();
        j.setRowspan(2);
        j.setColspan(1);
        j.addElement(new Paragraph("10"));
        invoiceTableHeader.addCell(j);


//         bottom ones
        PdfPCell k = new PdfPCell();
        k.setRowspan(1);
        k.setColspan(1);
        k.addElement(new Paragraph("11"));
        invoiceTableHeader.addCell(k);

        PdfPCell l = new PdfPCell();
        l.setRowspan(1);
        l.setColspan(1);
        l.addElement(new Paragraph("12"));
        invoiceTableHeader.addCell(l);

        PdfPCell m = new PdfPCell();
        m.setRowspan(1);
        m.setColspan(1);
        m.addElement(new Paragraph("13"));
        invoiceTableHeader.addCell(m);

        PdfPCell n = new PdfPCell();
        n.setRowspan(1);
        n.setColspan(1);
        n.addElement(new Paragraph("14"));
        invoiceTableHeader.addCell(n);
    }

    public static PdfPCell getParagraph(String content , int row, int col) {
        Paragraph paragraph = new Paragraph(content);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell();
        cell.setRowspan(row);
        cell.setColspan(col);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        return cell;
    }

}