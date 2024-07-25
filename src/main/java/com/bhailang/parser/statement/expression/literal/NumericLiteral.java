package com.bhailang.parser.statement.expression.literal;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;

public class NumericLiteral extends Literal {
    public NumericLiteral(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getLiteral() {
        final Token token = tokenExecutor.eatTokenAndForwardLookahead(TokenType.NUMBER_TYPE);
        return ASTNode.builder().type(NodeType.NUMERIC_LITERAL).numberValue(Double.parseDouble(token.getValue())).build();
    }
}
