package com.gthm.itext.lowagie.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LowagiePadding {

    Float left;
    Float right;
    Float top;
    Float bottom;


    public LowagiePadding() {
       left = right = top = bottom = 0f;
    }

}