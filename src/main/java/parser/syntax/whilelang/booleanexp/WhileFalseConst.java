package parser.syntax.whilelang.booleanexp;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.WhileState;

public class WhileFalseConst implements WhileBooleanExpression{
    @Override
    public String textRepresentation() {
        return "false";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return false;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processFalseConst(this);
    }
}
