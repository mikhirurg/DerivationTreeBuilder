package derivation.rules.whilelang;

import derivation.rules.Derivable;
import parser.syntax.whilelang.statements.WhileComp;
import states.State;
import states.WhileState;

import java.util.ArrayList;
import java.util.List;

public class WhileCompRule extends WhileDerivationRule implements DerivationRule {

    private final WhileComp comp;

    public WhileCompRule(WhileComp comp, WhileState state) {
        super(state);
        this.comp = comp;
    }

    @Override
    public State getState() {
        return state;
    }
}
