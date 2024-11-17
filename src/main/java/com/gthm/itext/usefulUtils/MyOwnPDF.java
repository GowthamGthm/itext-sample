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
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TabAlignment;
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

    private static String DUMMY_TEXT = "-NA-";
    private static String TAB = "\t";


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
            generateTheDataInInvoiceTable(document, invoiceTable);


//            generate total in words
            Paragraph totalAmountInWords =
                    new Paragraph("INVOICE TOTAL VALUE IN US DOLLARS FORTY-NINE THOUSAND THREE HUNDRED " +
                            "AND FIFTY TWO AND CENTS THIRTEEN");
            totalAmountInWords.setMarginTop(15);
            totalAmountInWords.setFontSize(9);
            totalAmountInWords.setMarginLeft(30);
            totalAmountInWords.setTextAlignment(TextAlignment.LEFT);
            totalAmountInWords.setMarginBottom(20);
            document.add(totalAmountInWords);

//            generateUnder line and extra Stuff
            generateUnderLineAndExtraStuff(document);



            // close the document
            document.close();

//             start generating the page number
            String outputFilePath =  directory + File.separator + "my-own-page-num.pdf";
            updateHeaders(headerEventHandler, PATH, outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void generateUnderLineAndExtraStuff(Document document) {
        addUnderLine(document);

        Paragraph descriptionPara = new Paragraph("DESCRIPTION, ORIGIN, QUANTITY, COST OF ANY FREE SUPPLIED PART(S):")
                .setFontSize(8)
                 .setTextAlignment(TextAlignment.LEFT)
                .setMarginLeft(30);

        document.add(descriptionPara);

        addUnderLine(document);


//         signature
        Paragraph namePara = new Paragraph("JANE").setFontSize(8)
                                                   .setTextAlignment(TextAlignment.LEFT).setMarginTop(20)
                .setMarginLeft(30);

        document.add(namePara);

        Paragraph shippingSpecialist = new Paragraph("SHIPPING SPECIALIST").setFontSize(8)
                                                  .setTextAlignment(TextAlignment.LEFT)
                .setMarginLeft(30);

        document.add(shippingSpecialist);


    }

    private static void generateInvoiceTotalRow(Table invoiceTable) {

        String invoiceTotal = "INVOICE TOTAL";

        createRowForInvoiceTable("", invoiceTotal, "1", "14", "42",
                "588", "", "83.9322", "49352.13", "",
                "210.0000", "8820.0000", "", "10010.7000", invoiceTable);



    }

    private static void generateTheDataInInvoiceTable(Document document, Table invoiceTable) {

        List<InvoiceContents> invoiceContentsList = InvoiceContents.getDummyData();

        //         generate shippingMarks
        String shippingMarks = invoiceContentsList.get(0)
                                                  .getShippingMarks();
        generateShippingMarksRow(shippingMarks , invoiceTable);

//         generate values for ASSORTMENT ITEMS
        generateAssortmentItems(invoiceTable);

//        generate product description
         generateProductDescription(invoiceTable);

//          generate item row
        generateItem(invoiceTable);

        //   generate invoice Total Row
        generateInvoiceTotalRow(invoiceTable);

//         add invoice table to the document at last of adding all RoWS
        document.add(invoiceTable);

    }

    private static void generateItem(Table invoiceTable) {

        StringBuilder builder = new StringBuilder();
        builder.append("ITEM # ").append("980272065").append(System.lineSeparator());


//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs,
//        total unit, weight, pack price,amount in USD, VNDR PACK type,
//        net vndr pack, net total, gross vndr pack, gross total

        createRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, "14", "42",
                "588", DUMMY_TEXT, "83.9322", "49352.1336", DUMMY_TEXT,
                "210.0000", "8820.0000", DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

        builder.setLength(0);
        builder.append("ITEM DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append(TAB).append("COMPONENT DETAILS: ").append(System.lineSeparator());


        createExpandingRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);


//         clear builder to reuse
        builder.setLength(0);

//        build the Common Name
        for (int i = 0; i < 5; i++) {

            builder.append(TAB).append("COMMON NAME: ").append("2QT COVERED SAUCE PAN").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("BREAKDOWN:").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("BREAKDOWN:").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("STAINLESS STEEL - ").append("20%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("GLASS - ").append("25%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("ALUMINUM - ").append("55%").append(System.lineSeparator());
            builder.append(TAB).append("VALUE: ").append("7.8391").append(System.lineSeparator())
                   .append(System.lineSeparator()).append(System.lineSeparator());

            createRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                    DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                    DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

        }

        builder.setLength(0);

        builder.append(System.lineSeparator());
        builder.append("ASSEMBLY MANUFACTURER:").append(System.lineSeparator());
        builder.append("NAME: ").append("GUANGDONG MASTER GROUP CO., LTD").append(System.lineSeparator());
        builder.append("ADDRESS: ")
               .append("NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300, 8618023382230")
               .append(System.lineSeparator())
               .append(System.lineSeparator())
               .append(System.lineSeparator());

        createRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);




    }

    private static void generateProductDescription(Table invoiceTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());


//        createRowForIncomeTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

        createExpandingRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

    }

    private static void createExpandingRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                          String vndrPack, String totalVNDRPack, String totalUnit,
                                                          String weight, String packPrice, String amountInUSD,
                                                          String vndrPackType, String netVNDRPack, String netTotal,
                                                          String grossVndrPack, String grossTotal, Table invoiceTable
    ) {

        Cell[] row = new Cell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,8 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack

        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , totalUnit); // total unit

        row[6] = addCellForInvoice(1,0 , weight); // weight
        row[7] = addCellForInvoice(2,1 , packPrice); // pack price
        row[8] = addCellForInvoice(2,2 , amountInUSD); // amount in USD

        row[9] = addCellForInvoice(1,1 , vndrPackType);  // VNDR PACK type
        row[10] = addCellForInvoice(1,1, netVNDRPack); // net vndr pack
//        row[11] = addCellForInvoice(1,1 , netTotal); // net total
//        row[12] = addCellForInvoice(1,1 , grossVndrPack); // gross vndr pack
//        row[13] = addCellForInvoice(1,1 , grossTotal); // gross total

        for(Cell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    private static void generateAssortmentItems(Table invoiceTable) {
        List<Cell[]> rowsForShippingMarks = new ArrayList<>();

        StringBuilder assortmentBuilder = new StringBuilder();
        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());

        createRowForInvoiceTable(DUMMY_TEXT, assortmentBuilder.toString(), "1", "14", "42",
                "588", DUMMY_TEXT, "1175.0508", "49352.1336", "PALLET(S)",
                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);

    }

    private static void createRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String weight, String packPrice, String amountInUSD,
                                                 String vndrPackType, String netVNDRPack, String netTotal,
                                                 String grossVndrPack, String grossTotal, Table invoiceTable
    ) {

        Cell[] row = new Cell[14];
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

            for(Cell cell: row) {
                if(cell != null) {
                    invoiceTable.addCell(cell);
                }
            }
    }

    private static void generateShippingMarksRow(String shippingMarks, Table invoiceTable) {

//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
//        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total

        createRowForInvoiceTable(shippingMarks, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT , invoiceTable);
    }

    private static Cell addCellForInvoice(int rowSpan, int colSpan, String content) {
//        Paragraph paragraph = new Paragraph(content).setFontSize(8).setPaddingLeft(5);

        // Create a Paragraph with tab stops
        Paragraph paragraph = new Paragraph()
                .setFontSize(8)
                .setPaddingLeft(5)
                .addTabStops(new TabStop(4, TabAlignment.LEFT));

        // Split content into parts where you want tabs
        String[] parts = content.split("\t"); // Use \t to define tabs in your input text
        for (int i = 0; i < parts.length; i++) {
            paragraph.add(parts[i]); // Add each part
            if (i < parts.length - 1) {
                paragraph.add(new Tab()); // Add a tab between parts
            }
        }

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
        addUnderLine(document);

    }

    private static void addUnderLine(Document document) {
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

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};

//        Table invoiceTableHeader = new Table(15);
        Table invoiceTableHeader = new Table(columnWidths);
        invoiceTableHeader.setWidth(UnitValue.createPercentValue(100));
        invoiceTableHeader.setMarginLeft(20);
        invoiceTableHeader.setMarginRight(20);
        invoiceTableHeader.setKeepTogether(true);

        invoiceTableHeader.addCell(new Cell(2, 3).add(TableUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#")))
                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(new Cell(2, 6).add(TableUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION " + "\n AS PER PO")))
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