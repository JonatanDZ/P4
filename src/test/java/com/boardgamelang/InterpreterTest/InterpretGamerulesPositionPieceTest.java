package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class InterpretGamerulesPositionPieceTest {
    @Test
    void interpretGamerulesPositionPieceSetg() {
        String input = "board(3,3); gamerules position piece {occupied(3,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = new Interpreter().run(programNode);

        assertInstanceOf(BexpNode.class, state.g);
    }
}
