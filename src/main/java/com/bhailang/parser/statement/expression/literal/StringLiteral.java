package com.bhailang.parser.statement.expression.literal;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;

public class StringLiteral extends Literal {

    public StringLiteral(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getLiteral() {
        final Token token = tokenExecutor.eatTokenAndForwardLookahead(TokenType.STRING_TYPE);
        final String slicedValue = token.getValue().substring(1, token.getValue().length() - 1);
        return ASTNode.builder().type(NodeType.STRING_LITERAL).stringValue(slicedValue).build();
    }
}
