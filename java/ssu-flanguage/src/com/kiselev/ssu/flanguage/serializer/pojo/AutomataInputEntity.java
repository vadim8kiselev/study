package com.kiselev.ssu.flanguage.serializer.pojo;

import java.util.Map;
import java.util.Set;

public class AutomataInputEntity {

    private String name;

    private Integer priority;

    private Set<String> alphabet;

    private Set<String> initialState;

    private Set<String> finalState;

    private Map<String, Map<String, Set<String>>> matrixOfTransitions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public Set<String> getInitialState() {
        return initialState;
    }

    public void setInitialState(Set<String> initialState) {
        this.initialState = initialState;
    }

    public Set<String> getFinalState() {
        return finalState;
    }

    public void setFinalState(Set<String> finalState) {
        this.finalState = finalState;
    }

    public Map<String, Map<String, Set<String>>> getMatrixOfTransitions() {
        return matrixOfTransitions;
    }

    public void setMatrixOfTransitions(Map<String, Map<String, Set<String>>> matrixOfTransitions) {
        this.matrixOfTransitions = matrixOfTransitions;
    }
}