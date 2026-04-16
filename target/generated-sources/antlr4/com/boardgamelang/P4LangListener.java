// Generated from com/boardgamelang/P4Lang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link P4LangParser}.
 */
public interface P4LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link P4LangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(P4LangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link P4LangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(P4LangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link P4LangParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(P4LangParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link P4LangParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(P4LangParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link P4LangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(P4LangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link P4LangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(P4LangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link P4LangParser#bexp}.
	 * @param ctx the parse tree
	 */
	void enterBexp(P4LangParser.BexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link P4LangParser#bexp}.
	 * @param ctx the parse tree
	 */
	void exitBexp(P4LangParser.BexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link P4LangParser#pos}.
	 * @param ctx the parse tree
	 */
	void enterPos(P4LangParser.PosContext ctx);
	/**
	 * Exit a parse tree produced by {@link P4LangParser#pos}.
	 * @param ctx the parse tree
	 */
	void exitPos(P4LangParser.PosContext ctx);
}