package converter;

import derivation.rules.DerivationTreeNode;

public interface DerivationTreeConverter {
    String convert(DerivationTreeNode node);

    void processDerivationTreeNode(DerivationTreeNode node);
}
