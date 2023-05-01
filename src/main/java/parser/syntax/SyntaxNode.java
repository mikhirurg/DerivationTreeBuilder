package parser.syntax;

import converter.DerivationTreeConverter;
import derivation.rules.DerivationTreeBuilder;

public interface SyntaxNode {
    String textRepresentation();
    void accept(DerivationTreeBuilder builder);

    void accept(DerivationTreeConverter converter);
}
