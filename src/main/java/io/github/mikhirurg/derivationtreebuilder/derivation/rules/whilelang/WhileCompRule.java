package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileComp;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileCompRule extends WhileDerivationRule implements DerivationRule {

    private final WhileComp comp;

    public WhileCompRule(WhileComp comp, WhileState state) {
        super(state);
        this.comp = comp;
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processCompRule(this);
    }

    public WhileComp getComp() {
        return comp;
    }
}
