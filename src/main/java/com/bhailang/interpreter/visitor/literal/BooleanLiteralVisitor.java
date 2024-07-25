package com.bhailang.interpreter.visitor.literal;

import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

public class BooleanLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.BOOLEAN_LITERAL);
        assert node.getStringValue().equals("sahi") || node.getStringValue().equals("galat");
        return node.getStringValue();
    }
}
