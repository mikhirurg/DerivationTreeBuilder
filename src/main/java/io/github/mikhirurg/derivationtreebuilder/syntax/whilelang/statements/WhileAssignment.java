package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.WhileDerivationTreeBuilder;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.WhileArithmeticExpression;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.WhileVar;

public class WhileAssignment implements WhileStatement {

    private final WhileVar variable;

    private final WhileArithmeticExpression expression;

    public WhileAssignment(WhileVar var, WhileArithmeticExpression expression) {
        this.variable = var;
        this.expression = expression;
    }

    public WhileVar getVariable() {
        return variable;
    }

    public WhileArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String textRepresentation() {
        return variable.textRepresentation() + " := " + expression.textRepresentation();
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processAssignment(this);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processAssignment(this);
    }
}
