package com.kiselev.ssu.flanguage.builder.components.modifiers;

import com.google.common.collect.Maps;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.AutomataOperation;
import com.kiselev.ssu.flanguage.builder.components.modifiers.service.impl.TransitionsMultiplyService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlusOperation implements AutomataOperation {

    private AutomataOperation operation;

    public PlusOperation(AutomataOperation operation) {
        this.operation = operation;
    }

    @Override
    public Automata compose() {Automata automata = operation.compose();
        new TransitionsMultiplyService().updateTransitions(automata);

        return Automata.createBuilder()
                .setName(automata.getName() + "+")
                .setPriority(automata.getPriority())
                .setAlphabet(automata.getAlphabet())
                .setInitialState(automata.getInitialState())
                .setFinalState(automata.getFinalState())
                .setMatrixOfTransitions(Maps.newHashMap(automata.getMatrixOfTransitions()))
                .build();
    }
}
