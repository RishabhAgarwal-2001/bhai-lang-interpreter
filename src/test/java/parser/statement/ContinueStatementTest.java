package parser.statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;
import com.bhailang.tokenizer.Tokenizer;

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
