package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.PlacePieceXAtPNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



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

    }


}
