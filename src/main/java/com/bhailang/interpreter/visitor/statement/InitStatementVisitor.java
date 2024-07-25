package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

public class InitStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.nonNull(node.getBody())) {
            for(final ASTNode statement: node.getBody()) {
                DependencyModule.getVisitor(statement.getType()).visitNode(statement);
            }
        }
        return null;
    }
}
