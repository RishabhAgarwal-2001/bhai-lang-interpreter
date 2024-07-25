package com.bhailang.interpreter.visitor.literal;

import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

public class NullLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.NULL_LITERAL);
        assert node.getStringValue().equals("nalla");
        return node.getStringValue();
    }
}
