package derivation.rules.whilelang;

import derivation.rules.Derivable;
import parser.syntax.whilelang.statements.WhileStatement;
import parser.syntax.whilelang.statements.WhileWhile;
import states.State;
import states.WhileState;

import java.util.ArrayList;

public class WhileWhileTTRule extends WhileWhileRule implements DerivationRule {
    public WhileWhileTTRule(WhileWhile whileStatement, WhileState state) {
        super(whileStatement, state);
    }

    @Override
    public State getState() {
        return state;
    }
}
