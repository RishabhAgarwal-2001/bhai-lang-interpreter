package com.schrodinger.interpreter.visitor.literal;

import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

public class NullLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.NULL_LITERAL);
        assert node.getStringValue().equals("nalla");
        return node.getStringValue();
    }
}
