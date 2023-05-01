package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.WhileState;

public class WhileSkipRule implements DerivationAxiom {

    private final WhileState state;

    public WhileSkipRule(WhileState state) {
        this.state = state;
    }

    @Override
    public WhileState getState() {
        return state;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processSkipRule(this);
    }
}
