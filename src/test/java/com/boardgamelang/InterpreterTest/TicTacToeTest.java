package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.typechecker.TypeChecker;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {
    @Test
    void testTicTacToeXWin(){
        String input =
                "board(3,3); "
                + "player X has 5 piece XP;"
                + "player O has 4 piece OP;"
                + "gamerules position piece {!occupied(position)};"

                + "win when positions {"
                // horizontal 3-in-a-row
                // x x p
                + "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, left, 2))"
                + "or "
                // x p x
                + "piece(position) == piece(offset (position, right, 1)) and piece(position) == piece(offset (position, right, 2))"
                + "or "
                // p x x
                + "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, right, 1))"
                + "or "
                // vertical 3-in-a-row
                // x
                // x
                // p
                + "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, up, 2))"
                + "or "
                // x
                // p
                // x
                + "piece(position) == piece(offset (position, down, 1)) and piece(position) == piece(offset (position, down, 2))"
                + "or "
                // p
                // x
                // x
                + "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, down, 1))"
                + "or "
                // diagonal ↖ 3-in-a-row
                // x x x
                // x x x
                // x x p
                + "piece(position) == piece(offset (offset (position, up, 1), left, 1)) and piece(position) == piece(offset (offset (position, up, 2), left, 2))"
                + "or "
                // x x x
                // x p x
                // x x x
                + "piece(position) == piece(offset (offset (position, down, 1), right, 1)) and piece(position) == piece(offset (offset (position, down, 2), right, 2))"
                + "or "
                // p x x
                // x x x
                // x x x
                + "piece(position) == piece(offset (offset (position, up, 1), left, 1)) and piece(position) == piece(offset (offset (position, down, 1), right, 1))"
                + "or "
                // diagonal ↗ 3-in-a-row
                // x x x
                // x x x
                // p x x
                + "piece(position) == piece(offset (offset (position, up, 1), right, 1)) and piece(position) == piece(offset (offset (position, up, 2), right, 2))"
                + "or "
                // x x x
                // x p x
                // x x x
                + "piece(position) == piece(offset (offset (position, down, 1), left, 1)) and piece(position) == piece(offset (offset (position, down, 2), left, 2))"
                + "or "
                // x x p
                // x x x
                // x x x
                + "piece(position) == piece(offset (offset (position, up, 1), right, 1)) and piece(position) == piece(offset (offset (position, down, 1), left, 1))"
                + "}; "

                + "draw when global{count(XP) == 5 and count(OP) == 4};"

                + "place piece XP at (1,1);"
                + "assert XMove1 (occupied((1,1)));"

                + "place piece OP at (2,3);"
                + "assert OMove1 (occupied((2,3)));"

                + "place piece XP at (3,1);"
                + "assert XMove2 (occupied((3,1)));"

                + "place piece OP at (2,1);"
                + "assert OMove2 (occupied((2,1)));"

                + "place piece XP at (2,2);"
                + "assert XMove3 (occupied((2,2)));"

                + "place piece OP at (1,3);"
                + "assert OMove3 (occupied((1,3)));"

                + "place piece XP at (3,3);"
                + "assert XMove4 (occupied((3,3)));"

                + "assert countPiecesPlayed (count(XP) == 4 and count(OP) == 3);"
                ;

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertFalse(state.t.containsValue(false), "at least one assertion evaluated to false");

        String[] expected = {
                "XMove1", "OMove1", "XMove2", "OMove2",
                "XMove3", "OMove3", "XMove4", "countPiecesPlayed"
        };
        for (String name : expected) {
            assertTrue(state.t.containsKey(name), "expected assertion missing: " + name);
        }

        assertEquals("X", state.sigma.get("win"));

    }

    @Test
    void testTicTacToeDraw(){
        String input =
                        "board(3,3); "
                        + "player X has 5 piece XP;"
                        + "player O has 4 piece OP;"
                        + "gamerules position piece {!occupied(position)};"

                        + "win when positions {"
                        // horizontal 3-in-a-row
                        // x x p
                        + "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, left, 2))"
                        + "or "
                        // x p x
                        + "piece(position) == piece(offset (position, right, 1)) and piece(position) == piece(offset (position, right, 2))"
                        + "or "
                        // p x x
                        + "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, right, 1))"
                        + "or "
                        // vertical 3-in-a-row
                        // x
                        // x
                        // p
                        + "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, up, 2))"
                        + "or "
                        // x
                        // p
                        // x
                        + "piece(position) == piece(offset (position, down, 1)) and piece(position) == piece(offset (position, down, 2))"
                        + "or "
                        // p
                        // x
                        // x
                        + "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, down, 1))"
                        + "or "
                        // diagonal ↖ 3-in-a-row
                        // x x x
                        // x x x
                        // x x p
                        + "piece(position) == piece(offset (offset (position, up, 1), left, 1)) and piece(position) == piece(offset (offset (position, up, 2), left, 2))"
                        + "or "
                        // x x x
                        // x p x
                        // x x x
                        + "piece(position) == piece(offset (offset (position, down, 1), right, 1)) and piece(position) == piece(offset (offset (position, down, 2), right, 2))"
                        + "or "
                        // p x x
                        // x x x
                        // x x x
                        + "piece(position) == piece(offset (offset (position, up, 1), left, 1)) and piece(position) == piece(offset (offset (position, down, 1), right, 1))"
                        + "or "
                        // diagonal ↗ 3-in-a-row
                        // x x x
                        // x x x
                        // p x x
                        + "piece(position) == piece(offset (offset (position, up, 1), right, 1)) and piece(position) == piece(offset (offset (position, up, 2), right, 2))"
                        + "or "
                        // x x x
                        // x p x
                        // x x x
                        + "piece(position) == piece(offset (offset (position, down, 1), left, 1)) and piece(position) == piece(offset (offset (position, down, 2), left, 2))"
                        + "or "
                        // x x p
                        // x x x
                        // x x x
                        + "piece(position) == piece(offset (offset (position, up, 1), right, 1)) and piece(position) == piece(offset (offset (position, down, 1), left, 1))"
                        + "}; "

                        + "draw when global{count(XP) == 5 and count(OP) == 4};"

                        + "place piece XP at (1,1);"
                        + "assert XMove1 (occupied((1,1)));"

                        + "place piece OP at (2,2);"
                        + "assert OMove1 (occupied((2,2)));"

                        + "place piece XP at (2,1);"
                        + "assert XMove2 (occupied((2,1)));"

                        + "place piece OP at (3,1);"
                        + "assert OMove2 (occupied((3,1)));"

                        + "place piece XP at (1,3);"
                        + "assert XMove3 (occupied((1,3)));"

                        + "place piece OP at (1,2);"
                        + "assert OMove3 (occupied((1,2)));"

                        + "place piece XP at (3,2);"
                        + "assert XMove4 (occupied((3,2)));"

                        + "place piece OP at (2,3);"
                        + "assert OMove4 (occupied((2,3)));"

                        + "place piece XP at (3,3);"
                        + "assert XMove5 (occupied((3,3)));"

                        + "assert countPiecesPlayed (count(XP) == 5 and count(OP) == 4);"
                ;

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertFalse(state.t.containsValue(false), "at least one assertion evaluated to false");

        String[] expected = {
                "XMove1", "OMove1", "XMove2", "OMove2",
                "XMove3", "OMove3", "XMove4", "OMove4",
                "XMove5", "countPiecesPlayed"
        };
        for (String name : expected) {
            assertTrue(state.t.containsKey(name), "expected assertion missing: " + name);
        }

        assertEquals(true, state.sigma.get("draw"));

    }

}
