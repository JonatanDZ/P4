package com.boardgamelang.AstTest;

import com.boardgamelang.BoardGameLangLexer;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ParseTreeHelper {
    public static BoardGameLangParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        BoardGameLangLexer lexer = new BoardGameLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new BoardGameLangParser(tokens);
    }
}
