package io.github.mikhirurg.derivationtreebuilder.derivation.rules.states;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;

public interface State {
    public String getTextRepresentation();

    void accept(DerivationTreeConverter converter);
}
