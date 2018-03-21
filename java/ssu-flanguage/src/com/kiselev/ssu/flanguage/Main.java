package com.kiselev.ssu.flanguage;

import com.google.common.collect.Maps;
import com.kiselev.ssu.flanguage.task.holder.TaskHolder;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static com.kiselev.ssu.flanguage.serializer.AutomataReader.read;

public class Main {

    public enum Lexeme {
        OPEN_BRACKET,
        CLOSE_BRACKET,
        ID,
        KEY_WORD,
        INTEGER,
        REAL,
        SPACE,
        TASK
    }

    public static void main(String[] args) throws IOException {

        //TaskHolder.FIRST.solve();

        //TaskHolder.SECOND.solve();

        //TaskHolder.THIRD.solve();

        TaskHolder.FOURTH.solve();

        //testFourthManually("configuration/output/Task.json");

        boolean needToRunTests = false;
        if (needToRunTests) {
            testFourth(Lexeme.OPEN_BRACKET);
            testFourth(Lexeme.CLOSE_BRACKET);
            testFourth(Lexeme.ID);
            testFourth(Lexeme.KEY_WORD);
            testFourth(Lexeme.INTEGER);
            testFourth(Lexeme.REAL);
            testFourth(Lexeme.SPACE);
            testFourth(Lexeme.TASK);
        }
    }

    private static void testFourthManually(String fileName) throws IOException {
        Automata automata = read(fileName);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            System.out.println("Is final state for " + input + ": " + check(automata, input));
        }
    }

    private static void testFourth(Lexeme lexeme) throws IOException {
        String fileName = "configuration/output/{0}.json";
        switch (lexeme) {
            case OPEN_BRACKET:
                runTests(read(MessageFormat.format(fileName, "OpenBracket")), Lexeme.OPEN_BRACKET);
                break;
            case CLOSE_BRACKET:
                runTests(read(MessageFormat.format(fileName, "CloseBracket")), Lexeme.CLOSE_BRACKET);
                break;
            case ID:
                runTests(read(MessageFormat.format(fileName, "ID")), Lexeme.ID);
                break;
            case KEY_WORD:
                runTests(read(MessageFormat.format(fileName, "KeyWord")), Lexeme.KEY_WORD);
                break;
            case INTEGER:
                runTests(read(MessageFormat.format(fileName, "Integer")), Lexeme.INTEGER);
                break;
            case REAL:
                runTests(read(MessageFormat.format(fileName, "Real")), Lexeme.REAL);
                break;
            case SPACE:
                runTests(read(MessageFormat.format(fileName, "Space")), Lexeme.SPACE);
                break;
            case TASK:
                runTests(read(MessageFormat.format(fileName, "Task")), Lexeme.TASK);
                break;
        }
    }

    private static Map<Lexeme, Map<String, Boolean>> tests = Maps.newHashMap();

    static {
        tests.put(Lexeme.OPEN_BRACKET, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put("(", true);
            put(")", false);
            put("a", false);
            put("2", false);
            put("()", false);
            put("(123)", false);
        }});
        tests.put(Lexeme.CLOSE_BRACKET, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put(")", true);
            put("(", false);
            put("a", false);
            put("2", false);
            put("()", false);
            put("(123)", false);
        }});
        tests.put(Lexeme.ID, new LinkedHashMap<String, Boolean>() {{
            put("var", true);
            put("123", true);
            put("123++456", true);
            put("123++456", true);
            put("123%", true);
            put("123%", true);
            put("!!%", true);
            put("_field", true);
            put("automata", true);
            put("abc+-*/%!_8", true);
        }});
        tests.put(Lexeme.KEY_WORD, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put("if", true);
            put("case", false);
            put("break", false);
            put("define", true);
            put("for", false);
            put("lambda", true);
            put("while", false);
            put("cond", true);
        }});
        tests.put(Lexeme.INTEGER, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put("1", true);
            put("+1", true);
            put("-1", true);
            put("12", true);
            put("123", true);
            put("1234", true);
        }});
        tests.put(Lexeme.REAL, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put("1", true);
            put("1.", true);
            put("+1123..e", false);
            put("1.5", true);
            put(".5", true);
            put("+1123..", false);
            put("1123.5412", true);
            put("+1123.5412e124", true);
            put("+1123ee", false);
            put("1123.5412e+124", true);
            put("+1123.e", false);
            put("+1123.5412e-124", true);
            put("1123.5412e-124", true);

        }});
        tests.put(Lexeme.SPACE, new LinkedHashMap<String, Boolean>() {{
            put("", false);
            put(" ", true);
            put("  ", true);
            put("   ", true);
        }});
        tests.put(Lexeme.TASK, new LinkedHashMap<String, Boolean>() {{
            put("", true);
            put("00", true);
            put("11111", false);
            put("11001", false);
            put("00110011", true);
            put("0010010010", false);
            put("0101110000110110", true);
        }});
    }

    private static void runTests(Automata automata, Lexeme lexeme) {
        Map<String, Boolean> lexemeTests = tests.get(lexeme);

        System.out.println(lexeme.name());
        for (Map.Entry<String, Boolean> entry : lexemeTests.entrySet()) {
            String indent = entry.getValue() ? " " : "";
            System.out.println((check(automata, entry.getKey()) == entry.getValue() ? "OK  : " : "FAIL: ") + entry.getValue() + indent + " - " + "\"" + entry.getKey() + "\"");
        }
        System.out.println();
    }

    private static boolean check(Automata automata, String alphabet) {
        try {
            automata.initAutomata();
            for (int index = 0; index < alphabet.length(); index++) {
                automata.process(alphabet.charAt(index));
            }
            //System.out.println("Is final state for " + alphabet + ": " + automata.isFinalState());
            return automata.isFinalState();
        } catch (Exception exception) {
            //System.out.println("Is final state for " + alphabet + ": " + false);
            return false;
        }
    }
}
