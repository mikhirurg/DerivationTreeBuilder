package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileSkip;
import states.WhileState;

public class WhileSkipRule implements DerivationAxiom {

    private final WhileState state;

    private final WhileSkip skip;

    public WhileSkipRule(WhileSkip skip, WhileState state) {
        this.state = state;
        this.skip = skip;
    }

    @Override
    public WhileState getState() {
        return state;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processSkipRule(this);
    }

    public WhileSkip getSkip() {
        return skip;
    }
}
