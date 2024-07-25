package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

public class BlockStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        final Scope parentScope = DependencyModule.getCurrentScope();
        DependencyModule.setCurrentScope(new Scope(parentScope));
        DependencyModule.getCurrentScope().setLoop(parentScope.isLoop());
        for(final ASTNode statement: node.getBody()) {
            if(DependencyModule.getCurrentScope().isBreakStatement()) {
                parentScope.setBreakStatement(true);
                break;
            }
            if(DependencyModule.getCurrentScope().isContinueStatement()) {
                parentScope.setContinueStatement(true);
                break;
            }
            DependencyModule.getVisitor(statement.getType()).visitNode(statement);
        }
        DependencyModule.setCurrentScope(parentScope);
        return null;
    }
}
