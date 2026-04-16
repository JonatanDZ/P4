// Generated from com/boardgamelang/BoardGameLang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class BoardGameLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLACE=1, BOARD=2, PIECE=3, AT=4, SEMI=5, LPAR=6, RPAR=7, COMMA=8, OCCUPIED=9, 
		ASSERT=10, PLAYER=11, HAS=12, NUM=13, IDENT=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PLACE", "BOARD", "PIECE", "AT", "SEMI", "LPAR", "RPAR", "COMMA", "OCCUPIED", 
			"ASSERT", "PLAYER", "HAS", "NUM", "IDENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'place'", "'board'", "'piece'", "'at'", "';'", "'('", "')'", "','", 
			"'occupied'", "'assert'", "'player'", "'has'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PLACE", "BOARD", "PIECE", "AT", "SEMI", "LPAR", "RPAR", "COMMA", 
			"OCCUPIED", "ASSERT", "PLAYER", "HAS", "NUM", "IDENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public BoardGameLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BoardGameLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000fj\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0004\fY\b\f\u000b\f\f\fZ\u0001\r\u0001\r\u0005\r_\b\r\n\r\f"+
		"\rb\t\r\u0001\u000e\u0004\u000ee\b\u000e\u000b\u000e\f\u000ef\u0001\u000e"+
		"\u0001\u000e\u0000\u0000\u000f\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u0001\u0000\u0004\u0001\u0000"+
		"09\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000\t\n\r\r  l\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003%\u0001\u0000\u0000"+
		"\u0000\u0005+\u0001\u0000\u0000\u0000\u00071\u0001\u0000\u0000\u0000\t"+
		"4\u0001\u0000\u0000\u0000\u000b6\u0001\u0000\u0000\u0000\r8\u0001\u0000"+
		"\u0000\u0000\u000f:\u0001\u0000\u0000\u0000\u0011<\u0001\u0000\u0000\u0000"+
		"\u0013E\u0001\u0000\u0000\u0000\u0015L\u0001\u0000\u0000\u0000\u0017S"+
		"\u0001\u0000\u0000\u0000\u0019X\u0001\u0000\u0000\u0000\u001b\\\u0001"+
		"\u0000\u0000\u0000\u001dd\u0001\u0000\u0000\u0000\u001f \u0005p\u0000"+
		"\u0000 !\u0005l\u0000\u0000!\"\u0005a\u0000\u0000\"#\u0005c\u0000\u0000"+
		"#$\u0005e\u0000\u0000$\u0002\u0001\u0000\u0000\u0000%&\u0005b\u0000\u0000"+
		"&\'\u0005o\u0000\u0000\'(\u0005a\u0000\u0000()\u0005r\u0000\u0000)*\u0005"+
		"d\u0000\u0000*\u0004\u0001\u0000\u0000\u0000+,\u0005p\u0000\u0000,-\u0005"+
		"i\u0000\u0000-.\u0005e\u0000\u0000./\u0005c\u0000\u0000/0\u0005e\u0000"+
		"\u00000\u0006\u0001\u0000\u0000\u000012\u0005a\u0000\u000023\u0005t\u0000"+
		"\u00003\b\u0001\u0000\u0000\u000045\u0005;\u0000\u00005\n\u0001\u0000"+
		"\u0000\u000067\u0005(\u0000\u00007\f\u0001\u0000\u0000\u000089\u0005)"+
		"\u0000\u00009\u000e\u0001\u0000\u0000\u0000:;\u0005,\u0000\u0000;\u0010"+
		"\u0001\u0000\u0000\u0000<=\u0005o\u0000\u0000=>\u0005c\u0000\u0000>?\u0005"+
		"c\u0000\u0000?@\u0005u\u0000\u0000@A\u0005p\u0000\u0000AB\u0005i\u0000"+
		"\u0000BC\u0005e\u0000\u0000CD\u0005d\u0000\u0000D\u0012\u0001\u0000\u0000"+
		"\u0000EF\u0005a\u0000\u0000FG\u0005s\u0000\u0000GH\u0005s\u0000\u0000"+
		"HI\u0005e\u0000\u0000IJ\u0005r\u0000\u0000JK\u0005t\u0000\u0000K\u0014"+
		"\u0001\u0000\u0000\u0000LM\u0005p\u0000\u0000MN\u0005l\u0000\u0000NO\u0005"+
		"a\u0000\u0000OP\u0005y\u0000\u0000PQ\u0005e\u0000\u0000QR\u0005r\u0000"+
		"\u0000R\u0016\u0001\u0000\u0000\u0000ST\u0005h\u0000\u0000TU\u0005a\u0000"+
		"\u0000UV\u0005s\u0000\u0000V\u0018\u0001\u0000\u0000\u0000WY\u0007\u0000"+
		"\u0000\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000ZX\u0001"+
		"\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\u001a\u0001\u0000\u0000"+
		"\u0000\\`\u0007\u0001\u0000\u0000]_\u0007\u0002\u0000\u0000^]\u0001\u0000"+
		"\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000a\u001c\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000"+
		"\u0000ce\u0007\u0003\u0000\u0000dc\u0001\u0000\u0000\u0000ef\u0001\u0000"+
		"\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hi\u0006\u000e\u0000\u0000i\u001e\u0001\u0000\u0000"+
		"\u0000\u0004\u0000Z`f\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}