package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class InterpretPlacePieceAtTest {

    @Test
    void placePieceWhenValid(){
        String input = "board(8,8); player x has 1 piece knight; place piece knight at (4,8);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertEquals("knight", state.beta.get(new Position(4,8)));
    }

    @Test
    void failsWhenPositionIsOutOfBounds(){
        String input = "board(8,8); player x has 1 piece knight; place piece knight at (9,8);";

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
    void failsWhenPieceNotOwned(){
        String input = "board(8,8); player x has 1 piece knight; place piece pawn at (5,8);";

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
    void failsWhenGameruleFalse(){
        String input = "board(8,8); player x has 1 piece knight; place piece knight at (9,8);";

    }






}
