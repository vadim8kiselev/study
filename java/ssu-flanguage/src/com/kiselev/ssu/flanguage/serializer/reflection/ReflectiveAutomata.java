package com.kiselev.ssu.flanguage.serializer.reflection;

import com.kiselev.ssu.flanguage.Automata;

import static com.kiselev.ssu.flanguage.serializer.reflection.format.Format.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class ReflectiveAutomata {

    private Automata automata;

    private ReflectiveAutomata(Automata automata) {
        this.automata = automata;
        // private constructor
    }

    public Automata getAutomata() {
        return automata;
    }

    public static ReflectiveAutomata of(Automata automata) {
        return new ReflectiveAutomata(automata);
    }

    public ReflectiveAutomata setName(String name) {
        Field field = getFieldByName(Automata.class, "name");
        automata = format(automata, name);
        setToField(field, name);
        return this;
    }

    public ReflectiveAutomata setPriority(Integer priority) {
        Field field = getFieldByName(Automata.class, "priority");
        setToField(field, priority);
        return this;
    }

    public ReflectiveAutomata setInitialState(Set<String> initialState) {
        Field field = getFieldByName(Automata.class, "initialState");
        setToField(field, initialState);
        return this;
    }

    public ReflectiveAutomata setFinalState(Set<String> finalState) {
        Field field = getFieldByName(Automata.class, "finalState");
        setToField(field, finalState);
        return this;
    }

    public ReflectiveAutomata setMatrixOfTransitions(Map<String, Map<String, Set<String>>> matrixOfTransitions) {
        Field field = getFieldByName(Automata.class, "matrixOfTransitions");
        setToField(field, matrixOfTransitions);
        return this;
    }

    private Field getFieldByName(Class<? extends Automata> clazz, String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    private void setToField(Field field, Object value) {
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(automata, value);
            } catch (ReflectiveOperationException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
