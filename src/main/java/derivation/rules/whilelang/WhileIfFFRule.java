package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileIf;
import states.State;
import states.WhileState;

public class WhileIfFFRule extends WhileIfRule implements DerivationRule {
    public WhileIfFFRule(WhileIf ifStatement, WhileState state) {
        super(ifStatement, state);
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processIfFFRule(this);
    }
}
