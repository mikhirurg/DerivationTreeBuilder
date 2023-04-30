package derivation.rules.whilelang;

import derivation.rules.Derivable;
import parser.syntax.whilelang.statements.WhileIf;
import parser.syntax.whilelang.statements.WhileStatement;
import states.State;
import states.WhileState;

import java.util.ArrayList;

public class WhileIfFFRule extends WhileIfRule implements DerivationRule {
    public WhileIfFFRule(WhileIf ifStatement, WhileState state) {
        super(ifStatement, state);
    }

    @Override
    public State getState() {
        return state;
    }
}
