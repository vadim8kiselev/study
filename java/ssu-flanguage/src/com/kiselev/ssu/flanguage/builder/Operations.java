package com.kiselev.ssu.flanguage.builder;

import com.kiselev.ssu.flanguage.Automata;
import com.kiselev.ssu.flanguage.builder.components.base.TextOperation;
import com.kiselev.ssu.flanguage.builder.components.groups.ConcatenationOperation;
import com.kiselev.ssu.flanguage.builder.components.groups.UnionOperation;
import com.kiselev.ssu.flanguage.builder.components.modifiers.PlusOperation;
import com.kiselev.ssu.flanguage.builder.components.modifiers.QuestionOperation;
import com.kiselev.ssu.flanguage.builder.components.modifiers.StarOperation;
import com.kiselev.ssu.flanguage.exception.UnknownOperationException;

import java.util.List;

public class Operations {

    public static AutomataOperation text(Character character) {
        return new TextOperation(String.valueOf(character));
    }

    public static AutomataOperation text(String... text) {
        return new TextOperation(text);
    }

    private static AutomataOperation star(AutomataOperation operation) {
        return new StarOperation(operation);
    }

    private static AutomataOperation plus(AutomataOperation operation) {
        return new PlusOperation(operation);
    }

    private static AutomataOperation question(AutomataOperation operation) {
        return new QuestionOperation(operation);
    }

    public static AutomataOperation modify(AutomataOperation operation, char modifier) {
        switch (modifier) {
            case '+': return plus(operation);
            case '*': return star(operation);
            case '?': return question(operation);
        }
        throw new UnknownOperationException("Unknown modifier: " + modifier);
    }

    public static AutomataOperation union(List<AutomataOperation> operations) {
        return new UnionOperation(operations);
    }

    public static AutomataOperation concat(List<AutomataOperation> operations) {
        return new ConcatenationOperation(operations);
    }

    public static Automata execute(AutomataOperation operation) {
        return operation.compose();
    }
}
