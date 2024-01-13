package com.ps.javacraft.portfolio;

import com.ps.javacraft.calculator.CalculatorService;

import java.util.List;

public class PortfolioService {

    private CalculatorService calculatorService;

    private MarketPriceService marketPriceService;

    public PortfolioService(MarketPriceService marketPriceService, CalculatorService calculatorService) {
        this.marketPriceService = marketPriceService;
        this.calculatorService = calculatorService;
    }

    public double calculateMarketValue(List<Stock> stocks) {

        stocks.forEach(s -> {
            s.setPrice(marketPriceService.getLatestStockPrice(s.getSymbol()));
        });

        double[] value = stocks.stream().map(s -> s.getQty() * s.getPrice()).mapToDouble(d -> d).toArray();
        return calculatorService.total(value);

    }
}
