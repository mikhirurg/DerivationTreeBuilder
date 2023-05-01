package parser.syntax.whilelang.booleanexp;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.WhileState;

public class WhileTrueConst implements WhileBooleanExpression {
    @Override
    public String textRepresentation() {
        return "true";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return true;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processTrueConst(this);
    }
}
