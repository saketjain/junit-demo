package com.ps.javacraft.portfolio;

import com.ps.javacraft.calculator.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioTest {

    @Mock
    MarketPriceService marketPriceService;

    @Spy
    CalculatorService calculatorService = new CalculatorService();

    @InjectMocks
    PortfolioService portfolioService = new PortfolioService(marketPriceService, calculatorService);

    List<Stock> stocks = new ArrayList<>();

    @BeforeEach
    public void init() {
        this.setUpData();
        this.setupMock();
    }

    private void setUpData() {
        stocks.add(new Stock("AMAZON", 10, 0));
        stocks.add(new Stock("APPLE", 20, 0));
        stocks.add(new Stock("GOOGLE", 30, 0));
    }

    private void setupMock() {
        when(marketPriceService.getLatestStockPrice("AMAZON")).thenReturn(100.0);
        when(marketPriceService.getLatestStockPrice("APPLE")).thenReturn(200.0);
        when(marketPriceService.getLatestStockPrice("GOOGLE")).thenReturn(300.0);
    }

    @Test
    public void testPortfolioMarketValue() {
        double value = portfolioService.calculateMarketValue(stocks);
        Assertions.assertEquals(14000, value);
    }

    @Test
    public void testPortfolioMarketValueBehaviour() {
        double value = portfolioService.calculateMarketValue(stocks);
        verify(marketPriceService).getLatestStockPrice("AMAZON");
        verify(marketPriceService).getLatestStockPrice("APPLE");
        verify(marketPriceService).getLatestStockPrice("GOOGLE");
        verify(calculatorService).total(new double[]{1000, 4000, 9000});
        Assertions.assertEquals(14000, value);
    }
}
