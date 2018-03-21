package com.kiselev.ssu.flanguage.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.serializer.pojo.AutomataInputEntity;

import java.io.File;
import java.io.IOException;

public class AutomataReader {

    public static Automata read(String fileName) throws IOException {
        AutomataInputEntity inputEntity =
                new ObjectMapper().readValue(new File(fileName), AutomataInputEntity.class);

        return Automata.createBuilder()
                .setName(inputEntity.getName())
                .setPriority(inputEntity.getPriority())
                .setAlphabet(inputEntity.getAlphabet())
                .setInitialState(inputEntity.getInitialState())
                .setFinalState(inputEntity.getFinalState())
                .setMatrixOfTransitions(inputEntity.getMatrixOfTransitions())
                .build();
    }

    public static Automata load(String fileName) {
        try {
            return read(fileName);
        } catch (IOException exception) {
            return null;
        }
    }
}
