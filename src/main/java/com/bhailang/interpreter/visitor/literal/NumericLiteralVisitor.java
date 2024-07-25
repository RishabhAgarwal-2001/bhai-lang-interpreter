package com.bhailang.interpreter.visitor.literal;

import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

public class NumericLiteralVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getType().equals(NodeType.NUMERIC_LITERAL);
        return node.getNumberValue();
    }
}
