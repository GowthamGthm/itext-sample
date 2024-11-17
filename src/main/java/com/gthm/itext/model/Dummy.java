package com.gthm.itext.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Dummy {

    String column1;
    Integer column2;
    String column3;
    Integer column4;
    String column5;
    String column6;
    String column7;

    public Dummy() {
        column1 = RandomStringUtils.randomAlphabetic(5);
        column2 = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        column3 = RandomStringUtils.randomAlphabetic(5);
        column4 = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        column5 = RandomStringUtils.randomAlphabetic(5);
        column6 = RandomStringUtils.randomAlphabetic(5);
        column7 = RandomStringUtils.randomAlphabetic(5);
    }


    public static List<Dummy> getInstances(int length) {
        List<Dummy> list = new ArrayList();

        for(int i =0; i< length; i++) {
            list.add(new Dummy());
        }
        return list;
    }

}