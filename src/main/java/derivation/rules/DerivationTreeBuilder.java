package derivation.rules;

import derivation.rules.whilelang.DerivationTreeNode;
import parser.syntax.SyntaxNode;
import states.State;

public interface DerivationTreeBuilder {
    DerivationTreeNode buildDerivationTree(SyntaxNode program, State state);

    State getState();
}
