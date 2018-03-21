package com.kiselev.ssu.flanguage.builder.components.groups;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.annotation.Important;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ConcatenationOperation extends UnionOperation {

    public ConcatenationOperation(List<AutomataOperation> operations) {
        super(operations);
    }

    @Override
    protected void unionInitialStates(Automata automata, Map<String, String> dictionary) {
        // Skip
    }

    @Override
    protected Set<String> getMergedInitialState(Automata first, Automata second) {
        return Sets.newHashSet(first.getInitialState());
    }

    @Override
    protected Set<String> getMergedFinalState(Automata first, Automata second) {
        return Sets.newHashSet(second.getFinalState());
    }

    @Override
    protected Map<String,Map<String,Set<String>>> getMergedMatrixOfTransitions(Automata first, Automata second) {

        Map<String, Map<String, Set<String>>> table = super.getMergedMatrixOfTransitions(first, second);

        Map<String, String> dictionary = Maps.newHashMap();

        for (String state : table.keySet()) {
            dictionary.put(state, state);
        }

        for (String initialState : second.getInitialState()) {
            for (String finalState : first.getFinalState()) {
                dictionary.put(finalState, initialState);
            }
        }

        Map<String, Map<String, Set<String>>> newTransitions = Maps.newHashMap();

        for (Map.Entry<String, Map<String, Set<String>>> entry : table.entrySet()) {
            Map<String, Set<String>> newInnerMap = Maps.newHashMap();
            for (Map.Entry<String, Set<String>> innerEntry : entry.getValue().entrySet()) {
                newInnerMap.put(
                        innerEntry.getKey(),
                        innerEntry.getValue().stream()
                                .map(dictionary::get)
                                .collect(Collectors.toSet()));
            }

            String translatedKey = dictionary.get(entry.getKey());
            if (!newTransitions.containsKey(translatedKey)) {
                newTransitions.put(translatedKey, newInnerMap);
            } else {
                Map<String, Set<String>> map = newTransitions.get(translatedKey);
                for (Map.Entry<String, Set<String>> ie : newInnerMap.entrySet()) {
                    if (!map.containsKey(ie.getKey())) {
                        map.put(ie.getKey(), ie.getValue());
                    } else {
                        map.get(ie.getKey()).addAll(ie.getValue());
                    }
                }
            }
        }

        return newTransitions;
    }
}
