package com.kiselev.ssu.flanguage.builder.components.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;

import java.util.*;

public class TextOperation implements AutomataOperation {

    private static final String INITIAL_STATE = "1";
    private static final String FINAL_STATE = "2";

    private static final Set<String> INITIAL_STATES = Sets.newHashSet(INITIAL_STATE);
    private static final Set<String> FINAL_STATES = Sets.newHashSet(FINAL_STATE);

    private List<String> words;

    public TextOperation(String... words) {
        this.words = Lists.newArrayList(words);
    }

    @Override
    public Automata compose() {
        Map<String, Map<String, Set<String>>> matrix = Maps.newHashMap();
        Map<String, Set<String>> transition = Maps.newHashMap();
        for (String word : words) {
            transition.put(word, FINAL_STATES);
        }
        matrix.put(INITIAL_STATE, transition);
        Map<String, Set<String>> emptyTransition = Maps.newHashMap();
        for (String word : words) {
            emptyTransition.put(word, Sets.newHashSet());
        }
        matrix.put(FINAL_STATE, emptyTransition);

        return Automata.createBuilder()
                .setName(String.join("", words))
                .setPriority(0)
                .setAlphabet(Sets.newHashSet(words))
                .setInitialState(INITIAL_STATES)
                .setFinalState(FINAL_STATES)
                .setMatrixOfTransitions(matrix)
                .build();
    }
}
