package derivation.rules.whilelang;

import states.WhileState;

public abstract class WhileDerivationRule {
    protected final WhileState state;

    public WhileDerivationRule(WhileState state) {
        this.state = state.cloneState();
    }
}
