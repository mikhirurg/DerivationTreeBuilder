package derivation.rules.whilelang;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.statements.WhileAssignment;
import states.WhileState;

public class WhileAssignmentRule extends WhileDerivationRule implements DerivationAxiom {

    private final WhileAssignment assignment;
    public WhileAssignmentRule(WhileAssignment assignment, WhileState state) {
        super(state);
        this.assignment = assignment;
    }

    @Override
    public WhileState getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processAssignmentRule(this);
    }

    public WhileAssignment getAssignment() {
        return assignment;
    }
}
