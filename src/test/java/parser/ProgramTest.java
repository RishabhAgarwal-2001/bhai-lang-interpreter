package parser;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class ProgramTest {
    private Tokenizer tokenizer;
    private TokenExecutor tokenExecutor;

    private static final List<String> validPrograms = List.of(
            """
                hi bhai;
                    bol bhai "Hello World";
                bye bhai;
            """,
            """
                gibberish
                hi bhai;
                    bol bhai "Hello World";
                bye bhai;
                gibberish
            """,
            """
                hi bhai;
                    bhai ye hai a = 5;
                    bhai ye hai b = 10;
                    bol bhai a, b;
                bye bhai;
            """
    );

    @BeforeEach
    void setup() {
        tokenizer = DependencyModule.getTokenizer();
        tokenExecutor = DependencyModule.getTokenExecutor();
    }

    @ParameterizedTest
    @MethodSource("validPrograms")
    void testGetProgram(String program) {
        tokenizer.initTokenizer(program);
        final Token token = tokenizer.getNextToken();
        tokenExecutor.setLookahead(token);

        DependencyModule.getProgram().getProgram();
    }

    private static List<String> validPrograms() {
        return validPrograms;
    }
}
