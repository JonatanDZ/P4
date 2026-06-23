package com.boardgamelang;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import com.boardgamelang.typechecker.TypeChecker;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("files/1_pipeline.bgl");
        //CharStream input = CharStreams.fromFileName("files/2_typeerror_malformed.bgl");
        //CharStream input = CharStreams.fromFileName("files/3_eq_typeerror.bgl");
        //CharStream input = CharStreams.fromFileName("files/4_tictactoe.bgl");


        System.out.println("=== SOURCE ===");
        System.out.println(input);

        BoardGameLangLexer lexer = new BoardGameLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        System.out.println("\n=== TOKENS ===");
        tokens.fill();
        for (Token t : tokens.getTokens()) {
            System.out.println(lexer.getVocabulary().getSymbolicName(t.getType()) + " '" + t.getText() + "'");
        }

        BoardGameLangParser parser = new BoardGameLangParser(tokens);
        BoardGameLangParser.ProgramContext parseTree = parser.program();

        System.out.println("\n=== PARSE TREE ===");
        System.out.println(parseTree.toStringTree(parser));

        ProgramNode ast = (ProgramNode) new AstBuilder().visit(parseTree);

        System.out.println("\n=== AST ===");
        System.out.println("ProgramNode: " + ast.gameRuleNodes.size() + " gamerules, " + ast.stmtNodes.size() + " statements");

        TypeChecker typeChecker = new TypeChecker();
        typeChecker.check(ast);

        State state = new Interpreter().run(ast);
        System.out.println("\n=== FINAL STATE ===");
        System.out.println("o = " + state.o);
        System.out.println("beta = " + state.beta);
        System.out.println("t    = " + state.t);
    }
}