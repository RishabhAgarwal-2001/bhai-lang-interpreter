package com.bhailang.interpreter.visitor.statement;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.Scope;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

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
