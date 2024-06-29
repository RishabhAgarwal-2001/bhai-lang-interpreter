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

public class WhileStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement() {
        final String stringToTokenize = "jab tak bhai (a < 5) { bol bhai \"a is less than 5\"; }";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getWhileStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.WHILE_STATEMENT, astNode.getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getTest().getType());
        assertEquals("<", astNode.getTest().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getTest().getLeft().getType());
        assertEquals(5, astNode.getTest().getRight().getNumberValue());
        assertEquals(1, astNode.getBody().size());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getBody().getFirst().getType());
    }
}
