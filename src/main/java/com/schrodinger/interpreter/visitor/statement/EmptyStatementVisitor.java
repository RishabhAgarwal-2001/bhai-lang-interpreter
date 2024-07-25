package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

public class EmptyStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        return null;
    }
}
