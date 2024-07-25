package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

import java.util.Objects;

public class ExpressionStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.nonNull(node.getExpression())) {
            DependencyModule.getVisitor(node.getExpression().getType()).visitNode(node.getExpression());
        }
        return null;
    }
}
