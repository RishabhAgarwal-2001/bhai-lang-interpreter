package com.bhailang.parser.statement.expression.literal;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

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
