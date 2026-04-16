package com.boardgamelang;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: P4Lang <source-file>");
            System.exit(1);
        }

        String source = Files.readString(Path.of(args[0]));
        CharStream input = CharStreams.fromString(source);

        P4LangLexer lexer = new P4LangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        P4LangParser parser = new P4LangParser(tokens);

        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
    }
}
