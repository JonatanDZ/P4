package com.boardgamelang.parser;

import com.boardgamelang.BoardGameLangLexer;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ParserErrorTest {

    private BoardGameLangParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        BoardGameLangLexer lexer = new BoardGameLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new BoardGameLangParser(tokens);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "board 3,3;",            // missing parentheses
            "(3,3);",                // missing board keyword
            "place piece at (1,1);"  // missing piece name
    })
    void parseInvalidInputs(String input) {
        BoardGameLangParser parser = createParser(input);
        parser.program();
        assertTrue(parser.getNumberOfSyntaxErrors() > 0,
                "Expected syntax errors for input: " + input);
    }
}
