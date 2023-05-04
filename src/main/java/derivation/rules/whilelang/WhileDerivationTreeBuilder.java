package derivation.rules.whilelang;

import derivation.rules.Derivable;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.DerivationTreeNode;
import parser.syntax.SyntaxNode;
import parser.syntax.whilelang.statements.*;
import states.State;
import states.WhileState;

public class WhileDerivationTreeBuilder implements DerivationTreeBuilder {

    private final DerivationTreeNode derivationTreeNode;

    private DerivationTreeNode currentNode;

    private WhileState state;

    private final long depthLimit;

    public WhileDerivationTreeBuilder() {
        this(DERIVATION_DEPTH_LIMIT);
    }

    public WhileDerivationTreeBuilder(long depthLimit) {
        this.depthLimit = depthLimit;
        this.derivationTreeNode = new DerivationTreeNode();
        this.currentNode = derivationTreeNode;
    }

    @Override
    public DerivationTreeNode buildDerivationTree(SyntaxNode program, State state) {
        WhileStatement whileProgram = (WhileStatement) program;
        this.state = (WhileState) state;

        whileProgram.accept(this);

        return derivationTreeNode;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public long getDerivationDepthLimit() {
        return depthLimit;
    }

    public void processWhile(WhileWhile whileStatement) {

        DerivationTreeNode baseNode = currentNode;
        baseNode.setInitialState(state.cloneState());

        if (whileStatement.getCondition().evaluate(state)) {
            Derivable rule = new WhileWhileTTRule(whileStatement, state);
            baseNode.setRule(rule);

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode1 = currentNode;
            subNode1.setDepth(baseNode.getDepth() + 1);
            if (subNode1.getDepth() <= getDerivationDepthLimit()) {
                whileStatement.getStatement().accept(this);
            } else {
                processDepthLimit();
            }

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode2 = currentNode;
            subNode2.setDepth(baseNode.getDepth() + 1);
            if (subNode1.getDepth() <= getDerivationDepthLimit()) {
                whileStatement.accept(this);
            } else {
                processDepthLimit();
            }

            baseNode.getChildren().add(subNode1);
            baseNode.getChildren().add(subNode2);
        } else {
            Derivable rule = new WhileWhileFFRule(whileStatement, state);
            baseNode.setRule(rule);
        }
    }

    public void processComp(WhileComp comp) {
        DerivationTreeNode baseNode = currentNode;
        Derivable rule = new WhileCompRule(comp, state);
        baseNode.setRule(rule);
        baseNode.setInitialState(state.cloneState());

        currentNode = new DerivationTreeNode();
        DerivationTreeNode subNode1 = currentNode;
        subNode1.setDepth(baseNode.getDepth() + 1);
        if (subNode1.getDepth() <= getDerivationDepthLimit()) {
            comp.getStatement1().accept(this);
        } else {
            processDepthLimit();
        }

        currentNode = new DerivationTreeNode();
        DerivationTreeNode subNode2 = currentNode;
        subNode2.setDepth(baseNode.getDepth() + 1);
        if (subNode1.getDepth() <= getDerivationDepthLimit()) {
            comp.getStatement2().accept(this);
        } else {
            processDepthLimit();
        }

        baseNode.getChildren().add(subNode1);
        baseNode.getChildren().add(subNode2);
    }

    public void processIf(WhileIf ifStatement) {

        DerivationTreeNode baseNode = currentNode;
        baseNode.setInitialState(state.cloneState());

        if (ifStatement.getCondition().evaluate(state)) {
            Derivable rule = new WhileIfTTRule(ifStatement, state);
            baseNode.setRule(rule);

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode = currentNode;
            subNode.setDepth(baseNode.getDepth() + 1);
            if (subNode.getDepth() <= getDerivationDepthLimit()) {
                ifStatement.getStatement1().accept(this);
            } else {
                processDepthLimit();
            }

            baseNode.getChildren().add(subNode);
        } else {
            Derivable rule = new WhileIfFFRule(ifStatement, state);
            baseNode.setRule(rule);

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode = currentNode;
            subNode.setDepth(baseNode.getDepth() + 1);
            if (subNode.getDepth() <= getDerivationDepthLimit()) {
                ifStatement.getStatement2().accept(this);
            } else {
                processDepthLimit();
            }

            baseNode.getChildren().add(subNode);
        }
    }

    public void processAssignment(WhileAssignment assignmentStatement) {
        DerivationTreeNode baseNode = currentNode;
        baseNode.setInitialState(state.cloneState());
        state.update(assignmentStatement.getVariable(), assignmentStatement.getExpression().evaluate(state));
        baseNode.setRule(new WhileAssignmentRule(assignmentStatement, state));
    }

    public void processSkip(WhileSkip skipStatement) {
        DerivationTreeNode baseNode = currentNode;
        baseNode.setInitialState(state.cloneState());
        WhileSkipRule rule = new WhileSkipRule(skipStatement, state);
        baseNode.setRule(rule);
    }

    public void processDepthLimit() {
        DerivationTreeNode baseNode = currentNode;
        baseNode.setInitialState(state.cloneState());
        WhileDepthLimitRule rule = new WhileDepthLimitRule(state);
        baseNode.setRule(rule);
    }

}
