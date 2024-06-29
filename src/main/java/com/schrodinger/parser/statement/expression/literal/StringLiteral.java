package com.schrodinger.parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

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
