package com.bhailang.interpreter.visitor.expression;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.Scope;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

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
