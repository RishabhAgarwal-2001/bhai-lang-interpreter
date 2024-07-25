package com.bhailang.interpreter.visitor.statement;

import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

public class EmptyStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        return null;
    }
}
