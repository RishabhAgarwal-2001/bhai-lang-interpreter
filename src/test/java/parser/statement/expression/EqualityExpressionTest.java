package parser.statement.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EqualityExpressionTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetExpression_validExpression_TwoOperands() {
        final String stringToTokenize = "a < b != c";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getEqualityExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getType());
        assertEquals("!=", astNode.getOperator());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("<", astNode.getLeft().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getRight().getType());
    }

    @Test
    void testGetExpression_validExpression_ThreeOperands() {
        final String stringToTokenize = "a < b != c + 5 == d";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getEqualityExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getType());
        assertEquals("==", astNode.getOperator());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("!=", astNode.getLeft().getOperator());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getLeft().getLeft().getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getLeft().getRight().getType());
        assertEquals("<", astNode.getLeft().getLeft().getOperator());
        assertEquals("+", astNode.getLeft().getRight().getOperator());
    }
}
