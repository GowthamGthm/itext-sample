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
public class VendorItem {

    @JsonProperty("line_nbr")
    public String lineNbr;

    @JsonProperty("vendor_stock_id")
    public String vendorStockId;

    @JsonProperty("vendor_item_seq_nbr")
    public String vendorItemSeqNbr;

    @JsonProperty("last_change_ts")
    public String lastChangeTs;

}
