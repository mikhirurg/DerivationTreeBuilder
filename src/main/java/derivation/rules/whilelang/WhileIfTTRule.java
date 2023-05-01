package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileIf;
import states.State;
import states.WhileState;

public class WhileIfTTRule extends WhileIfRule implements DerivationRule {

    public WhileIfTTRule(WhileIf ifStatement, WhileState s) {
        super(ifStatement, s);
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processIfTTRule(this);
    }
}
