package com.kiselev.ssu.flanguage.task;

import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.serializer.AutomataReader;

import java.io.IOException;

public class FirstTask implements AbstractTask {

    @Override
    public void solve() throws IOException {
        Automata automata = AutomataReader.read("configuration/Real.json");

        String alphabet = "5";

        String template = "Automata has finished with %sfinal state for " + alphabet + "\n";
        Boolean finished = performWholeString(automata, alphabet);
        System.out.println(String.format(template, finished ? "" : "no "));
    }

    private boolean performWholeString(Automata automata, String alphabet) {
        try {
            automata.initAutomata();
            for (int index = 0; index < alphabet.length(); index++) {
                automata.process(alphabet.charAt(index));
            }
            return automata.isFinalState();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return false;
        }
    }
}
