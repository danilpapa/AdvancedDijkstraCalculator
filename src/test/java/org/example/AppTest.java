package org.example;

import cmath.UpdatedCalculator;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private UpdatedCalculator updatedCalculator;

    @BeforeEach
    void setUpdatedCalculator() {
        updatedCalculator = new UpdatedCalculator();
    }

    @Test
    void testBasicArithmetic() {
        assertEquals(2.0, UpdatedCalculator.eval("1.0 + 1.0", false));
        assertEquals(0.0, UpdatedCalculator.eval("1.0 - 1.0", false));
        assertEquals(2.0, UpdatedCalculator.eval("1.0 * 2.0", false));
        assertEquals(0.5, UpdatedCalculator.eval("1.0 / 2.0", false));
        assertEquals(2.0, UpdatedCalculator.eval("1.0 / 0.5", false));
    }

    @Test
    void testTrigonometricFunctions() {
        assertEquals(Math.sin(90.0), UpdatedCalculator.eval("sin(90.0)", false));
        assertEquals(Math.cos(90.0), UpdatedCalculator.eval("cos(90.0)", false));
        assertEquals(Math.tan(45.0), UpdatedCalculator.eval("tan(45.0)", false));
    }

    @Test
    void testExponentiation() {
        assertEquals(Math.pow(2.0, 3.0), UpdatedCalculator.eval("2.0 ^ 3.0", false));
        assertEquals(Math.pow(Math.sin(45.0), 2) + Math.pow(Math.cos(45.0), 2), UpdatedCalculator.eval("sin45.0 ^ (2.0) + cos(45.0) ^ 2.0", false));
    }

    @Test
    void testComplexExpression() {
        assertEquals(16.0, UpdatedCalculator.eval("2.0 ^ ((8.0 * 2.0) / 4.0)", false));
    }

    @Test
    void testErrorHandling() {
        assertThrows(Exception.class, () -> UpdatedCalculator.eval("(1.0 +", false));
    }

}
