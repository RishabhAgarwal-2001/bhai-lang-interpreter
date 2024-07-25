package com.schrodinger.interpreter.visitor.literal;

import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

public class BooleanLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.BOOLEAN_LITERAL);
        assert node.getStringValue().equals("sahi") || node.getStringValue().equals("galat");
        return node.getStringValue();
    }
}
