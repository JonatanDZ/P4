package com.boardgamelang;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: boardgamelang <source-file>");
            System.exit(1);
        }

        String source = Files.readString(Path.of(args[0]));
        CharStream input = CharStreams.fromString(source);

        BoardGameLangLexer lexer = new BoardGameLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BoardGameLangParser parser = new BoardGameLangParser(tokens);

        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
    }
}
