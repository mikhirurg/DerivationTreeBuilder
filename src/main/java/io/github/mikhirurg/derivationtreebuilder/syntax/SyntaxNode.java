package io.github.mikhirurg.derivationtreebuilder.syntax;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeBuilder;

public interface SyntaxNode {
    String textRepresentation();
    void accept(DerivationTreeBuilder builder);

    void accept(DerivationTreeConverter converter);
}
