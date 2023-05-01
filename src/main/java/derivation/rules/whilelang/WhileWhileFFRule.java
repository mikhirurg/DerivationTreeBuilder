package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileWhile;
import states.WhileState;

public class WhileWhileFFRule extends WhileWhileRule implements DerivationAxiom {

    public WhileWhileFFRule(WhileWhile whileStatement, WhileState state) {
        super(whileStatement, state);
    }

    @Override
    public WhileState getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processWhileFFRule(this);
    }
}
