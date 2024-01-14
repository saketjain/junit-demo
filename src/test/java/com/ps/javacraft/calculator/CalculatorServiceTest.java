package com.ps.javacraft.calculator;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;


/**
 * Unit test for Calculator.
 */
// @Execution(ExecutionMode.CONCURRENT)
public class CalculatorServiceTest {

    static Logger logger = Logger.getLogger(CalculatorServiceTest.class.getName());

    CalculatorService calculatorService = new CalculatorService();

    @BeforeAll
    static void setup() {
        //logger.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        //logger.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void tearDown() {
        //logger.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        //logger.info("@AfterAll - executed after all test methods.");
    }


    @Test
    public void testAdd() {
        int answer = calculatorService.add(1, 2);
        Assertions.assertEquals(3, answer);
    }

    @Test
    public void testSubtraction() {
        int answer = calculatorService.subtract(3, 2);
        Assertions.assertEquals(1, answer);
    }


    @DisplayName("Multiplication Test")
    @Test
    public void testMultiply() {
        int answer = calculatorService.multiply(4, 2);
        Assertions.assertEquals(8, answer);
    }

    //@Disabled
    @Test
    public void testDivide() {
        int answer = calculatorService.divide(4, 2);
        Assertions.assertEquals(2, answer);
    }

    @Test
    void lambdaExpressions() {
        Assertions.assertEquals(5, calculatorService.add(1, 4), () -> "Sum should be equal to 5");
    }

    @Test
    void testExceptions() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> calculatorService.divide(5, 0));
        Assertions.assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSquare(int a) {
        int expected = a * a;
        Assertions.assertEquals(expected, calculatorService.square(a));

    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "4,5,9"})
    void testAdd(ArgumentsAccessor argumentsAccessor) {
        int a = argumentsAccessor.getInteger(0);
        int b = argumentsAccessor.getInteger(1);
        int expected = argumentsAccessor.getInteger(2);
        Assertions.assertEquals(expected, calculatorService.add(a, b));

    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "4, 5, 9"})
    void test(@AggregateWith(InputAggregator.class) int[] input) {
        int a = input[0];
        int b = input[1];
        int expected = input[2];
        Assertions.assertEquals(expected, calculatorService.add(a, b));
    }

    //@Disabled
    @RepeatedTest(value=2, name="{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Test Add")
    public void testRepeated(RepetitionInfo info) {
        logger.info("Hello.." + info.getCurrentRepetition() + "");
        calculatorService.add(1, 2);
    }


    @Test
    public void testShiftUp(){
        System.out.println("Shift Up Value Before..." + calculatorService.getSHIFT());
        calculatorService.setSHIFT(1);
        System.out.println("Shift Up Value After..." + calculatorService.getSHIFT());
    }

    @Test
    public void testShiftDown(){
        System.out.println("Shift Down Value Before..." + calculatorService.getSHIFT());
        calculatorService.setSHIFT(1);
        System.out.println("Shift Down Value After..." + calculatorService.getSHIFT());
    }

}
