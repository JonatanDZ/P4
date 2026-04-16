// Generated from com/boardgamelang/BoardGameLang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BoardGameLangParser}.
 */
public interface BoardGameLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BoardGameLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BoardGameLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoardGameLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BoardGameLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoardGameLangParser#def_}.
	 * @param ctx the parse tree
	 */
	void enterDef_(BoardGameLangParser.Def_Context ctx);
	/**
	 * Exit a parse tree produced by {@link BoardGameLangParser#def_}.
	 * @param ctx the parse tree
	 */
	void exitDef_(BoardGameLangParser.Def_Context ctx);
	/**
	 * Enter a parse tree produced by {@link BoardGameLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(BoardGameLangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoardGameLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(BoardGameLangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoardGameLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(BoardGameLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoardGameLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(BoardGameLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoardGameLangParser#pos}.
	 * @param ctx the parse tree
	 */
	void enterPos(BoardGameLangParser.PosContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoardGameLangParser#pos}.
	 * @param ctx the parse tree
	 */
	void exitPos(BoardGameLangParser.PosContext ctx);
}