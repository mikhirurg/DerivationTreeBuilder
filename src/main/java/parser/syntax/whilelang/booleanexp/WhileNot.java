package parser.syntax.whilelang.booleanexp;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.WhileState;

public class WhileNot implements WhileBooleanExpression {
    private final WhileBooleanExpression expression;

    public WhileNot(WhileBooleanExpression expression) {
        this.expression = expression;
    }

    public WhileBooleanExpression getExpression() {
        return expression;
    }

    @Override
    public String textRepresentation() {
        return "not(" + expression.textRepresentation() + ")";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return !expression.evaluate(state);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processNot(this);
    }
}
