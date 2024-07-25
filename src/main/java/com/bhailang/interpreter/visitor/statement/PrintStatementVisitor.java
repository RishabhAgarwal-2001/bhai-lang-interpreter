package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

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
