package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestIntersection {

    // Basic Black Box Testing:


    @Test
    public void orderDoesNotMatter(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");

        //act:
        Automaton a1 = BasicOperations.intersection(first, second);
        Automaton a2 = BasicOperations.intersection(second, first);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }


    @Test
    public void testNoIntersection(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");

        //act:
        Automaton a = BasicOperations.intersection(first, second);

        //assert:
        Assertions.assertTrue(a.isEmpty());
    }

    @Test
    public void testIntersection(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("First");

        //act:
        Automaton a = BasicOperations.intersection(first, second);

        //assert:
        Assertions.assertTrue(a.subsetOf(first) && first.subsetOf(a));
    }

    @Test
    public void sameAutomaton() {
        //arrange:
        Automaton a1 = BasicAutomata.makeString("a");

        //act:
        Automaton a2 = BasicOperations.intersection(a1, a1);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }

    // Metamorphic Testing:

    // White Box Testing:
}
