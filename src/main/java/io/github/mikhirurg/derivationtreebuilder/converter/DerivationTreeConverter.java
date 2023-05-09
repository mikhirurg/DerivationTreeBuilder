package io.github.mikhirurg.derivationtreebuilder.converter;

import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeNode;

public interface DerivationTreeConverter {
    String convert(DerivationTreeNode node);

    void processDerivationTreeNode(DerivationTreeNode node);
}
