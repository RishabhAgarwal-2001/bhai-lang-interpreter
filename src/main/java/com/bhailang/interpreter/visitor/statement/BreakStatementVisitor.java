package com.bhailang.interpreter.visitor.statement;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.Scope;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

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
