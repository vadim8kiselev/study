package com.kiselev.ssu.flanguage.task;

import com.google.common.collect.Lists;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.annotation.Important;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;
import com.kiselev.ssu.flanguage.exception.AutomataException;
import com.kiselev.ssu.flanguage.serializer.AutomataLoader;
import com.kiselev.ssu.flanguage.serializer.reflection.ReflectiveAutomata;
import com.kiselev.ssu.flanguage.task.union.TaskSolver;
import org.apache.commons.io.FileUtils;

import static com.kiselev.ssu.flanguage.builder.Operations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FourthTask implements AbstractTask {

    @Override
    public void solve() throws IOException {
        List<Automata> automatas = Lists.newArrayList();

        List<String> lexemes = readLexemesFromFile("configuration/lexeme/lex.lex");
        for (String lexeme : lexemes) {
            String lexemeName = retrieveLexemeName(lexeme);
            Integer priority = retrievePriority(lexeme);
            String regex = retrieveRegex(lexeme);
            ReflectiveAutomata reflectiveAutomata = ReflectiveAutomata.of(parseRegex(regex));
            reflectiveAutomata.setName(lexemeName);
            reflectiveAutomata.setPriority(priority);
            automatas.add(reflectiveAutomata.getAutomata());
        }

        for (Automata automata : automatas) {
            AutomataLoader.writeAutomataToFile(automata,
                    "configuration/output/" + automata.getName() + ".json");
        }

        TaskSolver.solve(automatas);
    }

    private List<String> readLexemesFromFile(String fileName) {
        try {
            return Arrays.asList(FileUtils.readFileToString(new File(fileName), "utf-8")
                    .split("\n"));
        } catch (IOException exception) {
            return Collections.singletonList("");
        }
    }

    private String retrieveLexemeName(String row) {
        return row.replace(" ", "")
                .split(":")[0];
    }

    private Integer retrievePriority(String row) {
        return Integer.parseInt(row.replace(" ", "")
                .split(":")[1]
                .replace("\r", ""));
    }

    private String retrieveRegex(String row) {
        return row.replace(" ", "")
                .split(":")[2]
                .replace("\r", "");
    }

    @Important
    private Automata parseRegex(String regex) {
        List<AutomataOperation> operations = parseRegexRecursively(regex);
        AutomataOperation operationToExecute;
        if (operations.size() == 1) {
            operationToExecute = operations.get(0);
        } else {
            operationToExecute = concat(operations);
        }
        return execute(operationToExecute);
    }

    // Groups
    private Map<Integer,Integer> retrieveGroupsBounds(String regex) {
        Map<Integer, Integer> groups = new HashMap<>();

        validateRegexGroups(regex);

        fillGroups(groups, regex);

        return groups;
    }

    private void validateRegexGroups(String regex) {
        if (numberOf(regex, "(") - numberOf(regex, "\\(") != numberOf(regex, ")") - numberOf(regex, "\\)")
                || numberOf(regex, "[") != numberOf(regex, "]")) {
            throw new AutomataException("Invalid number of braces or brackets in input regex: " + regex);
        }
    }

    private int numberOf(String regex, String token) {
        int number = 0;
        int position = 0;

        while ((position = regex.indexOf(token, position)) != -1) {
            position++;
            number++;
        }
        return number;
    }

    private void fillGroups(Map<Integer, Integer> groups, String regex) {
        List<Integer> openBraces = Lists.newArrayList();
        List<Integer> closedBraces = Lists.newArrayList();
        List<Integer> openBrackets = Lists.newArrayList();
        List<Integer> closedBrackets = Lists.newArrayList();

        for (int index = 0; index < regex.length(); index++) {
            char character = regex.charAt(index);
            switch (character) {
                case '(': openBraces.add(index); break;
                case ')': closedBraces.add(index); break;
                case '[': openBrackets.add(index); break;
                case ']': closedBrackets.add(index); break;
            }
        }

        fillInnerGroups(groups, openBraces, closedBraces);
        fillInnerGroups(groups, openBrackets, closedBrackets);
    }

    private void fillInnerGroups(Map<Integer, Integer> groups, List<Integer> open, List<Integer> closed) {
        for (Integer closedPosition : closed) {
            Integer lastAvailableOpenPosition = getFirstAvailableOpenPosition(groups, open);

            for (Integer openPosition : open) {
                if (groups.keySet().contains(openPosition)) {
                    continue;
                }

                if (openPosition > closedPosition) {
                    groups.put(lastAvailableOpenPosition, closedPosition);
                    break;
                } else {
                    lastAvailableOpenPosition = openPosition;
                }
            }

            if (!groups.keySet().contains(lastAvailableOpenPosition)) {
                groups.put(lastAvailableOpenPosition, closedPosition);
            }
        }
    }

    private Integer getFirstAvailableOpenPosition(Map<Integer, Integer> groups, List<Integer> open) {
        for (Integer position : open) {
            if (!groups.keySet().contains(position)) {
                return position;
            }
        }
        return null;
    }
    // Groups

    @Important
    private List<AutomataOperation> parseRegexRecursively(String regex) {
        Map<Integer, Integer> groups = retrieveGroupsBounds(regex);
        List<AutomataOperation> operations = new ArrayList<>();

        for (int index = 0; index < regex.length(); ) {
            char character = regex.charAt(index);

            switch (character) {
                case ' '  :
                case '\n' :
                case '\t' :
                case '\r' : index++;
                            break;

                case '\\' :
                            AutomataOperation slashOperator = parseAfterSlashSymbol(regex.substring(index, index + 2));
                            if (isModifierAfter(regex, index + 1)) {
                                slashOperator = modify(slashOperator, regex.charAt(index + 2));
                                index++;
                            }
                            index += 2;
                            operations.add(slashOperator);
                            break;

                case '('  : String braceGroup = regex.substring(index + 1, groups.get(index));
                            List<AutomataOperation> braceOperations = parseBraceRegex(braceGroup);
                            AutomataOperation braceOperation;
                            if (braceOperations.size() == 1) {
                                braceOperation = braceOperations.get(0);
                            } else {
                                braceOperation = union(braceOperations);
                            }
                            index = groups.get(index);
                            if (isModifierAfter(regex, index)) {
                                braceOperation = modify(braceOperation, regex.charAt(index + 1));
                                index += 1;
                            }
                            index++;
                            operations.add(braceOperation);
                            break;

                case '['  : String bracketGroup = regex.substring(index + 1, groups.get(index));
                            List<AutomataOperation> bracketOperations = parseBracketRegex(bracketGroup);
                            AutomataOperation bracketOperation;
                            if (bracketOperations.size() == 1) {
                                bracketOperation = bracketOperations.get(0);
                            } else {
                                bracketOperation = union(bracketOperations);

                            }
                            index = groups.get(index);
                            if (isModifierAfter(regex, index)) {
                                bracketOperation = modify(bracketOperation, regex.charAt(index + 1));
                                index += 1;
                            }
                            index++;
                            operations.add(bracketOperation);
                            break;
                default:
                            AutomataOperation textOperation = text(character);
                            if (isModifierAfter(regex, index)) {
                                textOperation = modify(textOperation, regex.charAt(index + 1));
                                index++;
                            }
                            index++;
                            operations.add(textOperation);
            }
        }

        return operations;
    }

    private List<AutomataOperation> parseBraceRegex(String braceGroup) {
        Map<Integer, Integer> groupsPositions = retrieveGroupsBounds(braceGroup);
        List<Integer> availableSeparators = getAvailableSeparators(braceGroup, groupsPositions);

        List<String> groups = Lists.newArrayList();
        if (availableSeparators.size() > 1) {
            int start = 0;
            for (Integer availableSeparator : availableSeparators) {
                groups.add(braceGroup.substring(start, availableSeparator));
                start = availableSeparator + 1;
            }
            groups.add(braceGroup.substring(start));
        } else if (availableSeparators.size() == 1) {
            Integer separator = availableSeparators.get(0);
            groups.add(braceGroup.substring(0, separator));
            groups.add(braceGroup.substring(separator + 1));
        } else if (availableSeparators.size() == 0) {
            groups.add(braceGroup);
        }

        List<AutomataOperation> operations = Lists.newArrayList();

        for (String group : groups) {
            Map<Integer, Integer> innerGroupsPositions = retrieveGroupsBounds(group);
            List<AutomataOperation> innerOperations = parseRegexRecursively(group);
            AutomataOperation innerOperation;

            if (getAvailableSeparators(group, innerGroupsPositions).isEmpty()) {
                if (innerOperations.size() == 1) {
                    innerOperation = innerOperations.get(0);
                } else {
                    innerOperation = concat(innerOperations);
                }
            } else {
                if (innerOperations.size() == 1) {
                    innerOperation = innerOperations.get(0);
                } else {
                    innerOperation = union(innerOperations);
                }
            }
            operations.add(innerOperation);
        }

        return operations;
    }

    private List<Integer> getAvailableSeparators(String group, Map<Integer, Integer> positions) {
        List<Integer> separators = Lists.newArrayList();
        int position = 0;
        while ((position = group.indexOf("|", position)) != -1) {
            separators.add(position);
            position++;
        }

        return separators.stream()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer position) {
                        for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
                            if (entry.getKey() < position && position < entry.getValue()) {
                                return false;
                            }
                        }
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<AutomataOperation> parseBracketRegex(String bracketGroup) {
        return Lists.newArrayList(parseRegexRecursively(bracketGroup));
    }

    private AutomataOperation parseAfterSlashSymbol(String text) {
        switch (text) {
            case "\\s": return text(' ');
            case "\\+": return text('+');
            case "\\*": return text('*');
            case "\\-": return text('-');
            case "\\.": return text('.');
            case "\\(": return text('(');
            case "\\)": return text(')');
            case "\\d": return text(digits());
            case "\\w": return text(symbols());
        }

        return text(text);
    }

    private String[] digits() {
        List<String> list = Lists.newArrayList();
        for (int index = 0; index < 10; index++) {
            list.add(String.valueOf(index));
        }
        return list.toArray(new String[]{});
    }

    private String[] symbols() {
        List<String> list = Lists.newArrayList();
        for (int index = 65; index <= 90; index++) {
            list.add(String.valueOf((char) index));
        }
        for (int index = 97; index <= 122; index++) {
            list.add(String.valueOf((char) index));
        }
        return list.toArray(new String[]{});
    }

    private boolean isModifierAfter(String regex, Integer position) {
        if (regex.length() - 1 < position + 1) {
            return false;
        }
        char character = regex.charAt(position + 1);
        return character == '+' || character == '*' || character == '?';
    }
}