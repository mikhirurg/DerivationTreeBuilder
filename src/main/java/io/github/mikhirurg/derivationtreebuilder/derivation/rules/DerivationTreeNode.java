package io.github.mikhirurg.derivationtreebuilder.derivation.rules;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;

import java.util.ArrayList;

public class DerivationTreeNode {
    private Derivable rule;

    private State initialState;

    private final ArrayList<DerivationTreeNode> children;

    private long depth = 0;

    public DerivationTreeNode() {
        children = new ArrayList<>();
    }

    public long getDepth() {
        return depth;
    }

    public Derivable getRule() {
        return rule;
    }

    public ArrayList<DerivationTreeNode> getChildren() {
        return children;
    }

    public void setDepth(long depth) {
        this.depth = depth;
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
