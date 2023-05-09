package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public abstract class WhileDerivationRule {
    protected final WhileState initialState;

    public WhileDerivationRule(WhileState state) {
        this.initialState = state.cloneState();
    }
}
