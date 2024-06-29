package parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
