package parser.statement.expression.literal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;
import com.bhailang.tokenizer.Tokenizer;

public class NumericLiteralTest {

    @ParameterizedTest
    @ValueSource(strings = {"123", "-456", "78.90", "-0.12"})
    void testGetLiteral_ValidNumericLiteral(final String stringToTokenize) {
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode numericLiteralNode = DependencyModule.getNumericLiteral().getLiteral();

        assert numericLiteralNode != null;
        assert numericLiteralNode.getType() == NodeType.NUMERIC_LITERAL;
        assert numericLiteralNode.getNumberValue() == Double.parseDouble(stringToTokenize);
    }

    @Test
    void testGetLiteral_InvalidNumericLiteral() {
        final String stringToTokenize = "not a number";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getNumericLiteral().getLiteral();
            assert false;
        } catch (RuntimeException e) {
            assert e.getMessage().equals("kya kar rha hai tu??...Unexpected token, expected " + TokenType.NUMBER_TYPE + " but found " + TokenType.IDENTIFIER_TYPE);
        }
    }
}