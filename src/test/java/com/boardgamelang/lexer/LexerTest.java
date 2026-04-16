package com.boardgamelang.lexer;

import com.boardgamelang.BoardGameLangLexer;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    private List<Token> tokenize(String input) {
        CharStream charStream = CharStreams.fromString(input);
        BoardGameLangLexer lexer = new BoardGameLangLexer(charStream);
        return (List<Token>) lexer.getAllTokens();
    }

    @Test
    void tokenizeBoardDeclaration() {
        List<Token> tokens = tokenize("board(3,3);");

        assertEquals(BoardGameLangLexer.BOARD, tokens.get(0).getType());
        assertEquals(BoardGameLangLexer.LPAR, tokens.get(1).getType());
        assertEquals(BoardGameLangLexer.NUM, tokens.get(2).getType());
        assertEquals("3", tokens.get(2).getText());
        assertEquals(BoardGameLangLexer.COMMA, tokens.get(3).getType());
        assertEquals(BoardGameLangLexer.NUM, tokens.get(4).getType());
        assertEquals(BoardGameLangLexer.RPAR, tokens.get(5).getType());
        assertEquals(BoardGameLangLexer.SEMI, tokens.get(6).getType());
    }

    @Test
    void tokenizeKeywords() {
        List<Token> tokens = tokenize("place piece board at assert occupied player has");

        assertEquals(BoardGameLangLexer.PLACE, tokens.get(0).getType());
        assertEquals(BoardGameLangLexer.PIECE, tokens.get(1).getType());
        assertEquals(BoardGameLangLexer.BOARD, tokens.get(2).getType());
        assertEquals(BoardGameLangLexer.AT, tokens.get(3).getType());
        assertEquals(BoardGameLangLexer.ASSERT, tokens.get(4).getType());
        assertEquals(BoardGameLangLexer.OCCUPIED, tokens.get(5).getType());
        assertEquals(BoardGameLangLexer.PLAYER, tokens.get(6).getType());
        assertEquals(BoardGameLangLexer.HAS, tokens.get(7).getType());
    }

    @Test
    void tokenizeIdentifiers() {
        List<Token> tokens = tokenize("myVar x testName123");

        assertEquals(BoardGameLangLexer.IDENT, tokens.get(0).getType());
        assertEquals("myVar", tokens.get(0).getText());
        assertEquals(BoardGameLangLexer.IDENT, tokens.get(1).getType());
        assertEquals(BoardGameLangLexer.IDENT, tokens.get(2).getType());
    }

    @Test
    void whitespaceIsSkipped() {
        List<Token> tokens = tokenize("board  (  3  ,  3  )  ;");
        // Should produce the same tokens as "board(3,3);" -- no WS tokens
        assertEquals(7, tokens.size());
    }
}
