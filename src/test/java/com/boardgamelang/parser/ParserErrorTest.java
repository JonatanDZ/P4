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

    // board(3,3) is inserted into every test to avoid def error, since def should be first in every program.
    @ParameterizedTest
    @ValueSource(strings = {
            "board 3,3;",                                          // missing parentheses
            "board(3,3); (3,3);",                                  // missing board keyword
            "board(3,3); place piece at (1,1);",                   // missing piece name
            "Board(3,3)",                                          // Capital b
            "board(3,3); player player has 5 piece red;",          // restricted keyword player
            "board(3,3); assert test {player x has 5 piece x};",   // Expecting bexp
            "board(3,3); board (3,3);",                            // Only one def
            "player x has 5 piece x; board (3,3);",                // Check that board def should be first
            "board(3,3) player x has 5 piece x;",                  // Missing Semi, after def
            "board(3,3); player assert has 5 piece x;"             // assert should be a restricted keyword
    })
    void parseInvalidInputs(String input) {
        BoardGameLangParser parser = createParser(input);
        parser.program();
        assertTrue(parser.getNumberOfSyntaxErrors() > 0,
                "Expected syntax errors for input: " + input);
    }
}
