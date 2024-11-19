package com.gthm.itext.lowagie.table;

import com.gthm.itext.events.PdfHeaderWithPageNumber;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LovagieInvoicePDF {

    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
    private static String PATH = directory + "my-lowa.pdf";

    private static String EMPTY_BLOCK_TXT = """

        """;
    private static String TAB = "\t";

    static Font FONT_SIZE_9 = new Font(Font.FontFamily.HELVETICA, 9);
    static Font FONT_SIZE_8 = new Font(Font.FontFamily.HELVETICA, 8);
    static Font HELVETICA_SIZE_10 = new Font(Font.FontFamily.HELVETICA, 10);
    static Font HELVETICA_SIZE_8_BOLD = new Font(Font.FontFamily.HELVETICA, 8);
    static Font HELVETICA_FONT_SIZE_5 = new Font(Font.FontFamily.HELVETICA, 5);
    static Font HELVETICA_FONT_SIZE_15 = new Font(Font.FontFamily.HELVETICA , 15);

    public static void main(String[] args) {

        try {

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
            PdfDocument pdfDoc = new PdfDocument();
            pdfDoc.addWriter(writer);

            document.open();
            document.setMargins(36, 20, 36, 20);
//            pdfDoc.setPageSize(PageSize.A4.rotate());
//            pdfDoc.setDefaultPageSize(PageSize.A4.rotate());

            HeaderEventHandler headerEventHandler = new HeaderEventHandler("WALMART INC",
                    "355670", "10/06/2024");
//            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, headerEventHandler);
            // Set the page event handler
//            writer.setPageEvent(headerEventHandler);


//             starting to construct the PDF
            generateAddress(document);
            generatePackingList(pdfDoc, document);
            generateOtherInformation(document);
            PdfPTable invoiceTable = generateInvoiceTableHeader(document);
//            document.add(invoiceTable);
            generateTheDataInInvoiceTable(document, invoiceTable);

//            generate total in words
            Paragraph totalAmountInWords =
                    new Paragraph("INVOICE TOTAL VALUE IN US DOLLARS FORTY-NINE THOUSAND THREE HUNDRED " +
                            "AND FIFTY TWO AND CENTS THIRTEEN");
            totalAmountInWords.setSpacingBefore(15);
            totalAmountInWords.setFont(FONT_SIZE_9);
            totalAmountInWords.setIndentationLeft(30);
            totalAmountInWords.setAlignment(Element.ALIGN_LEFT);
            totalAmountInWords.setSpacingAfter(20);
            document.add(totalAmountInWords);

//            generateUnder line and extra Stuff
            generateUnderLineAndExtraStuff(document);



            // close the document
            document.close();

//             start generating the page number
            String outputFilePath =  directory + File.separator + "my-own-page-num.pdf";
            PdfHeaderWithPageNumber.manipulatePdf(PATH , outputFilePath);

//            updatePageHeaders(headerEventHandler, PATH, outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void generateUnderLineAndExtraStuff(Document document) throws DocumentException {
        addUnderLine(document);

        Paragraph descriptionPara = new
                Paragraph("DESCRIPTION, ORIGIN, QUANTITY, COST OF ANY FREE SUPPLIED PART(S):");
        descriptionPara.setFont(FONT_SIZE_8);
        descriptionPara.setAlignment(Element.ALIGN_LEFT);
        descriptionPara.setIndentationLeft(30);

        document.add(descriptionPara);

        addUnderLine(document);


//         signature
        Paragraph namePara = new Paragraph("JANE");
        namePara.setFont(FONT_SIZE_8);
        namePara.setAlignment(Element.ALIGN_LEFT);
        namePara.setSpacingBefore(20);
        namePara.setIndentationLeft(30);

        document.add(namePara);

        Paragraph shippingSpecialist = LowagieUtil.createParagraph("SHIPPING SPECIALIST",
                FONT_SIZE_8, Element.ALIGN_LEFT,
                LowagieMargin.builder().left(30f).build());

        document.add(shippingSpecialist);

    }

    private static void generateInvoiceTotalRow(PdfPTable invoiceTable) {

        String invoiceTotal = "INVOICE TOTAL ";

//        7,10,13
        createRowForInvoiceTotal("", invoiceTotal, "1", "14", "42",
                "588",  "83.9322", "49352.13",
                "210.0000", "8820.0000",  "10010.7000", invoiceTable);



    }

    private static void generateTheDataInInvoiceTable(Document document, PdfPTable invoiceTable) throws DocumentException {

        //         generate shippingMarks
        String shippingMarks = "To US Case ID\nPO # 2923267225\nPo Type # 43\nDept # 9\nItem # 595469863\nSupplier STK # BF-PC";
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

    private static void generateItem(PdfPTable invoiceTable) {

        StringBuilder builder = new StringBuilder();
        builder.append("ITEM # ").append("980272065").append(System.lineSeparator());


//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs,
//        total unit, weight, pack price,amount in USD, VNDR PACK type,
//        net vndr pack, net total, gross vndr pack, gross total

        createRowForItemInInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(),  "14",
                "42", "588",  "83.9322", "49352.1336",
                 "210.0000", "8820.0000",
                 invoiceTable);

        builder.setLength(0);
        builder.append("ITEM DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append(TAB).append("  COMPONENT DETAILS: ").append(System.lineSeparator());

        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  invoiceTable);

//         clear builder to reuse
        builder.setLength(0);

//        build the Common Name
        for (int i = 0; i < 5; i++) {

            builder.append(TAB).append("   COMMON NAME: ").append("2QT COVERED SAUCE PAN").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    BREAKDOWN:").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    STAINLESS STEEL - ").append("20%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    GLASS - ").append("25%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    ALUMINUM - ").append("55%").append(System.lineSeparator());
            builder.append(TAB).append("   VALUE: ").append("7.8391").append(System.lineSeparator())
                   .append(System.lineSeparator()).append(System.lineSeparator());

            //     5 , 10, 12 ,13 , 14
            createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                    EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                    EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,
                    invoiceTable);

        }

        builder.setLength(0);
        builder.append("ASSEMBLY MANUFACTURER:").append(System.lineSeparator());
        builder.append("NAME: ").append("GUANGDONG MASTER GROUP CO., LTD").append(System.lineSeparator());
        builder.append("ADDRESS: ")
               .append("NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300, 8618023382230")
               .append(System.lineSeparator())
               .append(System.lineSeparator())
               .append(System.lineSeparator());


        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,  invoiceTable);

    }

    private static void generateProductDescription(PdfPTable invoiceTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());


//        createRowForIncomeTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

//     5 , 10, 12 ,13 , 14
        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                 EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                 EMPTY_BLOCK_TXT, invoiceTable);

    }


    private static void createExpandingRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
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

//    7,
    private static void createRowForAssortmentInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                           String vndrPack, String totalVNDRPack, String totalUnit,
                                                           String packPrice, String amountInUSD,
                                                           String vndrPackType, String netVNDRPack, String netTotal,
                                                           String grossVndrPack, String grossTotal, PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,6 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , totalUnit);  // total unit
        row[6] = addCellForInvoice(2,1 , netVNDRPack); // net vndr pack
        row[7] = addCellForInvoice(2,1 , netTotal); // net total
        row[8] = addCellForInvoice(2,1 , grossVndrPack); // gross vndr pack
        row[9] = addCellForInvoice(2,1, grossTotal); // gross total
        row[10] = addCellForInvoice(2,1 , packPrice); // pack price
        row[11] = addCellForInvoice(2,1 , amountInUSD);  // amount in USD
        row[12] = addCellForInvoice(1,1 , vndrPackType); // VNDR PACK type

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    private static void generateAssortmentItems(PdfPTable invoiceTable) {

        StringBuilder assortmentBuilder = new StringBuilder();
        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());

        createRowForAssortmentInvoiceTable(EMPTY_BLOCK_TXT, assortmentBuilder.toString(), "1", "14", "42",
                "588", "1175.0508", "49352.1336", "PALLET(S)",
                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);

    }

    private static void createRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
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

    private static void createRowForItemInInvoiceTable(String shippingMarks, String assortment,
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


    private static void createRowForInvoiceTotal(String shippingMarks, String assortment, String whsepack,
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

    private static void generateShippingMarksRow(String shippingMarks, PdfPTable invoiceTable) {

//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
//        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total

        createRowForInvoiceTable(shippingMarks, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                invoiceTable);
    }

    private static PdfPCell addCellForInvoice(int rowSpan, int colSpan, String content) {

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
//        cell.setBorder(Rectangle.NO_BORDER);
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
//        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private static void generateAddress(Document document) throws DocumentException {
        Paragraph title = LowagieUtil.createParagraph("MASTER GROUP GLOBAL CO., LIMITED" ,
                HELVETICA_SIZE_8_BOLD , Element.ALIGN_CENTER,
                null);
        document.add(title);

        Paragraph titleAddress = LowagieUtil.createParagraph("UNIT 2003,20/F,ORIENT INTERNATIONAL TOWER,1018 TAI NAN WEST STREET,CHEUNG SHA WAN",
                HELVETICA_SIZE_10, Element.ALIGN_CENTER,null);

        document.add(titleAddress);

        StringBuilder telephoneBuilder = new StringBuilder();
        telephoneBuilder.append("TEL:")
                .append("(852)2312 0409")
                .append(",")
                .append("FAX:")
                .append("(852)2312 0484");

        Paragraph telephone = LowagieUtil.createParagraph(telephoneBuilder.toString() , HELVETICA_SIZE_10,
                Element.ALIGN_LEFT , null);

        document.add(telephone);
        addUnderLine(document);

    }

    private static void addUnderLine(Document document) throws DocumentException {

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

    private static void generateOtherInformation(Document document) throws DocumentException {

        LowagieMargin margin = LowagieMargin.builder()
                                           .left(20f)
                                           .build();

        Paragraph shipperParagraph = LowagieUtil.createParagraph("SHIPPER:" , FONT_SIZE_8 , null, margin);
        Paragraph exporter =  LowagieUtil.createParagraph("EXPORTER:" , FONT_SIZE_8 , null, margin);
        Paragraph otherInformation = LowagieUtil.createParagraph("OTHER INFORMATION:" , FONT_SIZE_8 , null, margin);

        document.add(shipperParagraph);
        document.add(exporter);
        document.add(otherInformation);
    }

    private static PdfPTable generateInvoiceTableHeader(Document document) throws IOException {

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};

        PdfPTable invoiceTableHeader = new PdfPTable(columnWidths);
        invoiceTableHeader.setWidthPercentage(100);
//        invoiceTableHeader.setMarginLeft(20);
//        invoiceTableHeader.setMarginRight(20);


        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#" , 2 , 3));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);


        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION "
                + "\n AS PER PO", 2, 6));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("WHSE PACK \n (PCS)" , 2 , 1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("VNDR PACK \n (PCS)" , 2 ,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        // 5th row
        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("TOTAL VNDR PACKS" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("TOTAL UNITS" , 2,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        // 7th row
        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("WEIGHT (KG)" , 1, 4));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("PACK PRICE \n (USD)" , 2, 1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("AMOUNT IN \n USD" , 2 ,1));

//         bottom ones
        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("VNDR PACK TYPE" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("NET VNDR \n PACK" , 1, 1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("NET TOTAL" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("GROSS VNDR PACK" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(LowagieUtil.getTableHeader("GROSS TOTAL" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        return invoiceTableHeader;
    }

    private static void generatePackingList(PdfDocument pdfDoc, Document document) throws IOException, DocumentException {

        Paragraph packagingList = LowagieUtil.createParagraph("COMMERCIAL INVOICE AND PACKING LIST" , HELVETICA_FONT_SIZE_15,  Element.ALIGN_CENTER ,
                LowagieMargin.builder().top(5f).build());

        document.add(packagingList);

        // Create a table with 2 columns
        PdfPTable table = new PdfPTable(new float[]{2, 4, 4});
        table.getDefaultCell().setPaddingLeft(20);
        table.getDefaultCell().setPaddingRight(20);
        table.getDefaultCell().setPaddingBottom(10);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);

        StringBuilder builder = new StringBuilder();
        builder.append("SOLD TO:")
                .append(System.lineSeparator())
               .append("WALMART INC. \n 508 SW 8TH STREET")
               .append(System.lineSeparator())
               .append("BENTONVILLE")
               .append(System.lineSeparator())
               .append("AR")
               .append(System.lineSeparator())
               .append("US")
               .append(System.lineSeparator())
               .append("72716");

        Paragraph firstRowParagraph = LowagieUtil.createParagraph(builder.toString(), FONT_SIZE_9,
                Element.ALIGN_LEFT, null);

        PdfPTable secondTable = new PdfPTable(new float[]{1, 1});
        secondTable.setWidthPercentage(100);

        PdfPCell invoice = LowagieUtil.getPdfpCellForFromTO("INVOICE #");
        secondTable.addCell(invoice);

//         Invoice
        PdfPCell invoiceValue = LowagieUtil.getPdfpCellForFromTO("3555044");
        secondTable.addCell(invoiceValue);

//        COUNTRY OF CONSIGNEE:
        PdfPCell coc = LowagieUtil.getPdfpCellForFromTO("COUNTRY OF CONSIGNEE:");
        secondTable.addCell(coc);

        PdfPCell cocValue = LowagieUtil.getPdfpCellForFromTO("US");
        secondTable.addCell(cocValue);

//      VESSEL NAME:
        PdfPCell vesselName = LowagieUtil.getPdfpCellForFromTO("VESSEL NAME:");
        secondTable.addCell(vesselName);

        PdfPCell vesselNameValue = LowagieUtil.getPdfpCellForFromTO("GSL LYDIA");
        secondTable.addCell(vesselNameValue);

//        SHIPMENT INCOTERMS RULE:
        PdfPCell shipmentRule = LowagieUtil.getPdfpCellForFromTO("SHIPMENT INCOTERMS RULE:");
        secondTable.addCell(shipmentRule);

        PdfPCell shipmentRuleValue = LowagieUtil.getPdfpCellForFromTO("FOB");
        secondTable.addCell(shipmentRuleValue);

//    DEPARTURE/CARGO RECEIVED DATE:
        PdfPCell cargoRecvdDate = LowagieUtil.getPdfpCellForFromTO("DEPARTURE/CARGO RECEIVED DATE:");
        secondTable.addCell(cargoRecvdDate);

        PdfPCell cargoRecvdDateValue = LowagieUtil.getPdfpCellForFromTO("2024-09-24");
        secondTable.addCell(cargoRecvdDateValue);

//        COUNTRY OF DESTINATION:
        PdfPCell destinationCountry = LowagieUtil.getPdfpCellForFromTO("COUNTRY OF DESTINATION:");
        secondTable.addCell(destinationCountry);

        PdfPCell destinationCountryValue = LowagieUtil.getPdfpCellForFromTO("US");
        secondTable.addCell(destinationCountryValue);

//        FROM:
        PdfPCell from = LowagieUtil.getPdfpCellForFromTO("FROM:");
        secondTable.addCell(from);

        PdfPCell fromValue = LowagieUtil.getPdfpCellForFromTO("YANTIAN");
        secondTable.addCell(fromValue);

        PdfPCell fromSmaller = LowagieUtil.getPdfpCellForFromTO("(PORT/PLACE OF LOADING)", HELVETICA_FONT_SIZE_5);
        secondTable.addCell(fromSmaller);

//  Third Row Paragraph

//        Paragraph thirdParagraph = new Paragraph();
        PdfPTable thirdTable = new PdfPTable(new float[]{1, 1});
        thirdTable.setWidthPercentage(100);
//        thirdTable.setBorder(Border.NO_BORDER);

//        DATE:

        PdfPCell toDate = LowagieUtil.getPdfpCellForFromTO("DATE:");
        thirdTable.addCell(toDate);

        PdfPCell toDateValue = new PdfPCell (new Phrase("2024-09-24", FONT_SIZE_9));
        thirdTable.addCell(toDateValue);


//        VOYAGE/FLIGHT #
        PdfPCell voyageFlightNum = new PdfPCell (new Phrase("VOYAGE/FLIGHT #", FONT_SIZE_9));
        thirdTable.addCell(voyageFlightNum);


        PdfPCell voyageFlightNumValue = new PdfPCell (new Phrase("439W", FONT_SIZE_9));
        thirdTable.addCell(voyageFlightNumValue);


//        TRANSPORT MODE:
        PdfPCell transPortMode = new PdfPCell ( new Phrase("VOYAGE/FLIGHT #", FONT_SIZE_9));
        thirdTable.addCell(transPortMode);

        PdfPCell transPortModeValue =  new PdfPCell( new Phrase("OCEAN", FONT_SIZE_9));
        thirdTable.addCell(transPortModeValue);

//        NAMED PLACE:
        PdfPCell namedPlace =  new PdfPCell( new Phrase("NAMED PLACE:", FONT_SIZE_9));
        thirdTable.addCell(namedPlace);

        PdfPCell namedPlaceValue = new PdfPCell( new Phrase("YANTIAN", FONT_SIZE_9));
        thirdTable.addCell(namedPlaceValue);

//        COUNTRY OF LOADING:
        PdfPCell lodingCountry = new PdfPCell( new Phrase("COUNTRY OF LOADING:", FONT_SIZE_9));
        thirdTable.addCell(lodingCountry);

        PdfPCell lodingCountryValue = new PdfPCell( new Phrase("CN", FONT_SIZE_9));
        thirdTable.addCell(lodingCountryValue);


// COUNTRY OF ORIGIN:
        PdfPCell originCountry = new PdfPCell( new Phrase("COUNTRY OF ORIGIN:", FONT_SIZE_9));
        thirdTable.addCell(originCountry);

        PdfPCell originCountryValue = new PdfPCell( new Phrase("CN", FONT_SIZE_9));
        thirdTable.addCell(originCountryValue);


//        TO:
        PdfPCell to = new PdfPCell( new Phrase("TO:", FONT_SIZE_9));
        thirdTable.addCell(to);

        PdfPCell toValue = new PdfPCell( new Phrase("CHARLESTON", FONT_SIZE_9));
        thirdTable.addCell(toValue);

        PdfPCell toSmaller = new PdfPCell( new Phrase("(FINAL DESTINATION IN THE PO)", FONT_SIZE_9));
        thirdTable.addCell(toSmaller);

        PdfPCell firstTableCell = new PdfPCell(firstRowParagraph);
        PdfPCell secondTableCell = new PdfPCell(secondTable);
        PdfPCell thirdTableCell = new PdfPCell(thirdTable);


        table.addCell(firstTableCell);
        table.addCell(secondTableCell);
        table.addCell(thirdTableCell);

        PdfPTable borderLessTable = LowagieUtil.removeBorder(table);
        document.add(borderLessTable);
    }


    private static void updatePageHeaders(HeaderEventHandler headerEventHandler, String sourcePdfPath ,
                                          String targetPdfPath) throws IOException, DocumentException {

        System.out.println("re-opening the docs after saving ==========");
        // Read the existing PDF
        PdfReader reader = new PdfReader(sourcePdfPath);

        // Set up the output PDF file and the PdfStamper (for modifying the existing PDF)
        FileOutputStream fileOutputStream = new FileOutputStream(targetPdfPath);
        PdfStamper stamper = new PdfStamper(reader, fileOutputStream);

        int totalPageCount = reader.getNumberOfPages();
        headerEventHandler.setTotalPageCount(totalPageCount);

        // Get the writer instance to set the page event
        PdfWriter writer = stamper.getWriter();

        // Set the page event (i.e., the header event)
        writer.setPageEvent(headerEventHandler);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            headerEventHandler.updatePageHeaders(stamper.getWriter(), i, reader.getNumberOfPages(), reader.getPageSize(i));
        }


        // Update the header on each page
//        for (int pageNumber = 1; pageNumber <= totalPageCount; pageNumber++) {
//            PdfPage page = reader.getPage(pageNumber);
//            headerEventHandler.updatePageHeaders(page, pageNumber, totalPageCount);
//        }

        stamper.close();

    }










}