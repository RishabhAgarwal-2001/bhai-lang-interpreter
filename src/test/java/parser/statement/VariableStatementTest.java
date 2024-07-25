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

public class VariableStatementTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @Test
    void testGetStatement_singleVariableStatement() {
        final String stringToTokenize = "bhai ye hai a = 5;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getVariableStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.VARIABLE_STATEMENT, astNode.getType());
        assertEquals(1, astNode.getDeclarations().size());

        final ASTNode declaration = astNode.getDeclarations().getFirst();

        assertEquals(NodeType.VARIABLE_DECLARATION, declaration.getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, declaration.getId().getType());
        assertEquals("a", declaration.getId().getName());
        assertEquals(NodeType.NUMERIC_LITERAL, declaration.getInit().getType());
    }

    @Test
    void testGetStatement_singleVariableStatementWithComplexInitializer() {
        final String stringToTokenize = "bhai ye hai a = 5 + 3;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getVariableStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.VARIABLE_STATEMENT, astNode.getType());
        assertEquals(1, astNode.getDeclarations().size());

        final ASTNode declaration = astNode.getDeclarations().getFirst();

        assertEquals(NodeType.VARIABLE_DECLARATION, declaration.getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, declaration.getId().getType());
        assertEquals("a", declaration.getId().getName());
        assertEquals(NodeType.BINARY_EXPRESSION, declaration.getInit().getType());
        assertEquals(NodeType.NUMERIC_LITERAL, declaration.getInit().getLeft().getType());
        assertEquals(NodeType.NUMERIC_LITERAL, declaration.getInit().getRight().getType());
    }

    @Test
    void testGetStatement_multipleVariableStatement() {
        final String stringToTokenize = "bhai ye hai a = 5, b = 3;";
        tokenizer.initTokenizer(stringToTokenize);
        tokenExecutor.setLookahead(tokenizer.getNextToken());

        final ASTNode astNode = DependencyModule.getVariableStatement().getStatement();

        assertNotNull(astNode);
        assertEquals(NodeType.VARIABLE_STATEMENT, astNode.getType());
        assertEquals(2, astNode.getDeclarations().size());

        final ASTNode declaration1 = astNode.getDeclarations().getFirst();
        assertEquals(NodeType.VARIABLE_DECLARATION, declaration1.getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, declaration1.getId().getType());
        assertEquals("a", declaration1.getId().getName());
        assertEquals(NodeType.NUMERIC_LITERAL, declaration1.getInit().getType());

        final ASTNode declaration2 = astNode.getDeclarations().getLast();
        assertEquals(NodeType.VARIABLE_DECLARATION, declaration2.getType());
        assertEquals(NodeType.IDENTIFIER_EXPRESSION, declaration2.getId().getType());
        assertEquals("b", declaration2.getId().getName());
        assertEquals(NodeType.NUMERIC_LITERAL, declaration2.getInit().getType());
    }
}
