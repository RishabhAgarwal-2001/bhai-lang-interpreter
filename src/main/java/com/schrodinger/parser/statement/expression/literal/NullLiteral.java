package com.schrodinger.parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class NullLiteral extends Literal {
    public NullLiteral(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getLiteral() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.NALLA_TYPE);
        return ASTNode.builder().type(NodeType.NULL_LITERAL).stringValue("nalla").build();
    }
}
