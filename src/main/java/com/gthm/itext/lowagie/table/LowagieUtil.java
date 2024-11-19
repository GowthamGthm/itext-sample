package com.gthm.itext.lowagie.table;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.IOException;
import java.util.Optional;

public class LowagieUtil {

    static Font FONT_SIZE_9 = new Font(Font.FontFamily.HELVETICA, 9);

    static Font FONT_SIZE_8 = new Font(Font.FontFamily.HELVETICA, 8);

    static Font HELVETICA_SIZE_8_BOLD = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);


    public static Paragraph createParagraph(String text, Font font,
                                            Integer alignment,
                                     LowagieMargin margin) {
        Paragraph paragraph = new Paragraph(Optional.ofNullable(text).orElse(""));

        if(font != null) {
            paragraph.setFont(font);
        }

        if(alignment != null) {
            paragraph.setAlignment(alignment);
        }

        if(margin != null) {
            if(margin.getLeft() != null && margin.getLeft() > 0) {
                paragraph.setSpacingBefore(margin.getLeft());
            }

            if (margin.getBottom() != null && margin.getBottom() > 0) {
                paragraph.setSpacingAfter(margin.getBottom());
            }

            if(margin.getRight() != null && margin.getRight() > 0) {
                paragraph.setIndentationRight(margin.getRight());
            }

            if(margin.getLeft() != null && margin.getLeft() > 0) {
                paragraph.setIndentationLeft(margin.getLeft());
            }
        }

        return paragraph;
    }


    public static PdfPCell getPdfpCellForFromTO(String content) {
        PdfPCell  cell = new PdfPCell (new Phrase(Optional.ofNullable(content).orElse("") , FONT_SIZE_9));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell getPdfpCellForFromTO(String content , Font font) {
        PdfPCell  cell = new PdfPCell (new Phrase(Optional.ofNullable(content).orElse("") , font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }


    public static PdfPTable removeBorder(PdfPTable table) {
        // Loop through the outer table cells and check for inner tables
        for (int i = 0; i < table.getRows().size(); i++) {
            for (int j = 0; j < table.getNumberOfColumns(); j++) {
                PdfPCell cell = table.getRow(i).getCells()[j];
                if (cell != null && cell.getCompositeElements() != null) {
                    for (Element element : cell.getCompositeElements()) {
                        if (element instanceof PdfPTable) {
                            PdfPTable inner = (PdfPTable) element;
                            setTableBordersToNoBorder(inner);
                        }
                    }
                }

                // Set the outer table cell borders to no border
                if(cell != null) {
                    cell.setBorder(Rectangle.NO_BORDER);
                }
            }
        }
        return table;
    }




    private static void setTableBordersToNoBorder(PdfPTable table) {
        for (int i = 0; i < table.getRows().size(); i++) {
            for (int j = 0; j < table.getNumberOfColumns(); j++) {
                PdfPCell cell = table.getRow(i).getCells()[j];
                if (cell != null) {
                    cell.setBorder(Rectangle.NO_BORDER);
                }
            }
        }
    }

    public static PdfPCell getTableHeader(String name , int row , int col) throws IOException {

        Font font = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
        Paragraph paragraph = LowagieUtil.createParagraph(name, font, Element.ALIGN_CENTER, null);

        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.addElement(paragraph);
        pdfPCell.setRowspan(row);
        pdfPCell.setColspan(col);

        return pdfPCell;

    }

    public static void createRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String weight, String packPrice, String amountInUSD,
                                                 String vndrPackType, String netVNDRPack, String netTotal,
                                                 String grossVndrPack, String grossTotal, PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,6 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , totalUnit); // total unit
        row[6] = addCellForInvoice(1,4 , weight); // weight
        row[7] = addCellForInvoice(2,1 , packPrice); // pack price
        row[8] = addCellForInvoice(2,2 , amountInUSD); // amount in USD
        row[9] = addCellForInvoice(1,1 , vndrPackType);  // VNDR PACK type
        row[10] = addCellForInvoice(1,1, netVNDRPack); // net vndr pack
        row[11] = addCellForInvoice(1,1 , netTotal); // net total
        row[12] = addCellForInvoice(1,1 , grossVndrPack); // gross vndr pack
        row[13] = addCellForInvoice(1,1 , grossTotal); // gross total

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    public static void createExpandingRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                          String vndrPack,  String totalUnit,
                                                          String weight, String packPrice, String amountInUSD,
                                                          String netVNDRPack, PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,9 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[4] = addCellForInvoice(2,1 , totalUnit); // total unit
        row[5] = addCellForInvoice(1,0 , weight); // weight
        row[6] = addCellForInvoice(2,1 , packPrice); // pack price
        row[7] = addCellForInvoice(2,2 , amountInUSD); // amount in USD
        row[8] = addCellForInvoice(1,1, netVNDRPack); // net vndr pack

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    public static void createRowForItemInInvoiceTable(String shippingMarks, String assortment,
                                                       String vndrPack, String totalVNDRPack, String totalUnit,
                                                       String packPrice, String amountInUSD,
                                                       String netVNDRPack, String netTotal,
                                                       PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,7 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[3] = addCellForInvoice(2,1 , totalVNDRPack); // total VNDR packs
        row[4] = addCellForInvoice(2,1 , totalUnit); // total unit
        row[5] = addCellForInvoice(2,1, netVNDRPack); // net vndr pack
        row[6] = addCellForInvoice(2,1 , netTotal); // net total
        row[7] = addCellForInvoice(2,1 , ""); // empty field
        row[8] = addCellForInvoice(2,1 , ""); // empty field
        row[9] = addCellForInvoice(2,1 , packPrice); // pack price
        row[10] = addCellForInvoice(2,1 , amountInUSD); // amount in USD

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }


    public static void createRowForInvoiceTotal(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String packPrice, String amountInUSD,
                                                 String netVNDRPack, String netTotal,
                                                 String grossTotal, PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoiceTotal(2,6 , assortment); // assortment
        row[2] = addCellForInvoiceTotalWithBorder(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoiceTotalWithBorder(2,1 , vndrPack); // VNDR pack
        row[4] = addCellForInvoiceTotalWithBorder(2,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoiceTotalWithBorder(2,1 , totalUnit); // total unit
        row[6] = addCellForInvoiceTotalWithBorder(2,1, "");
        row[7] = addCellForInvoiceTotalWithBorder(2,1 , netVNDRPack); // net vndr pack
        row[8] = addCellForInvoiceTotalWithBorder(2,1 , grossTotal); // gross total
        row[9] = addCellForInvoiceTotalWithBorder(2,1 , packPrice); // pack price
        row[10] = addCellForInvoiceTotalWithBorder(2,1 , amountInUSD); // amount in USD
        row[11] = addCellForInvoiceTotalWithBorder(2,1 , netTotal); // net total

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }



    public static PdfPCell addCellForInvoice(int rowSpan, int colSpan, String content) {

        Paragraph paragraph = LowagieUtil.createParagraph(content, FONT_SIZE_8, null,
                LowagieMargin.builder().left(5f).build());

        PdfPCell cell = new PdfPCell();
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        cell.addElement(paragraph);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);

        return cell;
    }

    private static PdfPCell addCellForInvoiceTotalWithBorder(int rowSpan, int colSpan, String content) {

        Paragraph paragraph = LowagieUtil.createParagraph(content, HELVETICA_SIZE_8_BOLD, null,
                LowagieMargin.builder().left(5f).build());


        PdfPCell cell = new PdfPCell();
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        cell.addElement(paragraph);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPCell addCellForInvoiceTotal(int rowSpan, int colSpan, String content) {
        Paragraph paragraph = LowagieUtil.createParagraph(content, FONT_SIZE_8, null,
                LowagieMargin.builder().left(5f).build());


        PdfPCell cell = new PdfPCell();
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        cell.addElement(paragraph);
        cell.setVerticalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }


    public static void addUnderLine(Document document) throws DocumentException {

        LineSeparator line = new LineSeparator();
        line.setLineWidth(1f);
        line.setLineColor(BaseColor.BLACK);

        Chunk lineChunk = new Chunk(line);
        lineChunk.setLineHeight(1f);

        Paragraph paragraph = new Paragraph(lineChunk);
        paragraph.setIndentationLeft(20f);
        paragraph.setIndentationRight(20f);
        paragraph.setSpacingBefore(1f);

        document.add(paragraph);

    }


}