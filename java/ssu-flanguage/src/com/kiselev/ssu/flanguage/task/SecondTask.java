package com.kiselev.ssu.flanguage.task;

import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.exception.AutomataException;
import com.kiselev.ssu.flanguage.serializer.AutomataReader;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondTask implements AbstractTask {

    @Override
    public void solve() throws IOException {
        Automata automata = AutomataReader.read("configuration/Real.json");

        //Pair<Boolean, Integer> result = f(automata, inputEntity.getAlphabet(), 0);
        //System.out.println("<" + result.getLeft() + ", " + result.getRight() + ">");

        findAllWords(automata, "5zxc-5.c+.5e+5.5e.7cda-.3ee-5.e-3502e.3e-3ex3.E3.3.")
                .forEach(System.out::println);
        System.out.println();
    }

    private Pair<Boolean, Integer> f(Automata automata, String alphabet, Integer position) {
        MutablePair<Boolean, Integer> pair = new MutablePair<>(false, 0);

        Integer currentSession = 0;
        automata.initAutomata();

        try {
            for (int index = position; index < alphabet.length(); index++) {
                automata.process(alphabet.charAt(index));

                pair.setLeft(automata.isFinalState() || pair.getLeft());

                ++currentSession;
                if (automata.isFinalState()) {
                    pair.setRight(Integer.max(currentSession, pair.getRight()));
                }
            }
        } catch (AutomataException exception) {
            // sin
        }

        return pair;
    }

    private List<String> findAllWords(Automata automata, String line) {
        List<String> words = new ArrayList<>();

        for (int index = 0; index < line.length(); ) {
            int currentIndex = f(automata, line, index).getValue();
            if (currentIndex == 0) {
                index++;
            } else {
                words.add(line.substring(index, index + currentIndex));
                index += currentIndex;
            }
        }

        return words;
    }
}