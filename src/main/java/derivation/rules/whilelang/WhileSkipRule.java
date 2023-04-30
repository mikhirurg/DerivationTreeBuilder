package derivation.rules.whilelang;

import states.WhileState;

public class WhileSkipRule implements DerivationAxiom {

    private final WhileState state;

    public WhileSkipRule(WhileState state) {
        this.state = state;
    }

    @Override
    public WhileState getState() {
        return state;
    }
}
