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

public class LogicalOrExpressionTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetExpression_validExpression_TwoOperands() {
        final String stringToTokenize = "a || b";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getLogicalORExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.LOGICAL_EXPRESSION, astNode.getType());
        assertEquals("||", astNode.getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("a", astNode.getLeft().getName());
        assertEquals("b", astNode.getRight().getName());
    }

    @Test
    void testGetExpression_validExpression_TwoComplexOperands() {
        final String stringToTokenize = "a && b || c <= 5";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getLogicalORExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.LOGICAL_EXPRESSION, astNode.getType());
        assertEquals("||", astNode.getOperator());
        assertEquals(NodeType.LOGICAL_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getRight().getType());
        assertEquals("&&", astNode.getLeft().getOperator());
        assertEquals("<=", astNode.getRight().getOperator());
    }
}
