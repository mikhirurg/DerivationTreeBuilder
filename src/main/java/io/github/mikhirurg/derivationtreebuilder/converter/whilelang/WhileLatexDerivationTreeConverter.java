package io.github.mikhirurg.derivationtreebuilder.converter.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.DerivationTreeNode;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class WhileLatexDerivationTreeConverter implements WhileDerivationTreeConverter {

    private final StringBuilder currentBuilder;
    private boolean isExplicitState;

    public WhileLatexDerivationTreeConverter(boolean isExplicitState) {
        this.currentBuilder = new StringBuilder();
        this.isExplicitState = isExplicitState;
    }

    public WhileLatexDerivationTreeConverter() {
        this(true);
    }

    public void setExplicitState(boolean isExplicitState) {
        this.isExplicitState = isExplicitState;
    }

    @Override
    public String convert(DerivationTreeNode node) {
        node.accept(this);
        String treeContent = currentBuilder.toString();
        currentBuilder.setLength(0);

        currentBuilder.append("\\documentclass[a4paper,landscape]{article}\n")
                .append("\\usepackage{a4wide}\n")
                .append("\\usepackage{graphicx} % Required for inserting images\n")
                .append("\\usepackage{hyperref}\n")
                .append("\\usepackage{enumerate}\n")
                .append("\\usepackage{amsfonts}\n")
                .append("\\usepackage{prooftree}\n")
                .append("\\usepackage{lstwhile}\n")
                .append("\\usepackage{scdefspub}\n")
                .append("\\usepackage{verbatim}\n")
                .append("\\usepackage{enumitem}\n")
                .append("\\usepackage{mathtools}\n")
                .append("\\usepackage{adjustbox}")
                .append("\\begin{document}\n")
                .append("\\noindent\\makebox[\\linewidth]{\n")
                .append("\\resizebox{\\paperwidth}{!}{\n");

        currentBuilder.append("\\begin{prooftree}\n").append(treeContent).append("\n\\end{prooftree}");

        currentBuilder.append("}\n")
                .append("}\n")
                .append("\\end{document}");

        return currentBuilder.toString();
    }

    @Override
    public void processDerivationTreeNode(DerivationTreeNode node) {
        node.getRule().accept(this);
        String rule = currentBuilder.toString().split("\n")[0];
        String statement = currentBuilder.toString().split("\n")[1];

        currentBuilder.setLength(0);
        node.initialState().accept(this);
        String startState = currentBuilder.toString();

        currentBuilder.setLength(0);
        node.finalState().accept(this);
        String finalState = currentBuilder.toString();

        ArrayList<String> childrenText = new ArrayList<>();
        for (DerivationTreeNode child : node.getChildren()) {
            currentBuilder.setLength(0);
            child.accept(this);
            childrenText.add("\\[\n" + currentBuilder + "\n\\]");
        }

        currentBuilder.setLength(0);

        if (node.getChildren().size() > 0) {
            currentBuilder
                    .append(String.join("\n", childrenText))
                    .append("\n\\justifies\n")
                    .append("\\nstrans{\\mbox{")
                    .append(statement).append("}}")
                    .append("{\\mbox{").append(startState).append("}}")
                    .append("{\\mbox{").append(finalState).append("}}\n")
                    .append("\\using{").append(rule).append("}");
        } else {
            currentBuilder.append("\\axiomrule{")
                    .append(rule)
                    .append("}")
                    .append("\n\\nstrans{\\mbox{")
                    .append(statement).append("}}")
                    .append("{\\mbox{").append(startState).append("}}")
                    .append("{\\mbox{").append(finalState).append("}}");
        }
    }

    @Override
    public void processConst(WhileConst whileConst) {
        currentBuilder.setLength(0);
        currentBuilder.append(whileConst.getNumber().getValue());
    }

    @Override
    public void processMult(WhileMult mult) {
        currentBuilder.setLength(0);

        mult.getLeft().accept(this);
        String left = currentBuilder.toString();

        mult.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" \\cdot ")
                .append(right)
                .append(")");
    }

    @Override
    public void processPlus(WhilePlus plus) {
        currentBuilder.setLength(0);

        plus.getLeft().accept(this);
        String left = currentBuilder.toString();

        plus.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" + ")
                .append(right)
                .append(")");
    }

    @Override
    public void processSub(WhileSub sub) {
        currentBuilder.setLength(0);

        sub.getLeft().accept(this);
        String left = currentBuilder.toString();

        sub.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" - ")
                .append(right)
                .append(")");
    }

    @Override
    public void processVar(WhileVar var) {
        currentBuilder.setLength(0);
        currentBuilder.append(var.getVarName());
    }

    @Override
    public void processEq(WhileEq eq) {
        currentBuilder.setLength(0);

        eq.getLeft().accept(this);
        String left = currentBuilder.toString();

        eq.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" = ")
                .append(right)
                .append(")");
    }

    @Override
    public void processTrueConst(WhileTrueConst trueConst) {
        currentBuilder.setLength(0);
        currentBuilder.append("\\textbf{true}");
    }

    @Override
    public void processFalseConst(WhileFalseConst falseConst) {
        currentBuilder.setLength(0);
        currentBuilder.append("\\textbf{false}");
    }

    @Override
    public void processLeq(WhileLeq leq) {
        currentBuilder.setLength(0);
        leq.getLeft().accept(this);
        String left = currentBuilder.toString();

        currentBuilder.setLength(0);
        leq.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" \\leq ")
                .append(right)
                .append(")");
    }

    @Override
    public void processNot(WhileNot not) {
        currentBuilder.setLength(0);
        not.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("\\neg (").append(expressionText).append(")");
    }

    @Override
    public void processAnd(WhileAnd and) {
        currentBuilder.setLength(0);
        and.getLeft().accept(this);
        String left = currentBuilder.toString();

        currentBuilder.setLength(0);
        and.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" \\land ")
                .append(right)
                .append(")");
    }

    @Override
    public void processAssignment(WhileAssignment assignment) {
        currentBuilder.setLength(0);
        assignment.getVariable().accept(this);
        String variableText = currentBuilder.toString();

        currentBuilder.setLength(0);
        assignment.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append(variableText).append(" $\\coloneqq$ ").append(expressionText);

    }

    @Override
    public void processSkip(WhileSkip skip) {
        currentBuilder.setLength(0);
        currentBuilder.append("skip");
    }

    @Override
    public void processComp(WhileComp comp) {
        currentBuilder.setLength(0);
        comp.getStatement1().accept(this);
        String statement1Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        comp.getStatement2().accept(this);
        String statement2Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append(statement1Text).append("; ").append(statement2Text);
    }

    @Override
    public void processIf(WhileIf whileIf) {
        currentBuilder.setLength(0);
        whileIf.getCondition().accept(this);
        String conditionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileIf.getStatement1().accept(this);
        String statement1Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileIf.getStatement2().accept(this);
        String statement2Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("if $").append(conditionText)
                .append("$ then")
                .append("(")
                .append(statement1Text)
                .append(") ")
                .append("else")
                .append(" (")
                .append(statement2Text)
                .append(")");
    }

    @Override
    public void processWhile(WhileWhile whileWhile) {
        currentBuilder.setLength(0);
        whileWhile.getCondition().accept(this);
        String conditionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileWhile.getStatement().accept(this);
        String statementText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("while ")
                .append("$").append(conditionText)
                .append("$ do (")
                .append(statementText)
                .append(")");
    }


    @Override
    public void processAssignmentRule(WhileAssignmentRule assignmentRule) {
        currentBuilder.setLength(0);
        assignmentRule.getAssignment().accept(this);
        String assignmentText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{ass$_{ns}$}]").append("\n").append(assignmentText);
    }

    @Override
    public void processCompRule(WhileCompRule compRule) {
        currentBuilder.setLength(0);
        compRule.getComp().accept(this);
        String compText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{comp$_{ns}$}]").append("\n").append(compText);
    }

    @Override
    public void processIfFFRule(WhileIfFFRule ifFFRule) {
        currentBuilder.setLength(0);
        ifFFRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{if$^{ff}_{ns}$}]").append("\n").append(ifText);

    }

    @Override
    public void processIfTTRule(WhileIfTTRule ifTTRule) {
        currentBuilder.setLength(0);
        ifTTRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{if$^{tt}_{ns}$}]").append("\n").append(ifText);
    }

    @Override
    public void processSkipRule(WhileSkipRule skipRule) {
        currentBuilder.setLength(0);
        skipRule.getSkip().accept(this);
        String skipText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[skip]").append("\n").append(skipText);
    }

    @Override
    public void processWhileFFRule(WhileWhileFFRule whileFFRule) {
        currentBuilder.setLength(0);
        whileFFRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{while$^{ff}_{ns}$}]").append("\n").append(whileText);
    }

    @Override
    public void processWhileTTRule(WhileWhileTTRule whileTTRule) {
        currentBuilder.setLength(0);
        whileTTRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[\\mbox{while$^{tt}_{ns}$}]").append("\n").append(whileText);
    }

    @Override
    public void processDepthLimit() {
        currentBuilder.setLength(0);
        currentBuilder.append("[\\dots]\n\\dots");
    }

    @Override
    public void processWhileState(WhileState state) {
        currentBuilder.setLength(0);

        if (isExplicitState) {
            currentBuilder.append("s$_{");
            for (Map.Entry<WhileVar, Integer> entry : state.getMap().entrySet()) {
                currentBuilder.append(entry.getKey().getVarName()).append("=").append(entry.getValue()).append(", ");
            }
            if (state.getMap().size() > 0) {
                currentBuilder.setLength(currentBuilder.length() - 2);
            }
            currentBuilder.append("}$");
        } else {
            currentBuilder.append("s$_{");
            ArrayList<WhileASCIIDerivationTreeConverter.Pair<WhileVar, Integer>> entrySet = new ArrayList<>();
            for (Map.Entry<WhileVar, Integer> elem : state.getMap().entrySet()) {
                entrySet.add(WhileASCIIDerivationTreeConverter.Pair.of(elem.getKey(), elem.getValue()));
            }
            entrySet.sort(Comparator.comparing(WhileASCIIDerivationTreeConverter.Pair::getFirst));
            for (WhileASCIIDerivationTreeConverter.Pair<WhileVar, Integer> entry : entrySet) {
                currentBuilder.append(entry.getSecond()).append(", ");
            }
            if (state.getMap().size() > 0) {
                currentBuilder.setLength(currentBuilder.length() - 2);
            }
            currentBuilder.append("}$");
        }
    }
}
