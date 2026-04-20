package com.boardgamelang.parser;

import com.boardgamelang.BoardGameLangLexer;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private BoardGameLangParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        BoardGameLangLexer lexer = new BoardGameLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new BoardGameLangParser(tokens);
    }

    @Test
    void parseBoardDeclaration() {
        BoardGameLangParser parser = createParser("board(3,3);");
        parser.program();
        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @Test
    void parseBoardWithPlayerAndStatements() {
        String input = """
                board(3,3);
                player red has 5 piece X;
                place piece X at (1,1);
                assert testbob (occupied(1,1));
                """;
        BoardGameLangParser parser = createParser(input);
        parser.program();
        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "board(1,1);",
            "board(10,10);",
            "board(3,3); place piece X at (1,1);",
            "board(3,3); player red has 5 piece X;",
            "board(3,3); assert test (occupied(1,1));",
            "board \n (3,3);",                              // Newline is skipped = passing
            "board(3,3); assert test (occupied(3,3));",
    })
    void parseValidInputs(String input) {
        BoardGameLangParser parser = createParser(input);
        parser.program();
        assertEquals(0, parser.getNumberOfSyntaxErrors(),
                "Expected no syntax errors for input: " + input);
    }
}
