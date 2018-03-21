package com.kiselev.ssu.flanguage.builder.components.modifiers;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;
import com.kiselev.ssu.flanguage.builder.components.modifiers.service.impl.TransitionsMultiplyService;
import com.kiselev.ssu.flanguage.builder.components.modifiers.service.impl.TransitionsOptionalService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StarOperation implements AutomataOperation {

    private AutomataOperation operation;

    public StarOperation(AutomataOperation operation) {
        this.operation = operation;
    }

    @Override
    public Automata compose() {
        Automata automata = operation.compose();

        new TransitionsMultiplyService().updateTransitions(automata);
        new TransitionsOptionalService().updateTransitions(automata);

        return Automata.createBuilder()
                .setName(automata.getName() + "*")
                .setPriority(automata.getPriority())
                .setAlphabet(automata.getAlphabet())
                .setInitialState(Sets.newHashSet(automata.getInitialState()))
                .setFinalState(Sets.newHashSet(automata.getFinalState()))
                .setMatrixOfTransitions(Maps.newHashMap(automata.getMatrixOfTransitions()))
                .build();
    }
}
