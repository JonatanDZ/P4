package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpretAndTest {
    @Test
    void andReturnsTrueWhenBothPositionsOccupied() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertTrue(result);
    }

    @Test
    void andReturnsFalseWhenLeftIsNotOccupied() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void andReturnsFalseWhenRightIsNotOccupied() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void andReturnsFalseWhenBothIsNotOccupied() {
        Interpreter interp = new Interpreter();

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }
}
