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

public class AndTest {
    @Test
    public void andWithValidChildrenPasses() {
        String input = "board(3,3); assert test {occupied((1,1)) and occupied((1,1))};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
    }

    @Test
    public void andWithTypeMismatchInChildFails() {
        String input = "board(3,3); player red has 2 piece knight; assert test {occupied((1,1)) and count(knight) == piece((1,1))};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertThrows(TypeException.class, () -> typeChecker.check(program));
    }
}
