package com.schrodinger.interpreter;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Map<String, Object> variableNameToValueMap = new HashMap<>();
    @Getter @Setter
    private boolean isLoop = false;
    @Getter @Setter
    private boolean isBreakStatement = false;
    @Getter @Setter
    private boolean isContinueStatement = false;
    private Scope parentScope;

    public Scope(final Scope parentScope) {
        this.parentScope = parentScope;
    }

    public Object getVariableValue(final String variableName) {
        if(variableNameToValueMap.containsKey(variableName)) {
            return variableNameToValueMap.get(variableName);
        }
        if(parentScope != null) {
            return parentScope.getVariableValue(variableName);
        }
        throw new RuntimeException("Variable " + variableName + " bana toh leh pehle!");
    }

    public void assignValueToVariable(final String variableName, final Object value) {
        if(variableNameToValueMap.containsKey(variableName)) {
            variableNameToValueMap.put(variableName, value);
            return;
        }
        if(parentScope != null) {
            parentScope.assignValueToVariable(variableName, value);
            return;
        }
        throw new RuntimeException("Variable " + variableName + " bana toh leh pehle, fir assign krna!");
    }

    public void declareVariable(final String variableName, final Object value) {
        if(variableNameToValueMap.containsKey(variableName)) {
            throw new RuntimeException("Variable " + variableName + " pehle se hi declare ho chuka hai!");
        }
        variableNameToValueMap.put(variableName, value);
    }
}
