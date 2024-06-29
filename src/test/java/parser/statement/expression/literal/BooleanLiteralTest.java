package parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BooleanLiteralTest {

    @ParameterizedTest
    @ValueSource(strings = {"sahi", "galat"})
    void testGetLiteral_ValidBooleanLiteral(final String stringToTokenize) {
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode booleanLiteralNode = DependencyModule.getBooleanLiteral().getLiteral();

        assert booleanLiteralNode != null;
        assert booleanLiteralNode.getType() == NodeType.BOOLEAN_LITERAL;
        assert booleanLiteralNode.getStringValue().equals(stringToTokenize);
    }

    @Test
    void testGetLiteral_InvalidBooleanLiteral() {
        final String stringToTokenize = "true";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getBooleanLiteral().getLiteral();
            assert false;
        } catch (RuntimeException e) {
            assert e.getMessage().equals("kya kar rha hai tu??...Unexpected token, expected " + TokenType.BOOLEAN_TYPE + " but found " + TokenType.IDENTIFIER_TYPE);
        }
    }
}
