package com.schrodinger.interpreter.visitor.expression;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

import java.util.Objects;

import static com.schrodinger.interpreter.Helpers.getOperationValue;

public class AssignmentExpressionVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.ASSIGNMENT_EXPRESSION);
        assert node.getLeft() != null;

        final String variableName = node.getLeft().getName();
        Object value = null;
        final Scope currentScope = DependencyModule.getCurrentScope();

        if (Objects.nonNull(node.getRight())) {
            value = DependencyModule.getVisitor(node.getRight().getType()).visitNode(node.getRight());
        }

        if (Objects.nonNull(variableName) && Objects.nonNull(node.getOperator())) {
            final Object variableValue = currentScope.getVariableValue(variableName);
            if(Objects.isNull(variableValue) && !Objects.equals(node.getOperator(), "=")) {
                throw new RuntimeException("Nalla operand " + node.getOperator() + " ke saath kaam nahi karta");
            }
            if(variableValue instanceof Boolean && !Objects.equals(node.getOperator(), "=")) {
                throw new RuntimeException("Boolean ke saath " + node.getOperator() + " kaam nahi karta");
            }
            final Object newValue = getOperationValue(variableValue, value, node.getOperator());
            currentScope.assignValueToVariable(variableName, newValue);
            return currentScope.getVariableValue(variableName);
        }

        return null;
    }
}
