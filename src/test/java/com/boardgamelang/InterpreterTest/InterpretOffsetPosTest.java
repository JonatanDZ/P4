package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.direction.DirNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpretOffsetPosTest {
    // test the result of a single offset
    @Test
    void singleOffsetReturnsCorrectResult() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(2,2);
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3,3));

        Position result = interp.execPos(new OffsetNode(pos, dir, 1));
        assertDoesNotThrow(() -> result);
        assertEquals(new Position(2,1), result);
    }

    // test that base is out of bounds and throws
    @Test
    void offsetBaseOutOfBoundsAndThrows() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1, 4);   // column 4 is off a 3x3 board
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3, 3));

        assertThrows(RuntimeException.class,
                () -> interp.execPos(new OffsetNode(pos, dir, 1)));
    }

    // test that result is out of bounds and throws
    @Test
    void offsetResultOutOfBoundsAndThrows() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1, 1);   // column 4 is off a 3x3 board
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3, 3));

        assertThrows(RuntimeException.class,
                () -> interp.execPos(new OffsetNode(pos, dir, 5)));
    }
    // test the result of a nested offset
    @Test
    void nestedOffsetReturnsCorrectResult() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1,3);
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3,3));

        Position result = interp.execPos(new OffsetNode(new OffsetNode(pos, dir, 1), dir, 1));
        assertDoesNotThrow(() -> result);
        assertEquals(new Position(1,1), result);
    }
}
