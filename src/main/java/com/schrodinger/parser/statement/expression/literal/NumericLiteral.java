package com.schrodinger.parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

public class NumericLiteral extends Literal {
    public NumericLiteral(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getLiteral() {
        final Token token = tokenExecutor.eatTokenAndForwardLookahead(TokenType.NUMBER_TYPE);
        return ASTNode.builder().type(NodeType.NUMERIC_LITERAL).numberValue(Float.parseFloat(token.getValue())).build();
    }
}
