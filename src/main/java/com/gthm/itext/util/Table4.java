package com.gthm.itext.util;

import com.gthm.itext.usefulUtils.TableUtil;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
import java.util.List;

public class Table4 {

    private static String DUMMY_TEXT = "-NA-";
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
        generateProductDescription(table);

        // Populate full PDF structure
        document.add(table);
        document.close();

        System.out.println("PDF Table Generated Successfully!");
    }

    private static void generateAssortmentItems(Table invoiceTable) {
        List<Cell[]> rowsForShippingMarks = new ArrayList<>();

        StringBuilder assortmentBuilder = new StringBuilder();
        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());

        createRowForIncomeTable(DUMMY_TEXT, assortmentBuilder.toString(), "1", "14", "42",
                "588", DUMMY_TEXT, "1175.0508", "49352.1336", "PALLET(S)",
                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);

    }

    private static void generateProductDescription(Table invoiceTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());


        createRowForIncomeTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);


    }

    private static void generateShippingMarksRow(String shippingMarks, Table invoiceTable) {

//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
//        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total

        createRowForIncomeTable(shippingMarks, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
                DUMMY_TEXT, DUMMY_TEXT , invoiceTable);
    }

    private static void createRowForIncomeTable(String shippingMarks, String assortment, String whsepack,
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
        Paragraph paragraph = new Paragraph(content).setFontSize(8).setPaddingLeft(5);
        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
//        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.LEFT);
        return cell;
    }
}

