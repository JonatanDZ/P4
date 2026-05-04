package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterpreterPlayerHasPieceTest {

    @Test
    void playerRedHasTwoKnights() {
        String input = "board(3,3); player red has 2 piece knight;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);
        // Look in the state of ownedPiece, first the key red, and then count how many "knights" that exist in the state
        Set<State.OwnedPiece> redPieces = state.o.get("red");
        long knightCount = redPieces.stream()
                .filter(p -> p.piece().equals("knight"))
                .count();
        System.out.println(state.o);
        assertEquals(2, knightCount);
    }

    @Test
    void playerYellowHasFourQueens() {
        String input = "board(3,3); player red has 2 piece knight; player yellow has 4 piece queen;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);
        // Look in the state of ownedPiece, first the key yellow, and then count how many "queens" that exist in the state
        Set<State.OwnedPiece> yellowPieces = state.o.get("yellow");
        long queenCount = yellowPieces.stream()
                .filter(p -> p.piece().equals("queen"))
                .count();
        assertEquals(4, queenCount);
    }

    @Test
    void noOverrideOfPieces() {
        String input = "board(3,3); player red has 2 piece knight; player red has 4 piece queen;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);
        // Look in the state of ownedPiece, first the key red, and then count how many "knights" and "queens" that exist in the state
        Set<State.OwnedPiece> redPieces = state.o.get("red");
        long knightCount = redPieces.stream()
                .filter(p -> p.piece().equals("knight"))
                .count();
        long queenCount = redPieces.stream()
                .filter(p -> p.piece().equals("queen"))
                .count();
        assertEquals(2, knightCount);
        assertEquals(4, queenCount);
    }

    @Test
    void appendingPiecesToPlayer() {
        String input = "board(3,3); player red has 2 piece knight; player red has 4 piece knight;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);
        // Look in the state of ownedPiece, first the key red, and then count how many "knights" that exist in the state
        Set<State.OwnedPiece> redPieces = state.o.get("red");
        long knightCount = redPieces.stream()
                .filter(p -> p.piece().equals("knight"))
                .count();

        assertEquals(6, knightCount);
    }

    @Test
    void twoPlayerKeysInSet() {
        String input = "board(3,3); player red has 2 piece knight; player yellow has 4 piece queen;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);
        // Assert that there exists two player keys in the state o
        assertEquals(2, state.o.size());
    }

    @Test
    void twoPlayerCannotHaveSamePiece() {
        String input = "board(3,3); player red has 2 piece knight; player yellow has 4 piece knight;";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        // Assert that a runtimeerror is thrown
        assertThrows(RuntimeException.class, () -> interpreter.run(program));
    }


}
