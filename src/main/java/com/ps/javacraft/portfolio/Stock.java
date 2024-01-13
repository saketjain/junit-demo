package com.ps.javacraft.portfolio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {

    private String symbol;

    private int qty;

    private double price;
}
