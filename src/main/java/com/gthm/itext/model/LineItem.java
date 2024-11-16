package com.gthm.itext.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineItem {

    @JsonProperty("line_nbr")
    public String lineNbr;

    @JsonProperty("item_nbr")
    public String itemNbr;

    @JsonProperty("host_po_nbr")
    public String hostPoNbr;

    @JsonProperty("item_case_upc_nbr")
    public String itemCaseUpcNbr;

    @JsonProperty("item_desc")
    public String itemDesc;

    @JsonProperty("additional_item_desc")
    public String additionalItemDesc;

    @JsonProperty("whse_pack_ea_qty")
    public String whsePackEaQty;

    @JsonProperty("vndr_pack_ea_qty")
    public String vndrPackEaQty;

    @JsonProperty("vndr_pack_qty")
    public String vndrPackQty;

    @JsonProperty("vndr_pack_net_wgt_qty")
    public String vndrPackNetWgtQty;

    @JsonProperty("vndr_pack_gross_wgt_qty")
    public String vndrPackGrossWgtQty;

    @JsonProperty("ea_net_first_cost_amt")
    public String eaNetFirstCostAmt;

    @JsonProperty("shipping_marks_txt")
    public String shippingMarksTxt;

    @JsonProperty("dept_nbr")
    public String deptNbr;

    @JsonProperty("po_type_code")
    public String poTypeCode;

    @JsonProperty("pack_type_code")
    public String packTypeCode;

    @JsonProperty("last_change_ts")
    public String lastChangeTs;

    @JsonProperty("item_review_ind")
    public String itemReviewInd;

    @JsonProperty("assortment_type_code")
    public String assortmentTypeCode;

    @JsonProperty("pack_nbr")
    public String packNbr;

    @JsonProperty("first_sale_amt")
    public String firstSaleAmt;

    @JsonProperty("assortment_type_ind")
    public String assortmentTypeInd;

    @JsonProperty("cntry_of_origin")
    public String cntryOfOrigin;

}