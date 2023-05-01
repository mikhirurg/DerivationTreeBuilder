package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileComp;
import states.State;
import states.WhileState;

public class WhileCompRule extends WhileDerivationRule implements DerivationRule {

    private final WhileComp comp;

    public WhileCompRule(WhileComp comp, WhileState state) {
        super(state);
        this.comp = comp;
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processCompRule(this);
    }

    public WhileComp getComp() {
        return comp;
    }
}
