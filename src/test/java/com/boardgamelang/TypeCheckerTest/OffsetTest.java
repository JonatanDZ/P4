package com.boardgamelang.TypeCheckerTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.typechecker.TypeChecker;
import com.boardgamelang.typechecker.TypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OffsetTest {
    @Test
    void offsetWithLiteralPositionInAssertPasses() {
        String input = "board(3,3); assert test (occupied(offset (1,1) right 2));";
        // literal pos in offset — no position ref, always valid
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
    }

    @Test
    void offsetWithPositionRefInGamerulePasses() {
        String input = "board(3,3); player x has 1 piece knight; "
                        + "gamerules position piece {occupied(offset position right 1)};";
        // position ref inside gamerule — allowed
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
    }

    @Test
    void offsetWithPositionRefInAssertFails() {
        String input = "board(3,3); assert test (occupied(offset position right 1));";
        // position ref outside gamerule/win/draw — not allowed
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertThrows(TypeException.class, () -> typeChecker.check(program));
    }
}
