package derivation.rules.whilelang;

import states.WhileState;

public abstract class WhileDerivationRule {
    protected final WhileState initialState;

    public WhileDerivationRule(WhileState state) {
        this.initialState = state.cloneState();
    }
}
