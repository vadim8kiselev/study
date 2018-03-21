package com.kiselev.ssu.flanguage.builder.components.groups;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.annotation.Important;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;
import com.kiselev.ssu.flanguage.serializer.reflection.ReflectiveAutomata;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnionOperation implements AutomataOperation {

    private List<AutomataOperation> operations;

    public UnionOperation(List<AutomataOperation> operations) {
        this.operations = operations;
    }

    @Override
    public Automata compose() {
        List<Automata> automatas = operations.stream()
                .map(AutomataOperation::compose)
                .collect(Collectors.toList());

        Automata automata = automatas.get(0);
        for (int index = 1; index < automatas.size(); index++) {
            automata = updateAutomatas(automata, automatas.get(index));
            renameAutomatas(automata, automatas.get(index));
        }

        if (automata.getInitialState().contains(null)) {
            ReflectiveAutomata.of(automata)
                    .setInitialState(Sets.newHashSet("1"));
        }

        return automata;
    }

    @Important
    private Automata updateAutomatas(Automata first, Automata second) {

        renameAutomatas(first, second);

        return Automata.createBuilder()
                .setName(first.getName() + second.getName())
                .setPriority(first.getPriority() + second.getPriority())
                .setAlphabet(getMergedAlphabet(first, second))
                .setInitialState(getMergedInitialState(first, second))
                .setFinalState(getMergedFinalState(first, second))
                .setMatrixOfTransitions(getMergedMatrixOfTransitions(first, second))
                .build();
    }

    private void renameAutomatas(Automata first, Automata second) {
        Integer lastUsedID = renameAutomata(first, 1);
        renameAutomata(second, lastUsedID);
    }

    @Important
    private Integer renameAutomata(Automata automata, int id) {
        Map<String, String> dictionary = Maps.newHashMap();
        for (String state : automata.getMatrixOfTransitions().keySet()) {
            dictionary.put(state, String.valueOf(id++));
        }

        unionInitialStates(automata, dictionary);

        Set<String> newInitialState = automata.getInitialState().stream()
                .map(dictionary::get)
                .collect(Collectors.toSet());

        Set<String> newFinalState = automata.getFinalState().stream()
                .map(dictionary::get)
                .collect(Collectors.toSet());

        Map<String, Map<String, Set<String>>> oldTransitions = automata.getMatrixOfTransitions();
        Map<String, Map<String, Set<String>>> newTransitions = Maps.newHashMap();

        for (Map.Entry<String, Map<String, Set<String>>> entry : oldTransitions.entrySet()) {

            Map<String, Set<String>> newInnerMap = Maps.newHashMap();
            for (Map.Entry<String, Set<String>> innerEntry : entry.getValue().entrySet()) {
                newInnerMap.put(
                        innerEntry.getKey(),
                        innerEntry.getValue().stream()
                                .map(dictionary::get)
                                .collect(Collectors.toSet()));
            }
            newTransitions.put(dictionary.get(entry.getKey()), newInnerMap);
        }

        ReflectiveAutomata.of(automata)
                .setInitialState(newInitialState)
                .setFinalState(newFinalState)
                .setMatrixOfTransitions(newTransitions);

        return id;
    }

    protected void unionInitialStates(Automata automata, Map<String, String> dictionary) {
        for (String initialState : automata.getInitialState()) {
            dictionary.put(initialState, "1");
        }
    }

    protected Set<String> getMergedAlphabet(Automata first, Automata second) {
        Set<String> alphabet = Sets.newHashSet();
        alphabet.addAll(first.getAlphabet());
        alphabet.addAll(second.getAlphabet());
        return alphabet;
    }

    protected Set<String> getMergedInitialState(Automata first, Automata second) {
        Set<String> initialState = Sets.newHashSet();
        initialState.addAll(first.getInitialState());
        initialState.addAll(second.getInitialState());
        return initialState;
    }

    protected Set<String> getMergedFinalState(Automata first, Automata second) {
        Set<String> finalState = Sets.newHashSet();
        finalState.addAll(first.getFinalState());
        finalState.addAll(second.getFinalState());
        return finalState;
    }

    protected Map<String, Map<String, Set<String>>> getMergedMatrixOfTransitions(Automata first, Automata second) {
        Map<String, Map<String, Set<String>>> firstMatrixOfTransitions = first.getMatrixOfTransitions();
        Map<String, Map<String, Set<String>>> secondMatrixOfTransitions = second.getMatrixOfTransitions();

        // 1st level
        for (Map.Entry<String, Map<String, Set<String>>> entry : secondMatrixOfTransitions.entrySet()) {
            if (!firstMatrixOfTransitions.keySet().contains(entry.getKey())) {
                firstMatrixOfTransitions.put(entry.getKey(), entry.getValue());
            }
        }

        // 2nd level
        for (Map.Entry<String, Map<String, Set<String>>> entry : secondMatrixOfTransitions.entrySet()) {
            Map<String, Set<String>> firstInnerMap = firstMatrixOfTransitions.get(entry.getKey());
            for (Map.Entry<String, Set<String>> innerEntry : entry.getValue().entrySet()) {
                if (!firstInnerMap.keySet().contains(innerEntry.getKey())) {
                    firstInnerMap.put(innerEntry.getKey(), innerEntry.getValue());
                }
            }
        }

        return firstMatrixOfTransitions;
    }


    protected Set<String> getState(Set<String> state) {
        return state != null ? state : Sets.newHashSet();
    }
}
