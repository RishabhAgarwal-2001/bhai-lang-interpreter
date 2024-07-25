package parser.statement.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.parser.statement.expression.MultiplicativeExpression;
import com.bhailang.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MultiplicativeExpressionTest {

    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;
    private MultiplicativeExpression multiplicativeExpression;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
        multiplicativeExpression = DependencyModule.getMultiplicativeExpression();
    }

    @Test
    void testGetExpression_validExpression_TwoOperands() {
        final String stringToTokenize = "a * b";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = multiplicativeExpression.getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getType());
        assertEquals("*", astNode.getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("a", astNode.getLeft().getName());
        assertEquals("b", astNode.getRight().getName());
    }

    @Test
    void testGetExpression_validExpression_ThreeOperands() {
        final String stringToTokenize = "a * 5 * b";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = multiplicativeExpression.getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getType());
        assertEquals("*", astNode.getOperator());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("*", astNode.getLeft().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getLeft().getType());
        assertEquals(NodeType.NUMERIC_LITERAL, astNode.getLeft().getRight().getType());
        assertEquals("a", astNode.getLeft().getLeft().getName());
        assertEquals(Float.parseFloat("5"), astNode.getLeft().getRight().getNumberValue());
    }
}
