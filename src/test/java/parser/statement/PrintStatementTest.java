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

public class PrintStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_singlePrintStatement() {
        final String stringToToken = "bol bhai \"Hello World\";";
        tokenizer.initTokenizer(stringToToken);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getPrintStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.PRINT_STATEMENT, astNode.getType());
        assertEquals(1, astNode.getExpressions().size());
        assertEquals(NodeType.STRING_LITERAL, astNode.getExpressions().get(0).getType());
        assertEquals("Hello World", astNode.getExpressions().get(0).getStringValue());
    }
}
