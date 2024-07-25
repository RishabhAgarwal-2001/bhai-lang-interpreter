package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

public class ContinueStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        final Scope currentScope = DependencyModule.getCurrentScope();
        if(currentScope.isLoop()) {
            currentScope.setContinueStatement(true);
        } else {
            throw new RuntimeException("Kha \"agla dekh bhai\"?? Loop kidhar hai?");
        }
        return null;
    }
}
