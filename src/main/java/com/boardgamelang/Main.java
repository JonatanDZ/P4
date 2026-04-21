package com.boardgamelang;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.Node;
import org.antlr.v4.runtime.*;

public class Main {
    public static void main(String[] args) {
        String source = "board(3,3); place piece X at (1,1);";

        CharStream input = CharStreams.fromString(source);
        BoardGameLangLexer lexer = new BoardGameLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BoardGameLangParser parser = new BoardGameLangParser(tokens);

        BoardGameLangParser.ProgramContext parseTree = parser.program();
        Node ast = new AstBuilder().visit(parseTree);

        System.out.println("AST built: " + ast);
    }
}
