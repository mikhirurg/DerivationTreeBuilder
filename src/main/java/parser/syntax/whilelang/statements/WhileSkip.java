package parser.syntax.whilelang.statements;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;

public class WhileSkip implements WhileStatement {

    @Override
    public String textRepresentation() {
        return "skip";
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processSkip(this);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processSkip(this);
    }
}
