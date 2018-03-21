package com.kiselev.ssu.flanguage.builder.components.modifiers.service.impl;

import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.components.modifiers.service.TransitionsService;
import com.kiselev.ssu.flanguage.serializer.reflection.ReflectiveAutomata;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransitionsOptionalService implements TransitionsService {

    @Override
    public void updateTransitions(Automata automata) {
        Set<String> finalState = Sets.newHashSet(automata.getFinalState());
        finalState.addAll(automata.getInitialState());

        ReflectiveAutomata.of(automata)
                .setFinalState(finalState);
    }
}
