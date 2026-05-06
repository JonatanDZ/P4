package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class InterpretPlacePieceAtTest {

    @Test
    void placePieceWhenValid(){
        String input = "board(8,8); gamerules position piece {!occupied(position)}; player x has 1 piece knight; place piece knight at (4,8);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertEquals("knight", state.beta.get(new Position(4,8)));
    }

    @Test
    void failsWhenPositionIsOutOfBounds(){
        String input = "board(8,8); gamerules position piece {!occupied(position)}; player x has 1 piece knight; place piece knight at (9,8);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> interpreter.run(program)
        );

        assertTrue(ex.getMessage().contains("Out of bounds"));
    }

    @Test
    void failsWhenPieceDoesNotExist(){
        String input = "board(8,8); gamerules position piece {!occupied(position)}; player x has 1 piece knight; place piece pawn at (5,8);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> interpreter.run(program)
        );

        assertTrue(ex.getMessage().contains("Piece not owned"));
    }

    @Test
    void gamerulesAndPlacePieceTestEndToEnd(){
        String input = "board(8,8); "
                + "player x has 1 piece pawn;"
                + "player x has 1 piece knight; "
                + "gamerules position piece {!occupied(position)}; "
                + "place piece knight at (4,8); "
                + "place piece pawn at (5,8);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertEquals("knight", state.beta.get(new Position(4,8)));
    }

    @Test
    void winAssociatesWithOwningPlayer() {
        String input = "board(3,3); "
                + "player alice has 1 piece knight; "
                + "gamerules position piece {!occupied(position)}; "
                + "win when positions {occupied((1,1)) and occupied((2,2))}; "
                + "place piece knight at (1,1); "
                + "place piece knight at (2,2);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertEquals("alice", state.sigma.get("win"));
    }

    @Test
    void winCannotBeOverwritten() {
        String input = "board(3,3); "
                + "player alice has 1 piece knight; "
                + "player pete has 1 piece pawn; "
                + "gamerules position piece {!occupied(position)}; "
                + "win when positions {occupied((1,1)) and occupied((2,2)) or occupied((3,3)) and occupied((2,3))}; "
                + "place piece knight at (1,1); "
                + "place piece pawn at (3,3);"
                + "place piece knight at (2,2);"
                + "place piece pawn at (2,3);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertEquals("alice", state.sigma.get("win"));
    }

    // place piece removes σ["position"] after successful return.
    @Test
    void placePieceUnbindsPositionAfterSuccess() {
        String input = "board(8,8); player x has 1 piece knight; gamerules position piece {!occupied(position)}; "
                + "place piece knight at (5,8);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());
        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertTrue(state.sigma.isEmpty());
    }
}
