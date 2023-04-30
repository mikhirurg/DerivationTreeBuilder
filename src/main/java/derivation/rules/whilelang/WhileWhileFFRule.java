package derivation.rules.whilelang;

import parser.syntax.whilelang.statements.WhileWhile;
import states.WhileState;

public class WhileWhileFFRule extends WhileWhileRule implements DerivationAxiom {

    public WhileWhileFFRule(WhileWhile whileStatement, WhileState state) {
        super(whileStatement, state);
    }

    @Override
    public WhileState getState() {
        return state;
    }
}
