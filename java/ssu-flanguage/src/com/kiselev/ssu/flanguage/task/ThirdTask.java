package com.kiselev.ssu.flanguage.task;

import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.serializer.AutomataReader;
import com.kiselev.ssu.flanguage.task.union.TaskSolver;

import java.io.IOException;
import java.util.*;

public class ThirdTask implements AbstractTask {

    @Override
    public void solve() throws IOException {
        List<Automata> automatas = new ArrayList<>();

        automatas.add(AutomataReader.read("configuration/CloseBracket.json"));
        automatas.add(AutomataReader.read("configuration/OpenBracket.json"));
        automatas.add(AutomataReader.read("configuration/KeyWord.json"));

        automatas.add(AutomataReader.read("configuration/Integer.json"));
        automatas.add(AutomataReader.read("configuration/Real.json"));

        automatas.add(AutomataReader.read("configuration/Space.json"));
        automatas.add(AutomataReader.read("configuration/ID.json"));

        TaskSolver.solve(automatas);
    }
}