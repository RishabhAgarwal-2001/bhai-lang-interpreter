package interpreter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bhailang.interpreter.Helpers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpersTest {

    @Test
    void testAdditionWithNumbers() {
        assertEquals(30.0, Helpers.getOperationValue(10.0, 20.0, "+"));
    }

    @Test
    void testStringConcatenation() {
        assertEquals("HelloWorld", Helpers.getOperationValue("Hello", "World", "+"));
    }

    @Test
    void testDivisionByZeroThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> Helpers.getOperationValue(10.0, 0.0, "/"));
        assertTrue(exception.getMessage().contains("Zero se divide nahi krr skte bhai!"));
    }

    @Test
    void testUnsupportedOperationThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> Helpers.getOperationValue(10, 20, "^"));
        assertTrue(exception.getMessage().contains("Unsupported operator"));
    }

    @Test
    void testLogicalAndWithBooleans() {
        assertEquals(true, Helpers.getOperationValue(true, true, "&&"));
    }

    @Test
    void testSubtractionWithNumbers() {
        assertEquals(10.0, Helpers.getOperationValue(30.0, 20.0, "-"));
    }

    @Test
    void testMultiplicationWithNumbers() {
        assertEquals(200.0, Helpers.getOperationValue(10.0, 20.0, "*"));
    }

    @Test
    void testDivisionWithNumbers() {
        assertEquals(2.0, Helpers.getOperationValue(40.0, 20.0, "/"));
    }

    @Test
    void testModuloWithNumbers() {
        assertEquals(1.0, Helpers.getOperationValue(21.0, 20.0, "%"));
    }

    @Test
    void testEqualityWithNumbers() {
        assertEquals(true, Helpers.getOperationValue(20.0, 20.0, "=="));
    }

    @Test
    void testInequalityWithNumbers() {
        assertEquals(true, Helpers.getOperationValue(20.0, 30.0, "!="));
    }

    @Test
    void testGreaterThanWithNumbers() {
        assertEquals(true, Helpers.getOperationValue(30.0, 20.0, ">"));
    }

    @Test
    void testLessThanWithNumbers() {
        assertEquals(true, Helpers.getOperationValue(10.0, 20.0, "<"));
    }

    @Test
    void testLogicalOrWithBooleans() {
        assertEquals(true, Helpers.getOperationValue(false, true, "||"));
    }

    @Test
    void testStringAndNumberConcatenation() {
        assertEquals("Hello10.0", Helpers.getOperationValue("Hello", 10.0, "+"));
    }

    @Test
    void testStringGreaterThan() {
        assertEquals(false, Helpers.getOperationValue("abc", "def", ">"));
    }

    @Test
    void testStringLessThan() {
        assertEquals(true, Helpers.getOperationValue("abc", "def", "<"));
    }
}