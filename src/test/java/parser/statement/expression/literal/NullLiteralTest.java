package parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.Test;

public class NullLiteralTest {

    @Test
    void testGetLiteral_ValidNullLiteral() {
        final String stringToTokenize = "nalla";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode nullLiteralNode = DependencyModule.getNullLiteral().getLiteral();

        assert nullLiteralNode != null;
        assert nullLiteralNode.getType() == NodeType.NULL_LITERAL;
        assert nullLiteralNode.getStringValue().equals(stringToTokenize);
    }

    @Test
    void testGetLiteral_InvalidNullLiteral() {
        final String stringToTokenize = "not null";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getNullLiteral().getLiteral();
            assert false;
        } catch (RuntimeException e) {
            assert e.getMessage().equals("kya kar rha hai tu??...Unexpected token, expected " + TokenType.NALLA_TYPE + " but found " + TokenType.IDENTIFIER_TYPE);
        }
    }
}