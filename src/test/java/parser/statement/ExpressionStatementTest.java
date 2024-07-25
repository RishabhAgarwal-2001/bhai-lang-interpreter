package parser.statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpressionStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_validStatement_assignmentExpression() {
        final String stringToTokenize = "a = b;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getExpressionStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.EXPRESSION_STATEMENT, astNode.getType());
        assertEquals(NodeType.ASSIGNMENT_EXPRESSION, astNode.getExpression().getType());
    }

    @Test
    void testGetStatement_validStatement_additiveExpression() {
        final String stringToTokenize = "a + b;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getExpressionStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.EXPRESSION_STATEMENT, astNode.getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getExpression().getType());
    }
}
