package com.kiselev.ssu.flanguage.builder.components.modifiers.service.impl;

import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.components.modifiers.service.TransitionsService;

import java.util.Map;
import java.util.Set;

public class TransitionsMultiplyService implements TransitionsService {

    @Override
    public void updateTransitions(Automata automata) {
        Map<String, Map<String, Set<String>>> transitions = automata.getMatrixOfTransitions();

        Set<String> initialStates = automata.getInitialState();
        Set<String> finalStates = automata.getFinalState();

        for (String finalState : finalStates) {
            Map<String, Set<String>> matrix = transitions.get(finalState);
            for (String signal : automata.getAlphabet()) {

                Set<String> innerTransitions = getState(matrix.get(signal));

                for (String initialState : initialStates) {
                    Set<String> beginTransitions = getState(transitions.get(initialState).get(signal));
                    innerTransitions.addAll(beginTransitions);
                }

                if (!matrix.containsKey(signal)) {
                    matrix.put(signal, innerTransitions);
                } else {
                    matrix.get(signal).addAll(innerTransitions);
                }
            }
        }
    }

    private Set<String> getState(Set<String> state) {
        return state != null ? state : Sets.newHashSet();
    }
}
