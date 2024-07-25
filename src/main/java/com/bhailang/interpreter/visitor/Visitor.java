package com.bhailang.interpreter.visitor;

import com.bhailang.parser.ASTNode;

public interface Visitor {
    Object visitNode(final ASTNode node);
}
