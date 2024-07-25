package com.schrodinger.interpreter.visitor.literal;

import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

public class NumericLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.NUMERIC_LITERAL);
        return node.getNumberValue();
    }
}
