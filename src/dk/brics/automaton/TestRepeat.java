package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.ThreadLocalRandom;

import static dk.brics.automaton.TestUtils.repeatString;


public class TestRepeat {


    @ParameterizedTest
    @CsvSource({"R....y,2,6"})
    public void coversEntireRange(String exp, int min, int max) {
        //arrange:
        int randomBetween = ThreadLocalRandom.current().nextInt(min, max + 1);
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();
        String example = a.getShortestExample(true);
        String longest = repeatString(example, max);
        String shortest = repeatString(example, min);
        String between = repeatString(example, randomBetween);

        //act:
        Automaton a_repeat = BasicOperations.repeat(a, min, max);

        //assert:
        Assertions.assertTrue(a_repeat.run(longest), "longest string is not accepted");
        Assertions.assertTrue(a_repeat.run(shortest), "shortest string is not accepted");
        Assertions.assertTrue(a_repeat.run(between), "string between is not accepted");
    }

    @ParameterizedTest
    @CsvSource({"R....y,2,6"})
    public void notGoingOutOfRange(String exp, int min, int max) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();
        String example = a.getShortestExample(true);

        String tooLong = repeatString(example, max + 1);
        String tooShort = repeatString(example, min - 1);

        //act:
        Automaton a_repeat = BasicOperations.repeat(a, min, max);

        //assert:
        Assertions.assertFalse(a_repeat.run(tooLong), "too long string is accepted");
        Assertions.assertFalse(a_repeat.run(tooShort), "too short string is accepted");
    }

    @ParameterizedTest
    @CsvSource({"R....y,6,2"})
    public void minGreaterThanMax(String exp, int min, int max) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();

        //act:
        Automaton a_repeat = BasicOperations.repeat(a, min, max);

        //assert:
        Assertions.assertTrue(a_repeat.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"R....y,2"})
    public void minEqualsZero(String exp, int max) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();

        //act:
        Automaton a_repeat = BasicOperations.repeat(a, 0, max);

        //assert:
        Assertions.assertTrue(a_repeat.run(""));
    }

    @ParameterizedTest
    @CsvSource({"R....y","''"})
    public void emptyString(String exp) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();

        //act:
        Automaton a_repeat = BasicOperations.repeat(a,0, 0);

        //assert:
        Assertions.assertTrue(a_repeat.isEmptyString());
    }

}
