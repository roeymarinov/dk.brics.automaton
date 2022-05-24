package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class TestGetShortestExample {


    @ParameterizedTest
    @CsvSource({"R....y|()", "()"})
    public void emptyStringAccepted(String exp) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();

        //act:
        String empty = BasicOperations.getShortestExample(a, true);
        String notEmpty = BasicOperations.getShortestExample(a, false);

        //assert:
        Assertions.assertEquals("", empty, "empty string should be the shortest example " +
                "accepted");
        Assertions.assertNotEquals("", notEmpty, "empty string shouldn't be the shortest" +
                " example rejected");
    }

    @ParameterizedTest
    @CsvSource({"R....y", "Ariel"})
    public void emptyStringRejected(String exp) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();

        //act:
        String empty = BasicOperations.getShortestExample(a, false);
        String notEmpty = BasicOperations.getShortestExample(a, true);

        //assert:
        Assertions.assertEquals("", empty, "empty string should be the shortest example " +
                "rejected");
        Assertions.assertNotEquals("", notEmpty, "empty string shouldn't be the shortest" +
                " example accepted");
    }


    @Test
    public void returnsLexicographicallyFirst() {
        //arrange:
        Automaton a = BasicAutomata.makeCharRange('a', 'z');

        //act:
        String shortestExample = BasicOperations.getShortestExample(a, true);

        //assert:
        Assertions.assertEquals("a", shortestExample);
    }

    @Test
    public void noExample() {
        //arrange:
        Automaton a = BasicAutomata.makeEmpty();
        Automaton b = BasicAutomata.makeAnyString();

        //act:
        String shortestExample = BasicOperations.getShortestExample(a, true);
        String shortestExample2 = BasicOperations.getShortestExample(b, false);

        //assert:
        Assertions.assertNull(shortestExample, "found an accepted example in an empty automaton");
        Assertions.assertNull(shortestExample2, "found a rejected example in an any string automaton");
    }
}
