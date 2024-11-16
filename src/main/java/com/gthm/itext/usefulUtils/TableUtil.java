package com.gthm.itext.usefulUtils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.IOException;

public class TableUtil {

    public static Table removeBorder(Table table) {
        // Loop through the outer table cells and check for inner tables
        for (int i = 0; i < table.getNumberOfRows(); i++) {
            for (int j = 0; j < table.getNumberOfColumns(); j++) {
                Cell cell = table.getCell(i, j);
                if (cell != null && cell.getChildren() != null) {
                    for (com.itextpdf.layout.element.IElement element : cell.getChildren()) {
                        if (element instanceof Table) {
                            Table inner = (Table) element;
                            setTableBordersToNoBorder(inner);
                        }
                    }
                }
                // Set the outer table cell borders to no border
                cell.setBorder(Border.NO_BORDER);
            }
        }
        return table;
    }




    private static void setTableBordersToNoBorder(Table table) {
        for (int i = 0; i < table.getNumberOfRows(); i++) {
            for (int j = 0; j < table.getNumberOfColumns(); j++) {
                Cell cell = table.getCell(i, j);
                if (cell != null) {
                    cell.setBorder(Border.NO_BORDER);
                }
            }
        }
    }

    public static Paragraph getTableHeader(String name) throws IOException {

        PdfFont helveticaFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        return  new Paragraph(name)
                .setFont(helveticaFont)
                .setFontSize(7);

    }

}
