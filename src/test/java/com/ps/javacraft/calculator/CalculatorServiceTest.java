package com.ps.javacraft.calculator;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;


/**
 * Unit test for simple App.
 */
public class CalculatorServiceTest {

    static Logger logger = Logger.getLogger(CalculatorServiceTest.class.getName());

    CalculatorService app = new CalculatorService();

    @BeforeAll
    static void setup() {
        logger.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        logger.info("@BeforeEach - executes before each test method in this class");
    }

    @Test
    public void testAdd() {
        int answer = app.add(1, 2);
        Assertions.assertEquals(3, answer);
    }

    @Test
    public void testSubtraction() {
        int answer = app.subtract(3, 2);
        Assertions.assertEquals(1, answer);
    }


    @DisplayName("Multiplication Test")
    @Test
    public void testMultiply() {
        int answer = app.multiply(4, 2);
        Assertions.assertEquals(8, answer);
    }

    @Disabled
    @Test
    public void testDivide() {
        int answer = app.divide(4, 2);
        Assertions.assertEquals(2, answer);
    }

    @AfterEach
    void tearDown() {
        logger.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        logger.info("@AfterAll - executed after all test methods.");
    }

    @Test
    void lambdaExpressions() {
        Assertions.assertEquals(5, app.add(1, 4), () -> "Sum should be equal to 5");
    }

    @Test
    void testExceptions() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> app.divide(5, 0));
        Assertions.assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSquare(int a) {
        int expected = a * a;
        Assertions.assertEquals(expected, app.square(a));

    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "4,5,9"})
    void testAdd(ArgumentsAccessor argumentsAccessor) {
        int a = argumentsAccessor.getInteger(0);
        int b = argumentsAccessor.getInteger(1);
        int expected = argumentsAccessor.getInteger(2);
        Assertions.assertEquals(expected, app.add(a, b));

    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "4, 5, 9"})
    void test(@AggregateWith(InputAggregator.class) int[] input) {
        int a = input[0];
        int b = input[1];
        int expected = input[2];
        Assertions.assertEquals(expected, app.add(a, b));

    }
}
