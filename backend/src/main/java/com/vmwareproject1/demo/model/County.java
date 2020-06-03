package com.vmwareproject1.demo.model;

import lombok.Builder;

@Builder
public class County {
    private String state;
    private String county;

    private double taxRate;
    private double exportDollarAmount;
    private int population;
    private double unemployment;
    private double income;
}
