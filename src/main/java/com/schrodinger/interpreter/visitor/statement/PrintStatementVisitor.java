package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

import java.util.Objects;

public class PrintStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.isNull(node.getExpressions())) {
            throw new RuntimeException("No Expressions to print");
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for(final ASTNode expression: node.getExpressions()) {
            Object result = DependencyModule.getVisitor(expression.getType()).visitNode(expression);
            if(Objects.isNull(result)) {
                result = "nalla";
            } else if(result instanceof Boolean && result.equals(true)) {
                result = "sahi";
            } else if(result instanceof Boolean && result.equals(false)) {
                result = "galat";
            }
            stringBuilder.append(result);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder);
        return null;
    }
}
