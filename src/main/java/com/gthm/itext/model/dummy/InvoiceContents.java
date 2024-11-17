package com.gthm.itext.model.dummy;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceContents {

    private String shippingMarks;




    public static List<InvoiceContents> getDummyData() {

        List<InvoiceContents> invoiceContentsList = new ArrayList<>();

        String shiipingMarks = "To US Case ID\nPO # 2923267225\nPo Type # 43\nDept # 9\nItem # 595469863\nSupplier STK # BF-PC";
        InvoiceContents invoiceContents = InvoiceContents.builder()
                                               .shippingMarks(shiipingMarks)
                                               .build();
        invoiceContentsList.add(invoiceContents);
        return invoiceContentsList;

    }


}