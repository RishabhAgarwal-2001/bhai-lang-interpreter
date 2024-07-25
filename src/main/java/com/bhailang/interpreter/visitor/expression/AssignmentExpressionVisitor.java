package com.bhailang.interpreter.visitor.expression;

import static com.bhailang.interpreter.Helpers.getOperationValue;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.Scope;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

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
