package derivation.rules.whilelang;

import derivation.rules.Derivable;
import parser.syntax.whilelang.statements.WhileIf;
import parser.syntax.whilelang.statements.WhileStatement;
import states.State;
import states.WhileState;

import java.util.ArrayList;

public class WhileIfTTRule extends WhileIfRule implements DerivationRule {

    public WhileIfTTRule(WhileIf ifStatement, WhileState s) {
        super(ifStatement, s);
    }

    @Override
    public State getState() {
        return state;
    }
}
