package com.gthm.itext.util;

import com.gthm.itext.usefulUtils.TableUtil;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
import java.util.List;

public class Table4 {

    private static String EMPTY_TEXT = """
        
        """;
    private static String TAB = "\t";

    public static void main(String[] args) throws Exception {

        String dest = "C:\\Users\\anon\\Pictures\\output-invoide\\Table4.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDoc);

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100));

        table.addCell(new Cell(2, 3).add(TableUtil.getTableHeader("A1")));
        table.addCell(new Cell(2, 6).add(TableUtil.getTableHeader("B2")));
        table.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("C3")));
        table.addCell(new Cell(2, 1).add(TableUtil.getTableHeader("D4")));

        // 5th row
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("E5")));

        table.addCell(new Cell(2, 0).add(TableUtil.getTableHeader("F6")));
        // 7th row
        table.addCell(new Cell(0, 4).add(TableUtil.getTableHeader("G7")));

        table.addCell(new Cell(2, 0).add(TableUtil.getTableHeader("8")));
        table.addCell(new Cell(2, 0).add(TableUtil.getTableHeader("9")));

//         bottom ones
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("10")));
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("11")));
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("12")));
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("13")));
        table.addCell(new Cell(0, 0).add(TableUtil.getTableHeader("14")));


//         generate data

        String shippingMarks = "To US Case ID\nPO # 2923267225\nPo Type # 43\nDept # 9\nItem # 595469863\nSupplier STK # BF-PC";
        generateShippingMarksRow(shippingMarks , table);

//         generate values for ASSORTMENT ITEMS
        generateAssortmentItems(table);

        //        generate product description
//        generateProductDescription(table);

        //          generate item row
        generateItem(table);

//        generateInvoiceTotalRow(table);

        // Populate full PDF structure
        document.add(table);
        document.close();

        System.out.println("PDF Table Generated Successfully!");
    }

    private static void generateItem(Table invoiceTable) {

//        StringBuilder builder = new StringBuilder();
//        builder.append("ITEM # ").append("980272065").append(System.lineSeparator());


//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs,
//        total unit, weight, pack price,amount in USD, VNDR PACK type,
//        net vndr pack, net total, gross vndr pack, gross total

//        createRowForItemInInvoiceTable(EMPTY_TEXT, builder.toString(), EMPTY_TEXT, "14", "42",
//                "588", EMPTY_TEXT, "83.9322", "49352.1336", EMPTY_TEXT,
//                "210.0000", "8820.0000", EMPTY_TEXT, EMPTY_TEXT, invoiceTable);

//        builder.setLength(0);
//        builder.append("ITEM DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
//        builder.append(TAB).append("COMPONENT DETAILS: ").append(System.lineSeparator());
//
//
//        createExpandingRowForInvoiceTable(EMPTY_TEXT, builder.toString(), EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, invoiceTable);
//
//
////         clear builder to reuse
//        builder.setLength(0);
//
////        build the Common Name
//        for (int i = 0; i < 1; i++) {
//
//            builder.append(TAB).append("COMMON NAME: ").append("2QT COVERED SAUCE PAN").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("BREAKDOWN:").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("STAINLESS STEEL - ").append("20%").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("GLASS - ").append("25%").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("ALUMINUM - ").append("55%").append(System.lineSeparator());
//            builder.append(TAB).append("VALUE: ").append("7.8391").append(System.lineSeparator())
//                   .append(System.lineSeparator()).append(System.lineSeparator());
//
//            createExpandingRowForInvoiceTable(EMPTY_TEXT, builder.toString(), EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, invoiceTable);
//
//        }
//
//        builder.setLength(0);
//
//        builder.setLength(0);
////        builder.append(System.lineSeparator());
//        builder.append("ASSEMBLY MANUFACTURER:").append(System.lineSeparator());
//        builder.append("NAME: ").append("GUANGDONG MASTER GROUP CO., LTD").append(System.lineSeparator());
//        builder.append("ADDRESS: ")
//               .append("NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300, 8618023382230")
//               .append(System.lineSeparator())
//               .append(System.lineSeparator())
//               .append(System.lineSeparator());
//
//        createExpandingRowForInvoiceTable(EMPTY_TEXT, builder.toString(), EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT,
//                EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT,
//                EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, invoiceTable);


    }

    private static void createRowForItemInInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String weight, String packPrice, String amountInUSD,
                                                 String vndrPackType, String netVNDRPack, String netTotal,
                                                 String grossVndrPack, String grossTotal, Table invoiceTable
    ) {

        Cell[] row = new Cell[14];
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

        for(Cell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    private static void generateInvoiceTotalRow(Table invoiceTable) {

        String invoiceTotal = "INVOICE TOTAL ";

        createRowForInvoiceTotal("", invoiceTotal, "1", "14", "42",
                "588", "", "83.9322", "49352.13", "",
                "210.0000", "8820.0000", "", "10010.7000", invoiceTable);




    }

    private static void createRowForInvoiceTotal(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String weight, String packPrice, String amountInUSD,
                                                 String vndrPackType, String netVNDRPack, String netTotal,
                                                 String grossVndrPack, String grossTotal, Table invoiceTable
    ) {

        Cell[] row = new Cell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoiceTotal(2,6 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[4] = addCellForInvoice(2,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , totalUnit); // total unit
        row[10] = addCellForInvoice(2,1, "1");
        row[7] = addCellForInvoice(2,1 , netVNDRPack); // net vndr pack
        row[11] = addCellForInvoice(2,1 , grossTotal); // gross total
        row[12] = addCellForInvoice(2,1 , packPrice); // pack price
        row[13] = addCellForInvoice(2,1 , amountInUSD); // amount in USD
        row[8] = addCellForInvoice(2,1 , netTotal); // net total

        for(Cell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
    }

    private static Cell addCellForInvoiceTotal(int rowSpan, int colSpan, String content) {
        Paragraph paragraph = new Paragraph(content)
                .setFontSize(8)
                .setPaddingLeft(5);

        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
//        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.RIGHT);
        return cell;
    }

    private static void generateAssortmentItems(Table invoiceTable) {
        List<Cell[]> rowsForShippingMarks = new ArrayList<>();

        StringBuilder assortmentBuilder = new StringBuilder();
        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());

//         TODO: continue the changes here
        createRowForAssortmentInvoiceTable(EMPTY_TEXT, assortmentBuilder.toString(), "1", "14", "42",
                "588", EMPTY_TEXT, "1175.0508", "49352.1336", "PALLET(S)",
                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);

    }

    private static void generateProductDescription(Table invoiceTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());


//        createRowForInvoiceTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

        createExpandingRowForInvoiceTable(EMPTY_TEXT, builder.toString(), EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, invoiceTable);


    }


    private static void createExpandingRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                 String vndrPack, String totalVNDRPack, String totalUnit,
                                                 String weight, String packPrice, String amountInUSD,
                                                 String vndrPackType, String netVNDRPack, String netTotal,
                                                 String grossVndrPack, String grossTotal, Table invoiceTable
    ) {

        Cell[] row = new Cell[14];
        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = addCellForInvoice(2,9 , assortment); // assortment
        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack

//        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
        row[5] = addCellForInvoice(2,1 , totalUnit); // total unit

        row[6] = addCellForInvoice(1,0 , weight); // weight
        row[7] = addCellForInvoice(2,1 , packPrice); // pack price
        row[8] = addCellForInvoice(2,2 , amountInUSD); // amount in USD

//        row[9] = addCellForInvoice(1,1 , vndrPackType);  // VNDR PACK type
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

    private static void generateShippingMarksRow(String shippingMarks, Table invoiceTable) {

//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
//        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total

        createRowForInvoiceTable(shippingMarks, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, EMPTY_TEXT, invoiceTable);
    }

    private static void createRowForAssortmentInvoiceTable(String shippingMarks, String assortment, String whsepack,
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
        row[5] = addCellForInvoice(2,1 , totalUnit);  // total unit
        row[6] = addCellForInvoice(2,1 , netVNDRPack); // net vndr pack
        row[7] = addCellForInvoice(2,1 , netTotal); // net total
        row[8] = addCellForInvoice(2,1 , grossVndrPack); // gross vndr pack
        row[9] = addCellForInvoice(2,1, grossTotal); // gross total
        row[10] = addCellForInvoice(2,1 , packPrice); // pack price
        row[11] = addCellForInvoice(2,1 , amountInUSD);  // amount in USD
        row[12] = addCellForInvoice(1,1 , vndrPackType); // VNDR PACK type

        for(Cell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
            }
        }
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

    private static Cell addCellForInvoice(int rowSpan, int colSpan, String content) {

        // Create a Paragraph with tab stops
        Paragraph paragraph = new Paragraph(content)
                .setFontSize(8)
                .setPaddingLeft(5);
//                .addTabStops(new TabStop(4, TabAlignment.LEFT));

        // Split content into parts where you want tabs
//        String[] parts = content.split("\t"); // Use \t to define tabs in your input text
//        for (int i = 0; i < parts.length; i++) {
//            paragraph.add(parts[i]); // Add each part
//            if (i < parts.length - 1) {
//                paragraph.add(new Tab()); // Add a tab between parts
//            }
//        }

        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
//        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.LEFT);
        return cell;
    }
}

