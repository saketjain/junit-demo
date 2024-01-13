package com.ps.javacraft.calculator;

import java.util.Arrays;

/**
 * Calculator Service
 */
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if(b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return a / b;
    }

    public int square(int a) {
        return a * a;
    }

    public double total(double[] numbers) {
        if(numbers.length > 0) {
            return Arrays.stream(numbers).reduce(0, (double a, double b) -> a + b);
        } else {
            return 0;
        }
    }
}
