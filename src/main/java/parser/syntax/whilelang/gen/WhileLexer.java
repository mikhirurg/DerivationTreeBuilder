package parser.syntax.whilelang.gen;// Generated from C:/Users/mikha/IdeaProjects/DerivationTreeBuilder/src/main/antlr4\While.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class WhileLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		INT=18, NOT=19, AND=20, VAR=21, WS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"INT", "NOT", "AND", "VAR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':='", "'skip'", "';'", "'if'", "'then'", "'else'", "'while'", 
			"'do'", "'('", "')'", "'+'", "'-'", "'*'", "'true'", "'false'", "'='", 
			"'<='", null, "'not'", "'and'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "INT", "NOT", "AND", "VAR", "WS"
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


	public WhileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "While.g4"; }

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
		"\u0004\u0000\u0016\u0080\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0004\u0011i\b\u0011\u000b\u0011"+
		"\f\u0011j\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0004\u0014v\b\u0014"+
		"\u000b\u0014\f\u0014w\u0001\u0015\u0004\u0015{\b\u0015\u000b\u0015\f\u0015"+
		"|\u0001\u0015\u0001\u0015\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016\u0001\u0000\u0003\u0001\u000009\u0002"+
		"\u0000AZaz\u0002\u0000\t\n  \u0082\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0001"+
		"-\u0001\u0000\u0000\u0000\u00030\u0001\u0000\u0000\u0000\u00055\u0001"+
		"\u0000\u0000\u0000\u00077\u0001\u0000\u0000\u0000\t:\u0001\u0000\u0000"+
		"\u0000\u000b?\u0001\u0000\u0000\u0000\rD\u0001\u0000\u0000\u0000\u000f"+
		"J\u0001\u0000\u0000\u0000\u0011M\u0001\u0000\u0000\u0000\u0013O\u0001"+
		"\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017S\u0001\u0000\u0000"+
		"\u0000\u0019U\u0001\u0000\u0000\u0000\u001bW\u0001\u0000\u0000\u0000\u001d"+
		"\\\u0001\u0000\u0000\u0000\u001fb\u0001\u0000\u0000\u0000!d\u0001\u0000"+
		"\u0000\u0000#h\u0001\u0000\u0000\u0000%l\u0001\u0000\u0000\u0000\'p\u0001"+
		"\u0000\u0000\u0000)u\u0001\u0000\u0000\u0000+z\u0001\u0000\u0000\u0000"+
		"-.\u0005:\u0000\u0000./\u0005=\u0000\u0000/\u0002\u0001\u0000\u0000\u0000"+
		"01\u0005s\u0000\u000012\u0005k\u0000\u000023\u0005i\u0000\u000034\u0005"+
		"p\u0000\u00004\u0004\u0001\u0000\u0000\u000056\u0005;\u0000\u00006\u0006"+
		"\u0001\u0000\u0000\u000078\u0005i\u0000\u000089\u0005f\u0000\u00009\b"+
		"\u0001\u0000\u0000\u0000:;\u0005t\u0000\u0000;<\u0005h\u0000\u0000<=\u0005"+
		"e\u0000\u0000=>\u0005n\u0000\u0000>\n\u0001\u0000\u0000\u0000?@\u0005"+
		"e\u0000\u0000@A\u0005l\u0000\u0000AB\u0005s\u0000\u0000BC\u0005e\u0000"+
		"\u0000C\f\u0001\u0000\u0000\u0000DE\u0005w\u0000\u0000EF\u0005h\u0000"+
		"\u0000FG\u0005i\u0000\u0000GH\u0005l\u0000\u0000HI\u0005e\u0000\u0000"+
		"I\u000e\u0001\u0000\u0000\u0000JK\u0005d\u0000\u0000KL\u0005o\u0000\u0000"+
		"L\u0010\u0001\u0000\u0000\u0000MN\u0005(\u0000\u0000N\u0012\u0001\u0000"+
		"\u0000\u0000OP\u0005)\u0000\u0000P\u0014\u0001\u0000\u0000\u0000QR\u0005"+
		"+\u0000\u0000R\u0016\u0001\u0000\u0000\u0000ST\u0005-\u0000\u0000T\u0018"+
		"\u0001\u0000\u0000\u0000UV\u0005*\u0000\u0000V\u001a\u0001\u0000\u0000"+
		"\u0000WX\u0005t\u0000\u0000XY\u0005r\u0000\u0000YZ\u0005u\u0000\u0000"+
		"Z[\u0005e\u0000\u0000[\u001c\u0001\u0000\u0000\u0000\\]\u0005f\u0000\u0000"+
		"]^\u0005a\u0000\u0000^_\u0005l\u0000\u0000_`\u0005s\u0000\u0000`a\u0005"+
		"e\u0000\u0000a\u001e\u0001\u0000\u0000\u0000bc\u0005=\u0000\u0000c \u0001"+
		"\u0000\u0000\u0000de\u0005<\u0000\u0000ef\u0005=\u0000\u0000f\"\u0001"+
		"\u0000\u0000\u0000gi\u0007\u0000\u0000\u0000hg\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000k$\u0001\u0000\u0000\u0000lm\u0005n\u0000\u0000mn\u0005o\u0000\u0000"+
		"no\u0005t\u0000\u0000o&\u0001\u0000\u0000\u0000pq\u0005a\u0000\u0000q"+
		"r\u0005n\u0000\u0000rs\u0005d\u0000\u0000s(\u0001\u0000\u0000\u0000tv"+
		"\u0007\u0001\u0000\u0000ut\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000x*\u0001\u0000"+
		"\u0000\u0000y{\u0007\u0002\u0000\u0000zy\u0001\u0000\u0000\u0000{|\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u007f\u0006\u0015\u0000\u0000\u007f,\u0001"+
		"\u0000\u0000\u0000\u0004\u0000jw|\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}