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
public class Contact {

    @JsonProperty("contact_type_desc")
    public String contactTypeDesc;

    @JsonProperty("contact_name")
    public String contactName;

    @JsonProperty("addr_line_1")
    public String addrLine1;

    @JsonProperty("addr_line_2")
    public String addrLine2;

    @JsonProperty("addr_line_3")
    public String addrLine3;

    @JsonProperty("addr_line_4")
    public String addrLine4;

    @JsonProperty("city_name")
    public String cityName;

    @JsonProperty("state_prov_code")
    public String stateProvCode;

    @JsonProperty("state_prov_name")
    public String stateProvName;

    @JsonProperty("postal_code")
    public String postalCode;

    @JsonProperty("country_code")
    public String countryCode;

    @JsonProperty("phone_nbr")
    public String phoneNbr;

    @JsonProperty("fax_nbr")
    public String faxNbr;

    @JsonProperty("email_addr_id")
    public String emailAddrId;

    @JsonProperty("last_change_ts")
    public String lastChangeTs;

}