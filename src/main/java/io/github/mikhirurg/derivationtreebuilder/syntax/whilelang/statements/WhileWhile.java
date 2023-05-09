package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.WhileDerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp.WhileBooleanExpression;

public class WhileWhile implements WhileStatement {

    private final WhileBooleanExpression condition;

    private final WhileStatement statement;

    public WhileWhile(WhileBooleanExpression condition, WhileStatement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public WhileBooleanExpression getCondition() {
        return condition;
    }

    public WhileStatement getStatement() {
        return statement;
    }

    @Override
    public String textRepresentation() {
        return "while " + condition + " do " + statement;
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processWhile(this);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processWhile(this);
    }
}
