package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.State;
import states.WhileState;

public class WhileDepthLimitRule implements DerivationAxiom {

    private final WhileState state;

    public WhileDepthLimitRule(WhileState state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processDepthLimit();
    }
}
