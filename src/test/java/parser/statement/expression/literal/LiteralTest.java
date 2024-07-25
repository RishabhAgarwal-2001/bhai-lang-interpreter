package parser.statement.expression.literal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.bhailang.parser.statement.expression.literal.*;
import com.bhailang.tokenizer.TokenType;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LiteralTest {

    @ParameterizedTest
    @MethodSource("provideLiteralImplementationAndTokenType")
    void testGetLiteralImpl(final Class<? extends Literal> literalClass, final TokenType literalTokenType) {
        assertInstanceOf(literalClass, Literal.getLiteralImpl(literalTokenType));
    }

    private static Stream<Arguments> provideLiteralImplementationAndTokenType() {
        return Stream.of(
                Arguments.of(StringLiteral.class, TokenType.STRING_TYPE),
                Arguments.of(BooleanLiteral.class, TokenType.BOOLEAN_TYPE),
                Arguments.of(NullLiteral.class, TokenType.NALLA_TYPE),
                Arguments.of(NumericLiteral.class, TokenType.NUMBER_TYPE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNonLiteralTokenType")
    void testGetLiteralImpl_NonLiteralTokenType(final TokenType tokenType) {
        try {
            Literal.getLiteralImpl(tokenType);
            assert false;
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Unidentified token type " + tokenType);
        }
    }

    private static Stream<Arguments> provideNonLiteralTokenType() {
        return Arrays.stream(TokenType.values())
                .filter(tokenType -> tokenType != TokenType.STRING_TYPE
                        && tokenType != TokenType.BOOLEAN_TYPE
                        && tokenType != TokenType.NALLA_TYPE
                        && tokenType != TokenType.NUMBER_TYPE)
                .map(Arguments::of);
    }
}