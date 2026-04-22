package com.boardgamelang;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.typechecker.TypeChecker;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = args.length > 0 ? args[0] : "examples/bad.bgl";
        CharStream input = CharStreams.fromFileName(filePath);
        BoardGameLangLexer lexer = new BoardGameLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BoardGameLangParser parser = new BoardGameLangParser(tokens);

        BoardGameLangParser.ProgramContext parseTree = parser.program();
        ProgramNode ast = (ProgramNode) new AstBuilder().visit(parseTree);

        List<String> errors = new TypeChecker().check(ast);

        if (errors.isEmpty()) {
            System.out.println("Type check passed.");
        } else {
            errors.forEach(e -> System.out.println("Error: " + e));
        }
    }
}
