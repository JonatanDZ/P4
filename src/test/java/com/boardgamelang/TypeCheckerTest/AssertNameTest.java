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

public class AssertNameTest {
    @Test
    void noDuplicateAssertNamePassesTypeChecker() {
        String input = "board(3,3); player X has 2 piece XP; place piece XP at (1,1); " +
                       "assert OccupiedPosition {occupied((1,1))}; assert PieceCount {count(XP) == 2};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));

    }

    @Test
    public void duplicateAssertNameTypeCheckerThrowException() {
        String input = "board(3,3); player X has 2 piece XP; player O has 2 piece OP;" +
                       "assert PieceCount {count(XP) == 2}; assert PieceCount {count(OP) == 2};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());

    }
}

