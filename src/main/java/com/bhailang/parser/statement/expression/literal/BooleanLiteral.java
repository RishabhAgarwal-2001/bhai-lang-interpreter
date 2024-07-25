package com.bhailang.parser.statement.expression.literal;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;

public class BooleanLiteral extends Literal {
    public BooleanLiteral(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getLiteral() {
        final Token token = tokenExecutor.eatTokenAndForwardLookahead(TokenType.BOOLEAN_TYPE);
        return ASTNode.builder().type(NodeType.BOOLEAN_LITERAL).stringValue(token.getValue()).build();
    }
}
