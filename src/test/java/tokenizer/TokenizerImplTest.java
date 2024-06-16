package tokenizer;

import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;
import com.schrodinger.tokenizer.TokenizerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TokenizerImplTest {

    private TokenizerImpl tokenizerImpl;

    @BeforeEach
    void setup() {
        tokenizerImpl = new TokenizerImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "hi bhai, HI_BHAI_TYPE",
            "bye bhai, BYE_BHAI_TYPE",
            "\"hello\", STRING_TYPE",
            " '' , NULL_TYPE",
    })
    void testGetNextToken(final String stringToTokenize, final TokenType tokenType) {
        tokenizerImpl.initTokenizer(stringToTokenize);
        final Token token = tokenizerImpl.getNextToken();
        if(tokenType == TokenType.NULL_TYPE) {
            assert(token == null);
            return;
        }
        assert(token.getTokenType() == tokenType);
        assert(token.getValue().equals(stringToTokenize));
    }

    @Test
    void uninitializedTokenizerTest() {
        try {
            tokenizerImpl.getNextToken();
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Tokenizer not initialized!"));
        }
    }

    @Test
    void syntaxErrorTest() {
        tokenizerImpl.initTokenizer("~~~");
        try {
            tokenizerImpl.getNextToken();
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Kya krr raha hai bhai tu?? Unexpected token: ~"));
        }
    }

    @Test
    void testIsEOF_withInitialization() {
        tokenizerImpl.initTokenizer("\"hello\"");
        assert (!tokenizerImpl.isEOF());
    }

    @Test
    void testIsEOF_withoutInitialization() {
        assert (tokenizerImpl.isEOF());
    }

    @Test
    void testHasMoreTokens_withInitialization() {
        tokenizerImpl.initTokenizer("\"hello\"");
        assert (tokenizerImpl.hasMoreTokens());
    }

    @Test
    void testHasMoreTokens_withoutInitialization() {
        assert(!tokenizerImpl.hasMoreTokens());
    }

}
