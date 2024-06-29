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

public class InitStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_emptyProgram() {
        final String stringToTokenize = "hi bhai;\n" +
                "bye bhai;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getInitStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.INIT_STATEMENT, astNode.getType());
        assertEquals(0, astNode.getBody().size());
    }

    @Test
    void testGetStatement_emptyProgramWithComments() {
        final String stringToTokenize = "hi bhai; // This is a comment\n" +
                "bye bhai;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getInitStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.INIT_STATEMENT, astNode.getType());
        assertEquals(0, astNode.getBody().size());
    }

    @Test
    void testGetStatement_codeOutsideStartAndStopTokensIgnored() {
        final String stringToTokenize = """
                bol bhai "Hello World Before";
                hi bhai;
                bol bhai "Hello World";
                bye bhai;
                bol bhai "Hello World After";
                """;
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getInitStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.INIT_STATEMENT, astNode.getType());
        assertEquals(1, astNode.getBody().size());
        assertEquals(NodeType.PRINT_STATEMENT, astNode.getBody().getFirst().getType());
        assertEquals(1, astNode.getBody().getFirst().getExpressions().size());
        assertEquals("Hello World", astNode.getBody().getFirst().getExpressions().getFirst().getStringValue());
    }
}
