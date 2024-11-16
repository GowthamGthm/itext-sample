package com.gthm.itext.util;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


public class Generator2 {

    public static void main(String[] args) {

        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\invoice_gen2_landscape.pdf";

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate()); // Landscape layout
            Document document = new Document(pdf);

            // Page 1: Invoice Header
            addInvoiceHeader(document);
            document.add(new Paragraph("\n"));
            addShipmentDetails(document);
            document.add(new Paragraph("\n"));
            addTablePage1(document);

            // Page 2: Product Details
            pdf.addNewPage();
            addProductDetailsPage(document);

            // Page 3: Assembly & Final Details
            pdf.addNewPage();
            addAssemblyDetailsPage(document);

            document.close();
            System.out.println("PDF created successfully with three pages.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addInvoiceHeader(Document document) {
        document.add(new Paragraph("COMMERCIAL INVOICE AND PACKING LIST")
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER));

        Table headerTable = new Table(new float[]{1, 1});
        headerTable.setWidth(UnitValue.createPercentValue(95));

        headerTable.addCell(new Cell().add(new Paragraph("SOLD TO:\nWALMART INC.\n508 SW 8TH STREET\nBENTONVILLE, AR, US 72716"))
                                      .setBorder(null));

        headerTable.addCell(new Cell().add(new Paragraph("INVOICE # 3555044\nDATE: 2024-10-06\nCOUNTRY OF CONSIGNEE: US\nVOYAGE/FLIGHT #: 439W"))
                                      .setBorder(null));

        document.add(headerTable);
    }

    private static void addShipmentDetails(Document document) {
        Table shipmentTable = new Table(new float[]{1, 1, 1});
        shipmentTable.setWidth(UnitValue.createPercentValue(95));


        shipmentTable.addCell(new Cell().add(new Paragraph("VESSEL NAME:\nGSL LYDIA")).setBorder(null));
        shipmentTable.addCell(new Cell().add(new Paragraph("TRANSPORT MODE:\nOCEAN")).setBorder(null));
        shipmentTable.addCell(new Cell().add(new Paragraph("INCOTERMS:\nFOB NAMED PLACE: YANTIAN")).setBorder(null));

        document.add(shipmentTable);
    }

    private static void addTablePage1(Document document) {
        float[] columnWidths = {1, 3, 1, 1, 1, 1, 1};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(95));

        table.addHeaderCell(new Cell().add(new Paragraph("Item #").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Qty").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Weight (KG)").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Unit Price").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Total (USD)").setBold()));

        table.addCell(new Cell().add(new Paragraph("980272065")));
        table.addCell(new Cell().add(new Paragraph("15PC HARD ANODIZED COOKWARE SET")));
        table.addCell(new Cell().add(new Paragraph("14")));
        table.addCell(new Cell().add(new Paragraph("210.0000")));
        table.addCell(new Cell().add(new Paragraph("83.93")));
        table.addCell(new Cell().add(new Paragraph("49352.13")));

        document.add(table);
    }

    private static void addProductDetailsPage(Document document) {
        document.add(new Paragraph("Product Details")
                .setBold()
                .setFontSize(14)
                .setTextAlignment(TextAlignment.LEFT));

        float[] columnWidths = {2, 2, 2};
        Table detailsTable = new Table(columnWidths);
        detailsTable.setWidth(UnitValue.createPercentValue(95));

        detailsTable.addHeaderCell(new Cell().add(new Paragraph("Common Name").setBold()));
        detailsTable.addHeaderCell(new Cell().add(new Paragraph("Breakdown").setBold()));
        detailsTable.addHeaderCell(new Cell().add(new Paragraph("Value").setBold()));

        detailsTable.addCell(new Cell().add(new Paragraph("2QT Covered Sauce Pan")));
        detailsTable.addCell(new Cell().add(new Paragraph("Stainless Steel: 20%\nGlass: 25%\nAluminum: 55%")));
        detailsTable.addCell(new Cell().add(new Paragraph("7.8391")));

        detailsTable.addCell(new Cell().add(new Paragraph("3QT Covered Sauce Pan")));
        detailsTable.addCell(new Cell().add(new Paragraph("Stainless Steel: 20%\nGlass: 25%\nAluminum: 55%")));
        detailsTable.addCell(new Cell().add(new Paragraph("8.8043")));

        // Add more rows as needed based on the PDF content
        document.add(detailsTable);
    }

    private static void addAssemblyDetailsPage(Document document) {
        document.add(new Paragraph("Assembly Manufacturer Details")
                .setBold()
                .setFontSize(14)
                .setTextAlignment(TextAlignment.LEFT));

        document.add(new Paragraph("Name: GUANGDONG MASTER GROUP CO., LTD.\n" +
                "Address: NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300\n" +
                "Tel: 8618023382230"));

        document.add(new Paragraph("\nInvoice Total: $49,352.13")
                .setBold()
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT));
    }
}
