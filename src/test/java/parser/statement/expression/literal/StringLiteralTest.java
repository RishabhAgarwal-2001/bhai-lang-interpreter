package parser.statement.expression.literal;

import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;
import com.bhailang.tokenizer.Tokenizer;

public class StringLiteralTest {

    @Test
    void testGetLiteral_ValidStringLiteral() {
        final String stringToTokenize = "\"This is a valid string literal\"";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode stringLiteralNode = DependencyModule.getStringLiteral().getLiteral();

        assert stringLiteralNode != null;
        assert stringLiteralNode.getType() == NodeType.STRING_LITERAL;
        assert stringLiteralNode.getStringValue().equals("This is a valid string literal");
    }

    @Test
    void testGetLiteral_InvalidStringLiteral() {
        final String stringToTokenize = "{}";
        final Tokenizer tokenizer = DependencyModule.getTokenizer();
        tokenizer.initTokenizer(stringToTokenize);
        final TokenExecutor tokenExecutor = DependencyModule.getTokenExecutor();
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getStringLiteral().getLiteral();
            assert false;
        } catch (RuntimeException e) {
            assert e.getMessage().equals("kya kar rha hai tu??...Unexpected token, expected " + TokenType.STRING_TYPE + " but found " + TokenType.OPEN_CURLY_BRACE_TYPE);
        }
    }

}
