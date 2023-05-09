package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileIf;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileIfFFRule extends WhileIfRule implements DerivationRule {
    public WhileIfFFRule(WhileIf ifStatement, WhileState state) {
        super(ifStatement, state);
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processIfFFRule(this);
    }
}
