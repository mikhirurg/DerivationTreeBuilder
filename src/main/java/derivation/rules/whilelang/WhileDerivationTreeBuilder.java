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

    public WhileDerivationTreeBuilder() {
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

    public void processWhile(WhileWhile whileStatement) {

        DerivationTreeNode baseNode = currentNode;

        if (whileStatement.getCondition().evaluate(state)) {
            Derivable rule = new WhileWhileTTRule(whileStatement, state);
            baseNode.setRule(rule);
            baseNode.setInitialState(state.cloneState());

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode1 = currentNode;
            whileStatement.getStatement().accept(this);

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode2 = currentNode;
            whileStatement.accept(this);

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
        comp.getStatement1().accept(this);

        currentNode = new DerivationTreeNode();
        DerivationTreeNode subNode2 = currentNode;
        comp.getStatement2().accept(this);

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
            ifStatement.getStatement1().accept(this);

            baseNode.getChildren().add(subNode);
        } else {
            Derivable rule = new WhileIfFFRule(ifStatement, state);
            baseNode.setRule(rule);

            currentNode = new DerivationTreeNode();
            DerivationTreeNode subNode = currentNode;
            ifStatement.getStatement2().accept(this);

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
        WhileSkipRule rule = new WhileSkipRule(state);
        baseNode.setRule(rule);
    }

}
