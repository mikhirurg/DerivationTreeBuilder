package io.github.mikhirurg.derivationtreebuilder.derivation.rules;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;

public interface Derivable {
    State getState();

    void accept(DerivationTreeConverter converter);
}
