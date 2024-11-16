package com.gthm.itext.util;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


public class CommercialInvoicePDF {
    public static void main(String[] args) {
        // Output file path
        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\CommercialInvoicePDF.pdf";

        try (PdfWriter writer = new PdfWriter(dest); PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Title Section
            Paragraph title = new Paragraph("MASTER GROUP GLOBAL CO., LIMITED").setFontSize(18)
                                                                               .setBold()
                                                                               .setTextAlignment(TextAlignment.CENTER)
                                                                               .setMarginTop(10);
            document.add(title);

            Paragraph address = new Paragraph("UNIT 2003, 20/F, ORIENT INTERNATIONAL TOWER, 1018 TAI NAN " +
                    "WEST STREET, CHEUNG SHA WAN KOWLOON, HONG KONG, CN\n" + "TEL:(852)2312 0409, FAX:(852)" +
                    "2312 0484").setFontSize(12)
                                                                                                                                                                                                          .setTextAlignment(TextAlignment.CENTER)
                                                                                                                                                                                                          .setMarginBottom(20);
            document.add(address);

            // Subtitle Section
            Paragraph subtitle = new Paragraph("COMMERCIAL INVOICE AND PACKING LIST").setFontSize(15)
                                                                                     .setBold()
                                                                                     .setTextAlignment(TextAlignment.LEFT)
                                                                                     .setMarginLeft(280)
                                                                                     .setMarginBottom(10);
            document.add(subtitle);

            Paragraph pageInfo = new Paragraph("PAGE 1 OF 3").setFontSize(8)
                                                             .setTextAlignment(TextAlignment.RIGHT)
                                                             .setMarginBottom(10);
            document.add(pageInfo);

            // Sold To Section
            Paragraph soldTo = new Paragraph("SOLD TO: WALMART INC.\n508 SW 8TH STREET\nBENTONVILLE AR " +
                    "72716").setFontSize(9)
                                                                                                              .setTextAlignment(TextAlignment.LEFT)
                                                                                                              .setMarginBottom(20);
            document.add(soldTo);

            // Invoice Info
            Paragraph invoiceInfo = new Paragraph("INVOICE # 3555044    DATE: 2024-10-06\n" + "COUNTRY OF " +
                    "CONSIGNEE: US   VOYAGE/FLIGHT # 439W").setFontSize(9)
                                                                                                                                                .setTextAlignment(TextAlignment.LEFT)
                                                                                                                                                .setMarginBottom(20);
            document.add(invoiceInfo);

            // Shipment Details Section
            Table shipmentDetails =
                    new Table(UnitValue.createPercentArray(new float[]{3, 4, 2, 3, 2})).useAllAvailableWidth();
            shipmentDetails.addCell(createHeaderCell("VESSEL NAME:"));
            shipmentDetails.addCell(createCell("GSL LYDIA"));
            shipmentDetails.addCell(createHeaderCell("TRANSPORT MODE:"));
            shipmentDetails.addCell(createCell("OCEAN"));
            shipmentDetails.addCell(createCell("CN"));

            shipmentDetails.addCell(createHeaderCell("FROM:"));
            shipmentDetails.addCell(createCell("YANTIAN"));
            shipmentDetails.addCell(createHeaderCell("TO:"));
            shipmentDetails.addCell(createCell("CHARLESTON"));
            shipmentDetails.addCell(createCell("US"));
            document.add(shipmentDetails);

            // Table Section
            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 4, 1, 1, 2, 1, 2, 1, 2, 1,
                                                                             3})).useAllAvailableWidth();

            // Table Headers
            String[] headers = {"SHIPPING MARKS, PO#, PO Type & Dept#", "ASSORTMENT/ITEM #, COMMERCIAL " +
                    "BRAND & DETAILED DESCRIPTION AS PER PO", "WHSE PACK (PCS)", "VNDR PACK (PCS)", "TOTAL " +
                                        "VNDR PACKS", "TOTAL UNITS", "WEIGHT (KG)", "VNDR PACK TYPE", "NET " +
                                        "TOTAL", "GROSS TOTAL", "AMOUNT IN USD"};

            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setFontSize(7)
                                                                        .setBold())
                                              .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                                              .setTextAlignment(TextAlignment.CENTER));
            }

            // Table Data (Sample Data Filled In)
            String[][] data = {{"PO #7174558835\nPO Type #20\nDept #63", "ITEM #980272065\n15PC HARD " +
                    "ANODIZED COOKWARE SET", "14", "42", "588", "8820.00", "210.00", "Cardboard", "208.50",
                                "238.35", "49352.13"}, {"PO #7174558835\nPO Type #20\nDept #63", "ITEM " +
                    "#980272065\n10IN SAUTE PAN", "14", "42", "588", "8820.00", "210.00", "Cardboard", "208" +
                                                                ".50", "238.35", "49352.13"}};

            for (String[] row : data) {
                for (String cell : row) {
                    table.addCell(new Cell().add(new Paragraph(cell).setFontSize(8))
                                            .setTextAlignment(TextAlignment.LEFT));
                }
            }

            document.add(table);

            // Product Breakdown Section
            Paragraph productBreakdown = new Paragraph("PRODUCT DESCRIPTION: 15PC HARD ANODIZED COOKWARE " +
                    "SET").setFontSize(9)
                                                                                                              .setTextAlignment(TextAlignment.LEFT)
                                                                                                              .setMarginTop(20);
            document.add(productBreakdown);

            Paragraph breakdownDetails = new Paragraph("COMMON NAME: 2QT COVERED SAUCE PAN\n" + "STAINLESS " +
                    "STEEL - 20%\n" + "GLASS - 25%\n" + "ALUMINUM - 55%\n" + "VALUE: 7.8391").setFontSize(8)
                                                                                                                                                                                   .setTextAlignment(TextAlignment.LEFT)
                                                                                                                                                                                   .setMarginTop(10);
            document.add(breakdownDetails);

            // Footer Section
            Paragraph footer = new Paragraph("INVOICE TOTAL VALUE IN US DOLLARS: FORTY-NINE THOUSAND THREE " +
                    "HUNDRED AND FIFTY-TWO AND CENTS THIRTEEN").setFontSize(8)
                                                                                                                                                     .setTextAlignment(TextAlignment.CENTER)
                                                                                                                                                     .setMarginTop(20);
            document.add(footer);

            System.out.println("PDF created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cell createHeaderCell(String content) {
        return new Cell().add(new Paragraph(content).setFontSize(9)
                                                    .setBold())
                         .setTextAlignment(TextAlignment.LEFT)
                         .setBackgroundColor(ColorConstants.LIGHT_GRAY);
    }

    private static Cell createCell(String content) {
        return new Cell().add(new Paragraph(content).setFontSize(9))
                         .setTextAlignment(TextAlignment.LEFT);
    }

}