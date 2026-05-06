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
        assertEquals(new Position(2,1), result);
    }

    // a literal off-board base is a user error and still throws.
    @Test
    void offsetBaseOutOfBoundsAndThrows() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1, 4);   // column 4 is off a 3x3 board
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3, 3));

        assertThrows(RuntimeException.class,
                () -> interp.execPos(new OffsetNode(pos, dir, 1)));
    }

    // out-of-bounds result returns null instead of throwing. This lets win/gamerule
    // bexps ask "is there a 4-in-a-row through position?" near an edge without crashing —
    // null propagates through piece(...) and equality to a clean false.
    @Test
    void offsetResultOutOfBoundsReturnsNull() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1, 1);   // base is in bounds, result (1, -4) is off a 3x3 board
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3, 3));

        assertNull(interp.execPos(new OffsetNode(pos, dir, 5)));
    }

    // a nested offset whose inner step went off-board propagates null outward.
    @Test
    void nestedOffsetWithInnerOutOfBoundsReturnsNull() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1, 1);
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3, 3));

        // inner: offset (1,1) left 5 → null; outer: offset null left 1 → null
        assertNull(interp.execPos(new OffsetNode(new OffsetNode(pos, dir, 5), dir, 1)));
    }
    // test the result of a nested offset
    @Test
    void nestedOffsetReturnsCorrectResult() {
        Interpreter interp = new Interpreter();
        PositionNode pos = new PositionNode(1,3);
        DirNode dir = new LeftNode();
        interp.state.delta = interp.execPos(new PositionNode(3,3));

        Position result = interp.execPos(new OffsetNode(new OffsetNode(pos, dir, 1), dir, 1));
        assertEquals(new Position(1,1), result);
    }
}
