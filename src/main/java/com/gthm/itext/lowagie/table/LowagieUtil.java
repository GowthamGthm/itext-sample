package com.gthm.itext.lowagie.table;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.Optional;

public class LowagieUtil {

    static Font FONT_SIZE_9 = new Font(Font.FontFamily.HELVETICA, 9);


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

}