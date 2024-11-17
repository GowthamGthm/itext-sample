package com.gthm.itext.usefulUtils;

import com.gthm.itext.events.HeaderEventHandler;
import com.gthm.itext.model.dummy.InvoiceContents;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyOwnPDF {

    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
    private static String PATH = directory + "my-own.pdf";


    public static void main(String[] args) {

        try (PdfWriter writer = new PdfWriter(PATH);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {
            document.setMargins(36, 20, 36, 20);
            pdfDoc.setDefaultPageSize(PageSize.A4.rotate());

            HeaderEventHandler headerEventHandler = new HeaderEventHandler("WALMART INC",
                    "355670", "10/06/2024");
            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, headerEventHandler);


//             starting to construct the PDF
            generateAddress(document);
            generatePackingList(pdfDoc, document);
            generateOtherInformation(document);
            Table invoiceTable = generateInvoiceTableHeader(document);
//            document.add(invoiceTable);
            generateTheDataInInvoiceTable(document, invoiceTable);


            // close the document
            document.close();

//             start generating the page number
            String outputFilePath =  directory + File.separator + "my-own-page-num.pdf";
            updateHeaders(headerEventHandler, PATH, outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void generateTheDataInInvoiceTable(Document document, Table invoiceTable) {

        List<InvoiceContents> invoiceContentsList = InvoiceContents.getDummyData();
        List<Cell[]> rowsForShippingMarks = new ArrayList<>();

        String shippingMarks = invoiceContentsList.get(0)
                                                  .getShippingMarks();

//         generate shippingMarks
        Cell[] row = new Cell[14];
        row[0] = addCellForInvoice(2,2 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,3 , "ABCD"); // assortment
        row[2] = addCellForInvoice(2,1 , "ABCD"); // WHSE pack
        row[3] = addCellForInvoice(2,1 , "ABCD"); // VNDR pack

        row[4] = addCellForInvoice(1,1 , "ABCD"); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , "ABCD"); // total unit

        row[6] = addCellForInvoice(1,4 , "ABCD"); // weight
        row[7] = addCellForInvoice(2,1 , "ABCD"); // pack price
        row[8] = addCellForInvoice(2,2 , "ABCD"); // amount in USD

        row[9] = addCellForInvoice(1,1 , "ABCD");  // VNDR PACK type
        row[10] = addCellForInvoice(1,1, "ABCD"); // net vndr pack
        row[11] = addCellForInvoice(1,1 , "ABCD"); // net total
        row[12] = addCellForInvoice(1,1 , "ABCD"); // gross vndr pack
        row[13] = addCellForInvoice(1,1 , "ABCD"); // gross total


        rowsForShippingMarks.add(row);


        // add shipping mark rows to table
        for(Cell[] oneRow : rowsForShippingMarks) {
            for(Cell cell: oneRow) {
                if(cell != null) {
                    invoiceTable.addCell(cell);
                }
            }
        }
//        Table invoiceTable1 = TableUtil.removeBorder(invoiceTable);
        document.add(invoiceTable);

    }

    private static Cell addCellForInvoice(int rowSpan, int colSpan, String content) {
        Paragraph paragraph = new Paragraph(content).setFontSize(9);
        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
//        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.LEFT);
        return cell;
    }

    private static void generateAddress(Document document) {
        Paragraph title = new Paragraph("MASTER GROUP GLOBAL CO., LIMITED").setFontSize(18)
                                                                           .setBold()
                                                                           .setTextAlignment(TextAlignment.CENTER)
                                                                           .setMarginTop(0);
        document.add(title);


        Paragraph titleAddress = new Paragraph("UNIT 2003,20/F,ORIENT INTERNATIONAL TOWER,1018 TAI NAN WEST STREET,CHEUNG SHA WAN").setFontSize(12)
                                                                                                                                   .setTextAlignment(TextAlignment.CENTER)
                                                                                                                                   .setMarginTop(0);
        document.add(titleAddress);

        Paragraph telephone = new Paragraph().setMarginTop(0)
                                             .setFontSize(12)
                                             .setTextAlignment(TextAlignment.CENTER)
                                             .add("TEL:")
                                             .add("(852)2312 0409")
                                             .add(",")
                                             .add("FAX:")
                                             .add("(852)2312 0484");

        document.add(telephone);
        LineSeparator lineSeparator = new LineSeparator(new SolidLine());
        lineSeparator.setMarginTop(1);
        lineSeparator.setMarginLeft(20);
        lineSeparator.setMarginRight(20);
        lineSeparator.setStrokeColor(ColorConstants.BLACK);
        lineSeparator.setStrokeWidth(0);
        document.add(lineSeparator);

    }

    private static void generateOtherInformation(Document document) {
        Paragraph shipperParagraph = new Paragraph("SHIPPER:").setFontSize(9)
                                                              .setMarginLeft(20)
                                                              .setMarginTop(0);
        Paragraph exporter = new Paragraph("EXPORTER:").setFontSize(9)
                                                       .setMarginLeft(20)
                                                       .setMarginTop(0);
        Paragraph otherInformation = new Paragraph("OTHER INFORMATION:").setFontSize(9)
                                                                        .setMarginLeft(20)
                                                                        .setMarginTop(0);

        document.add(shipperParagraph);
        document.add(exporter);
        document.add(otherInformation);
    }

    private static Table generateInvoiceTableHeader(Document document) throws IOException {

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100};

//        Table invoiceTableHeader = new Table(15);
        Table invoiceTableHeader = new Table(columnWidths);
        invoiceTableHeader.setWidth(UnitValue.createPercentValue(100));
        invoiceTableHeader.setMarginLeft(20);
        invoiceTableHeader.setMarginRight(20);

        invoiceTableHeader.addCell(new Cell(2, 2).add(TableUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 3).add(TableUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION " + "\n AS PER PO")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("WHSE PACK \n (PCS)")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("VNDR PACK \n (PCS)")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        // 5th row
        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("TOTAL VNDR PACKS")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("TOTAL UNITS")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        // 7th row
        invoiceTableHeader.addCell(new Cell(1, 4).add(TableUtil.getTableHeader("WEIGHT (KG)")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("PACK PRICE \n (USD)")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("AMOUNT IN \n USD")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

//         bottom ones
        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("VNDR PACK TYPE")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("NET VNDR \n PACK")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("NET TOTAL")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("GROSS VNDR PACK")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(1, 1).add(TableUtil.getTableHeader("GROSS TOTAL")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

//        document.add(invoiceTableHeader);
        return invoiceTableHeader;
    }

    private static void generatePackingList(PdfDocument pdfDoc, Document document) throws IOException {

        Paragraph packagingList = new Paragraph("COMMERCIAL INVOICE AND PACKING LIST").setFontSize(15)
                                                                                      .setTextAlignment(TextAlignment.CENTER)
                                                                                      .setMarginTop(5);
        document.add(packagingList);


        // Create a table with 2 columns
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 4, 4}));
        table.setMarginLeft(20);
        table.setMarginRight(20);
        table.setMarginBottom(10);
        table.setBorder(Border.NO_BORDER);
        table.setWidth(UnitValue.createPercentValue(100));

        Paragraph firstRowParagraph = new Paragraph("SOLD TO:").setFontSize(9)
                                                               .add("\n")
                                                               .setTextAlignment(TextAlignment.LEFT)
                                                               .add("WALMART INC. \n 508 SW 8TH STREET")
                                                               .add("\n")
                                                               .add("BENTONVILLE")
                                                               .add("\n")
                                                               .add("AR")
                                                               .add("\n")
                                                               .add("US")
                                                               .add("\n")
                                                               .add("72716");

        Table secondTable = new Table(new float[]{1, 1});
        secondTable.setWidth(UnitValue.createPercentValue(100));
        secondTable.setBorder(Border.NO_BORDER);

        Paragraph invoice = new Paragraph("INVOICE #").setFontSize(9);
        secondTable.addCell(invoice)
                   .setBorder(Border.NO_BORDER)
                   .setPadding(0);

//         Invoice
        Paragraph invoiceValue = new Paragraph("3555044").setFontSize(9);
        secondTable.addCell(invoiceValue)
                   .setBorder(Border.NO_BORDER)
                   .setPadding(0);

//        COUNTRY OF CONSIGNEE:
        Paragraph coc = new Paragraph("COUNTRY OF CONSIGNEE:").setFontSize(9);
        secondTable.addCell(coc)
                   .setBorder(Border.NO_BORDER);

        Paragraph cocValue = new Paragraph("US").setFontSize(9);
        secondTable.addCell(cocValue)
                   .setBorder(Border.NO_BORDER);

//      VESSEL NAME:
        Paragraph vesselName = new Paragraph("VESSEL NAME:").setFontSize(9);
        secondTable.addCell(vesselName)
                   .setBorder(Border.NO_BORDER);

        Paragraph vesselNameValue = new Paragraph("GSL LYDIA").setFontSize(9);
        secondTable.addCell(vesselNameValue)
                   .setBorder(Border.NO_BORDER);

//        SHIPMENT INCOTERMS RULE:
        Paragraph shipmentRule = new Paragraph("SHIPMENT INCOTERMS RULE:").setFontSize(9);
        secondTable.addCell(shipmentRule)
                   .setBorder(Border.NO_BORDER);

        Paragraph shipmentRuleValue = new Paragraph("FOB").setFontSize(9);
        secondTable.addCell(shipmentRuleValue)
                   .setBorder(Border.NO_BORDER);

//    DEPARTURE/CARGO RECEIVED DATE:
        Paragraph cargoRecvdDate = new Paragraph("DEPARTURE/CARGO RECEIVED DATE:").setFontSize(9);
        secondTable.addCell(cargoRecvdDate)
                   .setBorder(Border.NO_BORDER);

        Paragraph cargoRecvdDateValue = new Paragraph("2024-09-24").setFontSize(9);
        secondTable.addCell(cargoRecvdDateValue)
                   .setBorder(Border.NO_BORDER);

//        COUNTRY OF DESTINATION:
        Paragraph destinationCountry = new Paragraph("COUNTRY OF DESTINATION:").setFontSize(9);
        secondTable.addCell(destinationCountry)
                   .setBorder(Border.NO_BORDER);

        Paragraph destinationCountryValue = new Paragraph("US").setFontSize(9);
        secondTable.addCell(destinationCountryValue)
                   .setBorder(Border.NO_BORDER);

//        FROM:
        Paragraph from = new Paragraph("FROM:").setFontSize(9);
        secondTable.addCell(from)
                   .setBorder(Border.NO_BORDER);

        Paragraph fromValue = new Paragraph("YANTIAN").setFontSize(9);
        secondTable.addCell(fromValue)
                   .setBorder(Border.NO_BORDER);

        Paragraph fromSmaller = new Paragraph("(PORT/PLACE OF LOADING)").setFontSize(5);
        secondTable.addCell(fromSmaller)
                   .setBorder(Border.NO_BORDER);

//  Third Row Paragraph

//        Paragraph thirdParagraph = new Paragraph();
        Table thirdTable = new Table(new float[]{1, 1});
        thirdTable.setWidth(UnitValue.createPercentValue(100));
        thirdTable.setBorder(Border.NO_BORDER);

//        DATE:
        Paragraph toDate = new Paragraph("DATE:").setFontSize(9);
        thirdTable.addCell(toDate)
                  .setBorder(Border.NO_BORDER);

        Paragraph toDateValue = new Paragraph("2024-09-24").setFontSize(9);
        thirdTable.addCell(toDateValue)
                  .setBorder(Border.NO_BORDER);

//        VOYAGE/FLIGHT #
        Paragraph voyageFlightNum = new Paragraph("VOYAGE/FLIGHT #").setFontSize(9);
        thirdTable.addCell(voyageFlightNum)
                  .setBorder(Border.NO_BORDER);

        Paragraph voyageFlightNumValue = new Paragraph("439W").setFontSize(9);
        thirdTable.addCell(voyageFlightNumValue)
                  .setBorder(Border.NO_BORDER);

//        TRANSPORT MODE:
        Paragraph transPortMode = new Paragraph("VOYAGE/FLIGHT #").setFontSize(9);
        thirdTable.addCell(transPortMode)
                  .setBorder(Border.NO_BORDER);

        Paragraph transPortModeValue = new Paragraph("OCEAN").setFontSize(9);
        thirdTable.addCell(transPortModeValue)
                  .setBorder(Border.NO_BORDER);
//        thirdParagraph.add("\n");

//        NAMED PLACE:
        Paragraph namedPlace = new Paragraph("NAMED PLACE:").setFontSize(9);
        thirdTable.addCell(namedPlace)
                  .setBorder(Border.NO_BORDER);

        Paragraph namedPlaceValue = new Paragraph("YANTIAN").setFontSize(9);
        thirdTable.addCell(namedPlaceValue)
                  .setBorder(Border.NO_BORDER);
//        thirdParagraph.add("\n");

//        COUNTRY OF LOADING:
        Paragraph lodingCountry = new Paragraph("COUNTRY OF LOADING:").setFontSize(9);
        thirdTable.addCell(lodingCountry)
                  .setBorder(Border.NO_BORDER);

        Paragraph lodingCountryValue = new Paragraph("CN").setFontSize(9);
        thirdTable.addCell(lodingCountryValue)
                  .setBorder(Border.NO_BORDER);
//        thirdParagraph.add("\n");

// COUNTRY OF ORIGIN:
        Paragraph originCountry = new Paragraph("COUNTRY OF ORIGIN:").setFontSize(9);
        thirdTable.addCell(originCountry)
                  .setBorder(Border.NO_BORDER);

        Paragraph originCountryValue = new Paragraph("CN").setFontSize(9);
        thirdTable.addCell(originCountryValue)
                  .setBorder(Border.NO_BORDER);

//        TO:
        Paragraph to = new Paragraph("TO:").setFontSize(9);
        thirdTable.addCell(to)
                  .setBorder(Border.NO_BORDER);

        Paragraph toValue = new Paragraph("CHARLESTON").setFontSize(9);
        thirdTable.addCell(toValue)
                  .setBorder(Border.NO_BORDER);

        Paragraph toSmaller = new Paragraph("(FINAL DESTINATION IN THE PO)").setFontSize(5);
        thirdTable.addCell(toSmaller)
                  .setBorder(Border.NO_BORDER);

        table.addCell(firstRowParagraph)
             .setBorder(Border.NO_BORDER);
        table.addCell(secondTable)
             .setBorder(Border.NO_BORDER);
        table.addCell(thirdTable)
             .setBorder(Border.NO_BORDER);


        Table borderLessTable = TableUtil.removeBorder(table);
        document.add(borderLessTable);
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