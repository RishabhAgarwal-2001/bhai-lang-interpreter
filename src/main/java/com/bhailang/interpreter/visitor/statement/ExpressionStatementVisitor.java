package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

public class ExpressionStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.nonNull(node.getExpression())) {
            DependencyModule.getVisitor(node.getExpression().getType()).visitNode(node.getExpression());
        }
        return null;
    }
}
