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

public class IfStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_singleIfStatement() {
        final String stringToTokenize = "agar bhai (a == 5) { bol bhai \"a is 5\"; }";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getIfStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.IF_STATEMENT, astNode.getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getTest().getType());
        assertEquals("==", astNode.getTest().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getTest().getLeft().getType());
        assertEquals(5, astNode.getTest().getRight().getNumberValue());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getConsequent().getType());
    }

    @Test
    void testGetStatement_singleIfElseStatement() {
        final String stringToTokenize = "agar bhai (a < 5) \n" +
                "{ bol bhai \"a is less than 5\"; }\n" +
                "warna bhai \n" +
                "{ bol bhai \"a is greater than or equal to 5\"; }";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getIfStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.IF_STATEMENT, astNode.getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getTest().getType());
        assertEquals("<", astNode.getTest().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getTest().getLeft().getType());
        assertEquals(5, astNode.getTest().getRight().getNumberValue());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getConsequent().getType());
        assertEquals(1, astNode.getAlternates().size());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getAlternates().get(0).getType());
    }

    @Test
    void testGetStatement_singleIfElseIfElseStatement() {
        final String stringToTokenize = "agar bhai (a < 5) \n" +
                "{ bol bhai \"a is less than 5\"; }\n" +
                "nahi to bhai (a == 5) \n" +
                "{ bol bhai \"a is 5\"; }\n" +
                "warna bhai \n" +
                "{ bol bhai \"a is greater than 5\"; }";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getIfStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.IF_STATEMENT, astNode.getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getTest().getType());
        assertEquals("<", astNode.getTest().getOperator());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, astNode.getTest().getLeft().getType());
        assertEquals(5, astNode.getTest().getRight().getNumberValue());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getConsequent().getType());
        assertEquals(2, astNode.getAlternates().size());
        assertEquals(NodeType.IF_STATEMENT, astNode.getAlternates().get(0).getType());
        assertEquals(NodeType.BINARY_EXPRESSION, astNode.getAlternates().get(0).getTest().getType());
        assertEquals(NodeType.BLOCK_STATEMENT, astNode.getAlternates().get(1).getType());
    }
}
