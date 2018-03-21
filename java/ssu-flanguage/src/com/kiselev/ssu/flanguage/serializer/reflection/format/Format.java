package com.kiselev.ssu.flanguage.serializer.reflection.format;

import com.kiselev.ssu.flanguage.Automata;

import static java.io.File.*;
import static com.kiselev.ssu.flanguage.serializer.AutomataReader.*;

/* Use for statistics */
public class Format {
    public static Automata format(Automata automata, String lexeme) {
        switch (lexeme.charAt(0)) {
            case 'K': return load(Symbol.f + separator + lexeme.substring(0, 1) + Symbol.ext);
            case 'R': return load(Symbol.f + separator + lexeme.substring(0, 1) + Symbol.ext);
        }
        return automata;
    }
}
