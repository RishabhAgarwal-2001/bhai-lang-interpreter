package com.schrodinger.interpreter.visitor.literal;

import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

public class StringLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.STRING_LITERAL);
        return node.getStringValue();
    }
}
