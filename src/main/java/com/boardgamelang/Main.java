package com.boardgamelang;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.Node;
import org.antlr.v4.runtime.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

        if(args.length == 0) {
            System.out.println("No file specified");
            return;
        }

        System.out.println("Reading file: " + Paths.get(args[0]).toAbsolutePath());
        String source = Files.readString(Paths.get(args[0]));

        CharStream input = CharStreams.fromString(source);
        BoardGameLangLexer lexer = new BoardGameLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BoardGameLangParser parser = new BoardGameLangParser(tokens);

        BoardGameLangParser.ProgramContext parseTree = parser.program();
        Node ast = new AstBuilder().visit(parseTree);


        System.out.println(source);
        System.out.println("AST built: " + ast);

    }
}
