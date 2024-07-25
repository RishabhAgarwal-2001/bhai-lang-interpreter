package com.schrodinger.interpreter.visitor.expression;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

import java.util.Objects;

public class IdentifierExpressionVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.IDENTIFIER_EXPRESSION);
        if(Objects.isNull(node.getName())) {
            throw new RuntimeException("Identifier name is null");
        }
        final Scope currentScope = DependencyModule.getCurrentScope();
        final Object variableValue = currentScope.getVariableValue(node.getName());
        if(Objects.isNull(variableValue)) {
            return "nalla";
        }
        if(variableValue instanceof Boolean && variableValue.equals(true)) {
            return "sahi";
        }
        if(variableValue instanceof Boolean && variableValue.equals(false)) {
            return "galat";
        }
        return variableValue;
    }
}
