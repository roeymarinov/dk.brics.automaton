package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.ThreadLocalRandom;

import static dk.brics.automaton.TestUtils.repeatString;


public class TestRepeat {

    // Basic Black Box Testing:


    @ParameterizedTest
    @CsvSource({"2,3"})
    public void coversEntireRange(int min, int max) {
        //arrange:
        String str = "Argh";
        int randomBetween = ThreadLocalRandom.current().nextInt(min, max + 1);

        Automaton a = BasicAutomata.makeString(str);
        String longest = repeatString(str, max);
        String shortest = repeatString(str, min);
        String between = repeatString(str, randomBetween);

        //act:
        Automaton a_repeat = a.repeat(min, max);

        //assert:
        Assertions.assertTrue(a_repeat.run(longest), "longest string is not accepted");
        Assertions.assertTrue(a_repeat.run(shortest), "shortest string is not accepted");
        Assertions.assertTrue(a_repeat.run(between), "string between is not accepted");
    }

    @ParameterizedTest
    @CsvSource({"2,3"})
    public void notGoingOutOfRange(int min, int max) {
        //arrange:
        String str = "Argh";

        Automaton a = BasicAutomata.makeString(str);
        String tooLong = repeatString(str, max + 1);
        String tooShort = repeatString(str, min - 1);

        //act:
        Automaton a_repeat = a.repeat(min, max);

        //assert:
        Assertions.assertFalse(a_repeat.run(tooLong), "too long string is accepted");
        Assertions.assertFalse(a_repeat.run(tooShort), "too short string is accepted");
    }



    // Metamorphic Testing:


    // White Box Testing:


}
