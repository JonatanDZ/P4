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
    public void TypePassesTypeCheck() {
        String input = "board(5,5); player y has 6 piece Goat; place piece Goat at (3,5); place piece Goat at (1,3); "
                + "assert test(!occupied((3,5)) and !occupied((3,5)));";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
    }

    @Test
    public void TypeDoesFailsTypeCheck() {
        String input = "board(5,5); "
                + "player y has 6 piece Goat; "
                + "place piece Goat at (3,5); "
                + "place piece Goat at (1,3); "
                + "assert test(!occupied((3,5)) and 8);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());

    }




}
