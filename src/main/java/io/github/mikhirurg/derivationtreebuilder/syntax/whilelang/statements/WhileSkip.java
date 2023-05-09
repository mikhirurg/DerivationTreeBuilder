package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.WhileDerivationTreeBuilder;

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
