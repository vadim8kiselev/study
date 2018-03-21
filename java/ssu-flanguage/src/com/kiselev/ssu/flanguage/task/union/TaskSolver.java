package com.kiselev.ssu.flanguage.task.union;

import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.exception.AutomataException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskSolver {

    public static void solve(List<Automata> automatas) {
        List<Pair<String, String>> lexemes = getLexemes(automatas, readInput());
        for (Pair<String, String> lexeme : lexemes) {
            System.out.println("[" + lexeme.getKey() + ", \"" + lexeme.getValue() + "\"]");
        }
        System.out.println();
    }

    private static String readInput() {
        try {
            return FileUtils.readFileToString(new File("input.txt"), "utf-8");
        } catch (IOException exception) {
            return "";
        }
    }

    private static List<Pair<String, String>> getLexemes(List<Automata> automatas, String line) {
        List<Pair<String, String>> lexemes = new ArrayList<>();

        for (int index = 0; index < line.length(); ) {
            try {
                refreshAutomatas(automatas);
                Pair<String, String> lexeme = getLexeme(automatas, line, index);
                lexemes.add(lexeme);
                index += lexeme.getValue().length();
            } catch (AutomataException exception) {
                index++;
            }
        }

        return lexemes;
    }

    private static void refreshAutomatas(List<Automata> automatas) {
        for (Automata automata : automatas) {
            automata.refreshAutomata();
        }
    }

    private static Pair<String, String> getLexeme(Collection<Automata> automatas, String line, int index) {
        Map<Automata, Pair<Boolean, Integer>> lexemes = new HashMap<>();
        for (Automata automata : automatas) {
            Pair<Boolean, Integer> token = f(automata, line, index);
            if (token.getKey()) {
                lexemes.put(automata, token);
            }
        }

        Automata automata = getRelevantAutomata(lexemes);
        Pair<Boolean, Integer> resultPair = lexemes.get(automata);

        String string = line.substring(index, index + resultPair.getValue());
        return new ImmutablePair<>(automata.getName(), filter(string));
    }

    private static String filter(String rawString) {
        //rawString = rawString.replace("\n", "\\n");
        return rawString;
    }

    private static Pair<Boolean, Integer> f(Automata automata, String alphabet, Integer position) {
        MutablePair<Boolean, Integer> pair = new MutablePair<>(false, 0);

        automata.initAutomata();
        Integer currentSession = 0;

        try {
            for (int index = position; index < alphabet.length(); index++) {
                automata.process(alphabet.charAt(index));

                ++currentSession;
                if (automata.isFinalState()) {
                    pair.setLeft(true);
                    pair.setRight(Integer.max(currentSession, pair.getRight()));
                }
            }
        } catch (AutomataException exception) {
            // sin
        }

        return pair;
    }

    private static Automata getRelevantAutomata(Map<Automata, Pair<Boolean, Integer>> automatas) {
        if (automatas.isEmpty()) {
            throw new AutomataException("Number of automatas cannot be equal to zero");
        }

        int maxCount = automatas.values().stream()
                .map(Pair::getValue)
                .max(Integer::compare)
                .orElse(0);

        Map<Automata, Pair<Boolean, Integer>> automatasByLength = automatas.entrySet().stream()
                .filter(entry -> entry.getValue().getValue() == maxCount)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int maxPriority = automatasByLength.keySet().stream()
                .map(Automata::getPriority)
                .max(Integer::compare)
                .orElse(0);

        List<Automata> automatasByPriority = automatasByLength.keySet().stream()
                .filter(automata -> automata.getPriority() == maxPriority)
                .collect(Collectors.toList());

        if (automatasByPriority.size() != 1) {
            throw new AutomataException("Wrong number of available automatas");
        }

        return automatasByPriority.iterator().next();
    }
}
