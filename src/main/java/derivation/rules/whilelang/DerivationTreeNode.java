package derivation.rules.whilelang;

import derivation.rules.Derivable;

import java.util.ArrayList;

public class DerivationTreeNode {
    private Derivable rule;

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
}
