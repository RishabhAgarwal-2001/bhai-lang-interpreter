package parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContinueStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_validStatement() {
        final String stringToTokenize = "agla dekh bhai";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getContinueStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.CONTINUE_STATEMENT, astNode.getType());
    }

    @Test
    void testGetStatement_invalidStatement() {
        final String stringToTokenize = "123";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        try {
            DependencyModule.getContinueStatement().getStatement();
        } catch (Exception e) {
            assertEquals("kya kar rha hai tu??...Unexpected token, expected " + TokenType.AGLA_DEKH_BHAI + " but found " + TokenType.NUMBER_TYPE, e.getMessage());
        }
    }
}
