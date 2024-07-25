package interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhailang.interpreter.Scope;

import static org.junit.jupiter.api.Assertions.*;

public class ScopeTest {
    private Scope scope;

    @BeforeEach
    void setUp() {
        scope = new Scope(null); // Initialize a new scope with no parent
    }

    @Test
    void testDeclareVariable() {
        scope.declareVariable("x", 10);
        assertEquals(10, scope.getVariableValue("x"));
    }

    @Test
    void testDeclareVariableTwiceThrowsException() {
        scope.declareVariable("x", 10);
        Exception exception = assertThrows(RuntimeException.class, () -> scope.declareVariable("x", 20));
        assertTrue(exception.getMessage().contains("pehle se hi declare ho chuka hai"));
    }

    @Test
    void testAssignValueToDeclaredVariable() {
        scope.declareVariable("x", 10);
        scope.assignValueToVariable("x", 20);
        assertEquals(20, scope.getVariableValue("x"));
    }

    @Test
    void testAssignValueToUndeclaredVariableThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> scope.assignValueToVariable("y", 20));
        assertTrue(exception.getMessage().contains("bana toh leh pehle, fir assign krna"));
    }

    @Test
    void testGetVariableValueFromParentScope() {
        Scope parentScope = new Scope(null);
        parentScope.declareVariable("x", 10);
        Scope childScope = new Scope(parentScope);
        assertEquals(10, childScope.getVariableValue("x"));
    }

    @Test
    void testVariableShadowing() {
        Scope parentScope = new Scope(null);
        parentScope.declareVariable("x", 10);
        Scope childScope = new Scope(parentScope);
        childScope.declareVariable("x", 20); // Shadowing parent scope variable
        assertEquals(20, childScope.getVariableValue("x"));
        assertEquals(10, parentScope.getVariableValue("x"));
    }
}