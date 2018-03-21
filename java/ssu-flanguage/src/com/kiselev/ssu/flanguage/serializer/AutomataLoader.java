package com.kiselev.ssu.flanguage.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.serializer.pojo.AutomataInputEntity;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AutomataLoader {

    public static void writeAutomataToFile(Automata automata, String fileName) throws IOException {
        new ObjectMapper().writeValue(new File(fileName), automata);
    }

    public static void writeAutomataToFile(String fileName) throws IOException {
        AutomataInputEntity automataInputEntity = new AutomataInputEntity();
        automataInputEntity.setName(getName());
        automataInputEntity.setPriority(getPriority());
        automataInputEntity.setAlphabet(getAlphabet());
        automataInputEntity.setInitialState(getInitialState());
        automataInputEntity.setFinalState(getFinalState());
        automataInputEntity.setMatrixOfTransitions(getMatrixOfTransitions());

        new ObjectMapper().writeValue(new File(fileName), automataInputEntity);
    }

    private static String getName() {
        return "Identify";
    }

    private static Integer getPriority() {
        return 0;
    }

    private static Set<String> getAlphabet() {
        return Sets.newHashSet();
    }

    private static Set<String> getInitialState() {
        return buildSet("A");
    }

    private static Set<String> getFinalState() {
        return buildSet("B", "C");
    }

    private static Map<String, Map<String, Set<String>>> getMatrixOfTransitions() {
        Map<String, Map<String, Set<String>>> map = new HashMap<>();

        List<String> signals = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_", "'", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        List<String> signals1 = Arrays.asList("<", ">", "+", "-", "!", "#", "*", "/", "&", "$", "@", "~");

        for (String signal : signals) {
            buildMatrix(map, "A", signal, "B");
            buildMatrix(map, "B", signal, "B");
            buildMatrix(map, "C", signal, null);
        }

        for (String signal : signals1) {
            buildMatrix(map, "A", signal, "C");
            buildMatrix(map, "B", signal, null);
            buildMatrix(map, "C", signal, "C");
        }

        return map;
    }

    private static Map<String, Map<String, Set<String>>> buildMatrix(Map<String, Map<String, Set<String>>> map,
                                                                     String state,
                                                                     String signal,
                                                                     String... transitions) {
        if (map.containsKey(state)) {
            Map<String, Set<String>> signalToTransitionsMap = map.get(state);
            if (signalToTransitionsMap.containsKey(signal)) {
                signalToTransitionsMap.get(signal).addAll(buildSet(transitions));
            } else {
                signalToTransitionsMap.put(signal, buildSet(transitions));
            }
        } else {
            map.put(state, buildTransitionsMap(signal, transitions));
        }

        return map;
    }

    private static Map<String, Set<String>> buildTransitionsMap(String signal, String... transitions) {
        return new HashMap<String, Set<String>>() {{
            if (signal != null) {
                put(signal, buildSet(transitions));
            }
        }};
    }

    private static Set<String> buildSet(String... values) {
        return new LinkedHashSet<String>() {{
            if (values != null) {
                for (String value : values) {
                    if (value != null) {
                        add(value);
                    }
                }
            }
        }};
    }
}
