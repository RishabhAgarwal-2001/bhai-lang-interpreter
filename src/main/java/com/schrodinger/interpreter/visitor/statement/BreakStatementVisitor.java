package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

public class BreakStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        final Scope currentScope = DependencyModule.getCurrentScope();
        if(currentScope.isLoop()) {
            currentScope.setBreakStatement(true);
        } else {
            throw new RuntimeException("Kya \"bas kar bhai\"?? Loop kahan hai?");
        }
        return null;
    }
}
