package parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryExpressionTest {

    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetExpression_ParenthesizedExpression() {
        final String stringToTokenize = "(1 + 2)";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.BINARY_EXPRESSION, node.getType());
        assertEquals("+", node.getOperator());
        assertEquals(NodeType.NUMERIC_LITERAL, node.getLeft().getType());
        assertEquals(NodeType.NUMERIC_LITERAL, node.getRight().getType());
    }

    @Test
    void testGetExpression_StringLiteral() {
        final String stringToTokenize = "\"sahi\"";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.STRING_LITERAL, node.getType());
        assertEquals("sahi", node.getStringValue());
    }

    @Test
    void testGetExpression_NumericLiteral() {
        final String stringToTokenize = "-123.4";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.NUMERIC_LITERAL, node.getType());
        assertEquals(Float.parseFloat("-123.4"), node.getNumberValue());
    }

    @Test
    void testGetExpression_BooleanLiteral() {
        final String stringToTokenize = "sahi";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.BOOLEAN_LITERAL, node.getType());
        assertEquals(stringToTokenize, node.getStringValue());
    }

    @Test
    void testGetExpression_NallaLiteral() {
        final String stringToTokenize = "nalla";
        tokenizer.initTokenizer(stringToTokenize);
        final Token token = tokenizer.getNextToken();
        tokenExecutor.setLookahead(token);

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.NULL_LITERAL, node.getType());
        assertEquals(stringToTokenize, node.getStringValue());

    }

    @Test
    void testGetExpression_IdentifierExpression() {
        final String stringToTokenize = "my_variable";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode node = DependencyModule.getPrimaryExpression().getExpression();

        assertNotNull(node);
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, node.getType());
        assertEquals(stringToTokenize, node.getName());
    }
}
