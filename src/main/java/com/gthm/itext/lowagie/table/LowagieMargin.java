package com.gthm.itext.lowagie.table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LowagieMargin {

    Float left;
    Float right;
    Float top;
    Float bottom;

    public LowagieMargin() {
       left = right = top = bottom = 0f;
    }

}