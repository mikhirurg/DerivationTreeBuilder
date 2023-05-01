package derivation.rules;

import converter.DerivationTreeConverter;
import states.State;

import java.util.ArrayList;

public class DerivationTreeNode {
    private Derivable rule;

    private State initialState;

    private final ArrayList<DerivationTreeNode> children;

    public DerivationTreeNode() {
        children = new ArrayList<>();
    }

    public Derivable getRule() {
        return rule;
    }

    public ArrayList<DerivationTreeNode> getChildren() {
        return children;
    }

    public void setRule(Derivable rule) {
        this.rule = rule;
    }

    public void setInitialState(State state) {
        this.initialState = state;
    }
    public State initialState() {
        return initialState;
    }

    public State finalState() {
        if (children.size() > 0) {
            return children.get(children.size() - 1).finalState();
        } else {
            return rule.getState();
        }
    }

    public void accept(DerivationTreeConverter derivationTreeConverter) {
        derivationTreeConverter.processDerivationTreeNode(this);
    }
}
