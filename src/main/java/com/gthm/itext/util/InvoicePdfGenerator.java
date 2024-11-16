package com.gthm.itext.util;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


import java.io.File;

public class InvoicePdfGenerator {
    public static void main(String[] args) {
        try {
//            String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\invoice_landscape.pdf";
            String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\invoice_portrait.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(com.itextpdf.kernel.geom.PageSize.A4);

            Document document = new Document(pdf);

            // Title and Header
            PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
            PdfFont fontNormal = PdfFontFactory.createFont("Helvetica");

            // Add company information
            Paragraph title = new Paragraph("MASTER GROUP GLOBAL CO., LIMITED")
                    .setFont(font)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("UNIT 2003, 20/F, ORIENT INTERNATIONAL TOWER, 1018 TAI NAN WEST STREET, CHEUNG SHA WAN\nKOWLOON, HONG KONG, CN")
                    .setFont(fontNormal)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("TEL:(852)2312 0409, FAX:(852)2312 0484")
                    .setFont(fontNormal)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("COMMERCIAL INVOICE AND PACKING LIST")
                    .setFont(font)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));

            // Table for main details
            float[] columnWidths = {2, 1, 2, 1, 2, 1, 2, 1};
            Table detailsTable = new Table(columnWidths);
            detailsTable.setWidth(UnitValue.createPercentValue(100));

            detailsTable.addCell(new Cell().add(new Paragraph("SOLD TO:")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("WALMART INC.\n508 SW 8TH STREET\nBENTONVILLE, AR\nUS 72716")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("INVOICE #")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("3555044")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("DATE")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("2024-10-06")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("COUNTRY OF CONSIGNEE:")).setFont(fontNormal).setBorder(Border.NO_BORDER));
            detailsTable.addCell(new Cell().add(new Paragraph("US")).setFont(fontNormal).setBorder(Border.NO_BORDER));

            // Add more cells as needed (add cells for each header as per the provided image)

            document.add(detailsTable);

            // Table for the detailed breakdown
            float[] itemColumnWidths = {2, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            Table itemTable = new Table(itemColumnWidths);
            itemTable.setWidth(UnitValue.createPercentValue(95));

            // Header row
            itemTable.addHeaderCell(new Cell().add(new Paragraph("SHIPPING MARKS, PO#, PO Type & Dept#").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("ASSORTMENT/ITEM #, COMMERCIAL BRAND & DETAILED DESCRIPTION AS PER PO").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("WHSE PACK (PCS)").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("VNDR PACK (PCS)").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("TOTAL VNDR PACKS").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("TOTAL UNITS").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("WEIGHT (KG)").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("PACK PRICE (USD)").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            itemTable.addHeaderCell(new Cell().add(new Paragraph("AMOUNT IN USD").setFont(font)).setBackgroundColor(ColorConstants.LIGHT_GRAY));

            // Add rows for item details
            itemTable.addCell(new Cell().add(new Paragraph("To US Case ID\nPO # 17415558835\nPO Type # 20\nDept # 63\nItem # 980272065\nSupplier STK # 15PCHA")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("ITEM # 980272065\nUPC # 193968069360\nCOUNTRY ORIGIN: CHINA\nPRODUCT DESCRIPTION: 15PC HARD ANODIZED COOKWARE SET\nADDITIONAL PRODUCT DESCRIPTION:\nVENDOR STK # 15PCHA\nCOMPONENT DETAILS:\nCOMMON NAME: 2QT COVERED SAUCE PAN")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("1")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("14")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("42")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("588")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("210.0000")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("1175.0508")).setFont(fontNormal));
            itemTable.addCell(new Cell().add(new Paragraph("49352.1336")).setFont(fontNormal));

            document.add(itemTable);

            document.close();
            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
