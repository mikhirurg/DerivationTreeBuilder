import derivation.rules.DerivationTreeBuilder;
import derivation.rules.whilelang.DerivationTreeNode;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.syntax.whilelang.WhileListener;
import parser.syntax.whilelang.gen.WhileLexer;
import parser.syntax.whilelang.gen.WhileParser;
import parser.syntax.whilelang.statements.WhileStatement;
import states.WhileState;

import java.util.HashMap;

public class AntlrDemo {
    public static void main(String[] args) {
        //String program = "x:=5; y:=1; i:=1; while i <= x do (y := y * x; x := x - 1)";

        String program = "x:=27; y:=8; z:=0; while y <= x do (z := z + 1; x := x - y)";

        WhileLexer whileLexer = new WhileLexer(CharStreams.fromString(program));

        CommonTokenStream tokens = new CommonTokenStream(whileLexer);
        WhileParser parser = new WhileParser(tokens);
        ParseTree tree = parser.prog();

        WhileListener listener = new WhileListener();

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(listener, tree);

        WhileStatement p = listener.getProgram();

        DerivationTreeBuilder builder = new WhileDerivationTreeBuilder();
        DerivationTreeNode derivationTree = builder.buildDerivationTree(p, new WhileState(new HashMap<>()));

        System.out.println(builder.getState().getTextRepresentation());
    }
}
