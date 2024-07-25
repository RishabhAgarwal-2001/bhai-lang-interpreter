package com.schrodinger.interpreter.visitor.expression;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

import java.util.Objects;

import static com.schrodinger.interpreter.Helpers.getOperationValue;

public class BinaryAndLogicalExpressionVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.BINARY_EXPRESSION) || node.getType().equals(NodeType.LOGICAL_EXPRESSION);
        assert Objects.nonNull(node.getLeft());
        assert Objects.nonNull(node.getRight());
        assert Objects.nonNull(node.getOperator());

        if(node.getType().equals(NodeType.BINARY_EXPRESSION)) {
            return handleBinaryExpression(node);
        }

        return handleLogicalExpression(node);
    }

    private Object handleBinaryExpression(final ASTNode node) {
        if(!Objects.equals(node.getOperator(), "==") && !Objects.equals(node.getOperator(), "!=")) {
            checkForNallaOperands(node);
            checkForBooleanOperands(node);
        }
        final Object leftValue = getNodeValue(node.getLeft());
        final Object rightValue = getNodeValue(node.getRight());
        return getOperationValue(leftValue, rightValue, node.getOperator());
    }

    private Object handleLogicalExpression(final ASTNode node) {
        checkForNallaOperands(node);
        final Object leftValue = getNodeValue(node.getLeft());
        final Object rightValue = getNodeValue(node.getRight());
        return getOperationValue(leftValue, rightValue, node.getOperator());
    }

    private void checkForNallaOperands(final ASTNode node) {
        final RuntimeException runtimeException = new RuntimeException("Nalla operands ke saath %s operator kaam nahi karta".formatted(node.getOperator()));
        if(node.getLeft().getType().equals(NodeType.NULL_LITERAL) || node.getRight().getType().equals(NodeType.NULL_LITERAL)) {
            throw runtimeException;
        }
        if(node.getLeft().getType().equals(NodeType.IDENTIFIER_EXPRESSION) && Objects.nonNull(node.getLeft().getName())) {
            final Object variableValue = DependencyModule.getCurrentScope().getVariableValue(node.getLeft().getName());
            if(Objects.isNull(variableValue)) {
                throw runtimeException;
            }
        }
        if(node.getRight().getType().equals(NodeType.IDENTIFIER_EXPRESSION) && Objects.nonNull(node.getRight().getName())) {
            final Object variableValue = DependencyModule.getCurrentScope().getVariableValue(node.getRight().getName());
            if(Objects.isNull(variableValue)) {
                throw runtimeException;
            }
        }
    }

    private void checkForBooleanOperands(final ASTNode node) {
        final RuntimeException runtimeException = new RuntimeException("Boolean operands ke saath %s operator kaam nahi karta".formatted(node.getOperator()));
        if(node.getLeft().getType().equals(NodeType.BOOLEAN_LITERAL) || node.getRight().getType().equals(NodeType.BOOLEAN_LITERAL)) {
            throw runtimeException;
        }
        if(node.getLeft().getType().equals(NodeType.IDENTIFIER_EXPRESSION) && Objects.nonNull(node.getLeft().getName())) {
            final Object variableValue = DependencyModule.getCurrentScope().getVariableValue(node.getLeft().getName());
            if(variableValue instanceof Boolean) {
                throw runtimeException;
            }
        }
        if(node.getRight().getType().equals(NodeType.IDENTIFIER_EXPRESSION) && Objects.nonNull(node.getRight().getName())) {
            final Object variableValue = DependencyModule.getCurrentScope().getVariableValue(node.getRight().getName());
            if(variableValue instanceof Boolean) {
                throw runtimeException;
            }
        }
    }

    private Object getNodeValue(final ASTNode node) {
        if(node.getType().equals(NodeType.NULL_LITERAL)) {
            return null;
        }
        if(node.getType().equals(NodeType.BOOLEAN_LITERAL)) {
            return node.getStringValue().equals("sahi");
        }
        if(node.getType().equals(NodeType.IDENTIFIER_EXPRESSION) && Objects.nonNull(node.getName())) {
            return DependencyModule.getCurrentScope().getVariableValue(node.getName());
        }
        return DependencyModule.getVisitor(node.getType()).visitNode(node);
    }
}
