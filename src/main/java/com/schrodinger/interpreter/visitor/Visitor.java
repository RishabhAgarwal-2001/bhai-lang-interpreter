package com.schrodinger.interpreter.visitor;

import com.schrodinger.parser.ASTNode;

public interface Visitor {
    Object visitNode(final ASTNode node);
}
