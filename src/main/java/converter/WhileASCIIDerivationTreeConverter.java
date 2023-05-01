package converter;

import derivation.rules.DerivationTreeNode;
import derivation.rules.whilelang.*;
import parser.syntax.whilelang.arithmeticexp.*;
import parser.syntax.whilelang.booleanexp.*;
import parser.syntax.whilelang.statements.*;
import states.WhileState;

import java.util.*;
import java.util.stream.Collectors;

public class WhileASCIIDerivationTreeConverter implements WhileDerivationTreeConverter {

    private final boolean IS_EXPLICIT_STATE_REPRESENTATION = true;

    private final StringBuilder stringBuilder;

    private final StringBuilder currentBuilder;

    public WhileASCIIDerivationTreeConverter() {
        this.stringBuilder = new StringBuilder();
        this.currentBuilder = new StringBuilder();
    }

    public static class Pair<K, V> {
        private final K first;
        private final V second;

        public static <K, V> Pair<K, V> of(K first, V second) {
            return new Pair<>(first, second);
        }

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }

    @Override
    public String convert(DerivationTreeNode node) {

        Queue<Pair<Integer, DerivationTreeNode>> queue = new LinkedList<>();
        queue.add(Pair.of(0, node));

        ArrayList<ArrayList<ArrayList<String>>> text = new ArrayList<>();

        while (queue.size() > 0) {
            Pair<Integer, DerivationTreeNode> treeNode = queue.poll();

            if (treeNode.getFirst() == -1) {
                text.get(text.size() - 1).add(new ArrayList<>());
            } else {
                processDerivationTreeNode(treeNode.getSecond());
                String rule = currentBuilder.toString();

                if (text.size() <= treeNode.getFirst()) {
                    text.add(new ArrayList<>());
                    text.get(text.size() - 1).add(new ArrayList<>());
                }
                text.get(treeNode.getFirst()).get(text.get(treeNode.getFirst()).size() - 1).add(rule);

                for (DerivationTreeNode childNode : treeNode.getSecond().getChildren()) {
                    queue.add(Pair.of(treeNode.first + 1, childNode));
                }

                if (treeNode.getSecond().getChildren().size() > 0) {
                    queue.add(Pair.of(-1, null));
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        /*for (int i = text.size() - 1; i >= 0; i--) {
            StringBuilder ruleLineBuilder = new StringBuilder();
            StringBuilder statementLineBuilder = new StringBuilder();
            for (String str : text.get(i)) {
                String[] parts = str.split("\n");
                String transition = parts[0];
                String ruleName = parts[1];


                int diff1 = Math.abs(ruleLineBuilder.length() - statementLineBuilder.length());
                statementLineBuilder.append("<").append(statement).append(", ")
                        .append(initialState).append(">").append(" -> ").append(finalState).append(" ");

                int diff2 = Math.abs(ruleLineBuilder.length() - statementLineBuilder.length());
                ruleLineBuilder.append(" ".repeat(diff1)).append("-".repeat(diff2)).append(ruleName).append(" ");

                int diff3 = Math.abs(ruleLineBuilder.length() - statementLineBuilder.length());
                statementLineBuilder.append(" ".repeat(diff3));

            }
            builder.append(ruleLineBuilder).append("\n").append(statementLineBuilder).append("\n");
        }*/

        return builder.toString();
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

        currentBuilder.setLength(0);
        currentBuilder.append("<")
                .append(statement)
                .append(", ")
                .append(startState)
                .append("> -> ")
                .append(finalState)
                .append("\n")
                .append(rule);
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
                .append(" * ")
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
        currentBuilder.append("true");
    }

    @Override
    public void processFalseConst(WhileFalseConst falseConst) {
        currentBuilder.setLength(0);
        currentBuilder.append("false");
    }

    @Override
    public void processLeq(WhileLeq leq) {
        currentBuilder.setLength(0);

        leq.getLeft().accept(this);
        String left = currentBuilder.toString();

        leq.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" <= ")
                .append(right)
                .append(")");
    }

    @Override
    public void processNot(WhileNot not) {
        currentBuilder.setLength(0);
        not.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.append("not(").append(expressionText).append(")");
    }

    @Override
    public void processAnd(WhileAnd and) {
        currentBuilder.setLength(0);

        and.getLeft().accept(this);
        String left = currentBuilder.toString();

        and.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" and ")
                .append(right)
                .append(")");
    }

    @Override
    public void processAssignment(WhileAssignment assignment) {
        currentBuilder.setLength(0);

        assignment.getVariable().accept(this);
        String variableText = currentBuilder.toString();

        assignment.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append(variableText).append(" := ").append(expressionText);
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
        currentBuilder.append("if ").append(conditionText)
                .append(" then")
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
        currentBuilder.append("while ").append(conditionText).append(" do (").append(statementText).append(")");
    }

    @Override
    public void processAssignmentRule(WhileAssignmentRule assignmentRule) {
        currentBuilder.setLength(0);
        assignmentRule.getAssignment().accept(this);
        String assignmentText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[ass ns]").append("\n").append(assignmentText);
    }

    @Override
    public void processCompRule(WhileCompRule compRule) {
        currentBuilder.setLength(0);
        compRule.getComp().accept(this);
        String compText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[comp ns]").append("\n").append(compText);
    }

    @Override
    public void processIfFFRule(WhileIfFFRule ifFFRule) {
        currentBuilder.setLength(0);
        ifFFRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[if ff ns]").append("\n").append(ifText);
    }

    @Override
    public void processIfTTRule(WhileIfTTRule ifTTRule) {
        currentBuilder.setLength(0);
        ifTTRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[if tt ns]").append("\n").append(ifText);
    }

    @Override
    public void processSkipRule(WhileSkipRule skipRule) {
        currentBuilder.setLength(0);
        currentBuilder.append("[skip]");
    }

    @Override
    public void processWhileFFRule(WhileWhileFFRule whileFFRule) {
        currentBuilder.setLength(0);
        whileFFRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[while ff ns]").append("\n").append(whileText);
    }

    @Override
    public void processWhileTTRule(WhileWhileTTRule whileTTRule) {
        currentBuilder.setLength(0);
        whileTTRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[while tt ns]").append("\n").append(whileText);
    }

    @Override
    public void processWhileState(WhileState state) {
        currentBuilder.setLength(0);

        if (IS_EXPLICIT_STATE_REPRESENTATION) {
            currentBuilder.append("s_{");
            for (Map.Entry<WhileVar, Integer> entry : state.getMap().entrySet()) {
                currentBuilder.append(entry.getKey().getVarName()).append("=").append(entry.getValue()).append(", ");
            }
            currentBuilder.setLength(currentBuilder.length() - 2);

            if (state.getMap().entrySet().size() > 0) {
                currentBuilder.append("}");
            }
        } else {
            currentBuilder.append("s_{");
            Set<Map.Entry<WhileVar, Integer>> entrySet = state.getMap().entrySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
            for (Map.Entry<WhileVar, Integer> entry : entrySet) {
                currentBuilder.append(entry.getValue()).append(", ");
            }
            currentBuilder.setLength(currentBuilder.length() - 2);

            if (state.getMap().entrySet().size() > 0) {
                currentBuilder.append("}");
            }
        }
    }
}
