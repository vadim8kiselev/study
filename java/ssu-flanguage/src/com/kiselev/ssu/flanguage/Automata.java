package com.kiselev.ssu.flanguage;

import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.exception.AutomataException;
import sun.misc.Regexp;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Automata {

    private String name;

    private Integer priority;

    private Set<String> alphabet;

    private Set<String> initialState;

    private Set<String> finalState;

    private Map<String, Map<String, Set<String>>> matrixOfTransitions;

    private Set<String> currentState;

    private Automata() {
        // private constructor
    }

    public void initAutomata() {
        currentState = new HashSet<>(initialState);
    }

    public void refreshAutomata() {
        initAutomata();
    }

    public void process(Character symbol) {
        Set<String> updateCurrentState = new HashSet<>();
        String key = String.valueOf(symbol);

        boolean valid = currentState.stream()
                .anyMatch(state -> isValidState(state) && isValidKey(state, key));
        if (!valid) {
            throw new AutomataException("No transitions for current state and sent key");
        }

        updateCurrentState.addAll(currentState.stream()
                .map(state -> {
                    if (matrixOfTransitions.get(state) == null || matrixOfTransitions.get(state).get(key) == null) {
                        return Sets.<String>newHashSet();
                    }
                    return matrixOfTransitions.get(state).get(key);
                })
                .flatMap(Set::stream)
                .collect(Collectors.toList()));

        currentState = updateCurrentState;
    }

    private boolean isValidState(String state) {
        return matrixOfTransitions.containsKey(state);
    }

    private boolean isValidKey(String state, String key) {
        return isValidState(state) && matrixOfTransitions.get(state).containsKey(key);
    }

    public boolean isFinalState() {
        return currentState != null && !finalState.stream()
                .filter(currentState::contains)
                .collect(Collectors.toList())
                .isEmpty();
    }

    /*private void compileAlphabet() {
        if (translator != null) {
            List<String> tempAlphabet = new ArrayList<>(alphabet);
            for (String letter : tempAlphabet) {
                List<String> translations = translator.getTranslateElements(letter);
                if (translations != null) {
                    int index = alphabet.indexOf(letter);
                    alphabet.remove(index);
                    for (String translation : translations) {
                        alphabet.add(index, translation);
                        index++;
                    }

                    for (String key : table.keySet()) {
                        Map<String, Set<String>> map = table.get(key);
                        Set<String> value = map.get(letter);
                        map.remove(letter);
                        for (String translation : translations) {
                            map.put(translation, value);
                        }
                    }
                }
            }
        }
    }*/

    public static Builder createBuilder() {
        return new Automata().new Builder();
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public Set<String> getInitialState() {
        return initialState;
    }

    public Set<String> getFinalState() {
        return finalState;
    }

    public Map<String, Map<String, Set<String>>> getMatrixOfTransitions() {
        return matrixOfTransitions;
    }

    public class Builder {

        public Builder setName(String name) {
            Automata.this.name = name;
            return this;
        }

        public Builder setPriority(Integer priority) {
            Automata.this.priority = priority;
            return this;
        }

        public Builder setAlphabet(Set<String> alphabet) {
            Automata.this.alphabet = alphabet;
            return this;
        }

        public Builder setInitialState(Set<String> initialStates) {
            Automata.this.initialState = initialStates;
            return this;
        }

        public Builder setFinalState(Set<String> finalStates) {
            Automata.this.finalState = finalStates;
            return this;
        }

        public Builder setMatrixOfTransitions(Map<String, Map<String, Set<String>>> matrix) {
            Automata.this.matrixOfTransitions = matrix;
            return this;
        }

        public Automata build() {

            if (Automata.this.name == null) {
                System.err.println("Name cannot be null");
                return null;
            }

            if (Automata.this.priority == null) {
                System.err.println("Priority cannot be null");
                return null;
            }

            if (Automata.this.initialState == null) {
                System.err.println("Initial state cannot be null");
                return null;
            }

            if (Automata.this.finalState == null) {
                System.err.println("Final state cannot be null");
                return null;
            }

            if (Automata.this.matrixOfTransitions == null) {
                System.err.println("Matrix of transitions cannot be null");
                return null;
            }

            return Automata.this;
        }
    }
}