package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

import java.util.Objects;

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
