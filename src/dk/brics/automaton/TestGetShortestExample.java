package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.ThreadLocalRandom;

import static dk.brics.automaton.TestUtils.repeatString;


public class TestGetShortestExample {

    // Basic Black Box Testing:


    @Test
    public void emptyStringAccepted() {
        //arrange:
        Automaton a1 = BasicAutomata.makeString("aaa");
        Automaton a2 = BasicAutomata.makeString("");
        Automaton a = BasicOperations.union(a1, a2);

        //act:
        String empty = a.getShortestExample(true);

        //assert:
        Assertions.assertEquals("", empty);
    }

    @Test
    public void emptyStringRejected() {
        //arrange:
        Automaton a1 = BasicAutomata.makeString("aaa");
        Automaton a2 = BasicAutomata.makeString("th");
        Automaton a = BasicOperations.union(a1, a2);

        //act:
        String empty = a.getShortestExample(false);

        //assert:
        Assertions.assertEquals("", empty);
    }

    @Test
    public void returnsLexicographicallyFirst() {
        //arrange:
        Automaton a1 = BasicAutomata.makeString("aaa");
        Automaton a2 = BasicAutomata.makeString("bbb");
        Automaton a = BasicOperations.union(a1, a2);

        //act:
        String shortestExample = a.getShortestExample(true);

        //assert:
        Assertions.assertEquals("aaa", shortestExample);
    }

    @Test
    public void noExample() {
        //arrange:
        Automaton a = BasicAutomata.makeEmpty();
        Automaton b = BasicAutomata.makeAnyString();

        //act:
        String shortestExample = a.getShortestExample(true);
        String shortestExample2 = b.getShortestExample(false);


        //assert:
        Assertions.assertNull(shortestExample, "found an accepted example in an empty automaton");
        Assertions.assertNull(shortestExample2, "found a rejected example in an any string automaton");

    }







    // Metamorphic Testing:


    // White Box Testing:


}
