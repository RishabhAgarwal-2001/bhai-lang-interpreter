package parser.statement.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssignmentExpressionTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetExpression_validExpression_TwoOperands() {
        final String stringToTokenize = "a = b";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getAssignmentExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.ASSIGNMENT_EXPRESSION, astNode.getType());
        assertEquals("=", astNode.getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getRight().getType());
        assertEquals("a", astNode.getLeft().getName());
        assertEquals("b", astNode.getRight().getName());
    }

    @Test
    void testGetExpression_validExpression_ThreeOperands() {
        final String stringToTokenize = "a = b = c";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getAssignmentExpression().getExpression();

        assertNotNull(astNode);
        assertEquals(NodeType.ASSIGNMENT_EXPRESSION, astNode.getType());
        assertEquals("=", astNode.getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getLeft().getType());
        assertEquals(NodeType.ASSIGNMENT_EXPRESSION, astNode.getRight().getType());
        assertEquals("a", astNode.getLeft().getName());
        assertEquals("b", astNode.getRight().getLeft().getName());
        assertEquals("c", astNode.getRight().getRight().getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a + b = c", "5 = a", "6 = 5"})
    void testGetExpression_invalidExpression(final String stringToTokenize) {
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getAssignmentExpression().getExpression();
        } catch (Exception e) {
            assertEquals("Kya krr raha hai bhai tu?", e.getMessage());
        }
    }
}
