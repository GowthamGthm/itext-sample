
package com.gthm.itext.model;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdfResponse {

    @JsonProperty("cmrcl_invc_id")
    public String cmrclInvcId;

    @JsonProperty("invoice_nbr")
    public String invoiceNbr;

    @JsonProperty("invoice_date")
    public String invoiceDate;

    @JsonProperty("purchase_cmpny_id")
    public String purchaseCmpnyId;

    @JsonProperty("supplier_id")
    public String supplierId;

    @JsonProperty("consignee_country_code")
    public String consigneeCountryCode;

    @JsonProperty("vessel_name")
    public String vesselName;

    @JsonProperty("transport_mode_code")
    public String transportModeCode;

    @JsonProperty("voyage_nbr")
    public String voyageNbr;

    @JsonProperty("incoterm_code")
    public String incotermCode;

    @JsonProperty("place_of_poss_code")
    public String placeOfPossCode;

    @JsonProperty("place_of_poss_name")
    public String placeOfPossName;

    @JsonProperty("etd_date")
    public String etdDate;

    @JsonProperty("dest_country_code")
    public String destCountryCode;

    @JsonProperty("loading_port_name")
    public String loadingPortName;

    @JsonProperty("destnaton_cmpny_id")
    public String destnatonCmpnyId;

    @JsonProperty("final_dest_desc")
    public String finalDestDesc;

    @JsonProperty("shipper_name")
    public String shipperName;

    @JsonProperty("comment1_txt")
    public String comment1Txt;

    @JsonProperty("preparer_name")
    public String preparerName;

    @JsonProperty("preparer_job_title_name")
    public String preparerJobTitleName;

    @JsonProperty("last_change_ts")
    public String lastChangeTs;

    @JsonProperty("last_change_userid")
    public String lastChangeUserid;

    @JsonProperty("create_ts")
    public String createTs;

    @JsonProperty("orign_country_code")
    public String orignCountryCode;

    @JsonProperty("loading_country_code")
    public String loadingCountryCode;

    @JsonProperty("line_items")
    public List<LineItem> lineItems;

    @JsonProperty("contacts")
    public List<Contact> contacts;

    @JsonProperty("certification_clause")
    public List<Object> certificationClause;

    @JsonProperty("vendor_item")
    public List<VendorItem> vendorItem;

    @JsonProperty("vendor_item_factory")
    public List<VendorItemFactory> vendorItemFactory;

}