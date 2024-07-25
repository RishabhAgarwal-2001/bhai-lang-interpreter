package parser.statement.expression.literal;

import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;
import com.bhailang.tokenizer.Tokenizer;

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