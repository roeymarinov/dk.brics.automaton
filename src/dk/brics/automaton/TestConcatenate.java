package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestConcatenate {

    // Basic Black Box Testing:

    @Test
    public void emptyList(){
        //arrange:
        List<Automaton> empty = new ArrayList<>();

        //act:
        Automaton a = BasicOperations.concatenate(empty);

        //assert:
        Assertions.assertTrue(a.isEmptyString());
    }

    @Test
    public void orderMatters(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list = Arrays.asList(first, second);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run("FirstSecond"), "concatenation of the words in the right order is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a.run("SecondFirst"), "concatenation of the words in the wrong order is" +
                " in the resulting automaton language");
    }

    @Test
    public void listWithNull(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list = Arrays.asList(first, null, second);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run("FirstSecond"), "concatenation of the words in the right order is " +
                "not in the resulting automaton language");
    }

    @Test
    public void concatenatesAll(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        Automaton third = BasicAutomata.makeString("Third");
        Automaton fourth = BasicAutomata.makeString("Fourth");
        List<Automaton> list = Arrays.asList(first, second, third, fourth);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run("FirstSecondThirdFourth"), "concatenation of all of the words is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a.run("FirstSecond"), "concatenation of only some of the words is" +
                " in the resulting automaton language");
    }

    @Test
    public void sameAutomaton(){
        //arrange:
        Automaton a1 = BasicAutomata.makeString("a");
        List<Automaton> list = Arrays.asList(a1, a1, a1, a1, a1);

        //act:
        Automaton a2 = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a2.run("aaaaa"), "concatenation of exactly the number of repetitions is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a2.run("aaaa"), "concatenation of less than the number of repetitions is" +
                " in the resulting automaton language");
        Assertions.assertFalse(a2.run("aaaaaa"), "concatenation of more than the number of repetitions is" +
                " in the resulting automaton language");
    }

    @Test
    public void oneAutomaton(){
        //arrange:
        Automaton a1 = BasicAutomata.makeString("a");
        List<Automaton> list = Collections.singletonList(a1);

        //act:
        Automaton a2 = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertEquals(SpecialOperations.getFiniteStrings(a1), SpecialOperations.getFiniteStrings(a2));
    }

    // Metamorphic Testing:
    @Test
    public void orderMatterss(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list = Arrays.asList(first, second);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run("FirstSecond"), "concatenation of the words in the right order is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a.run("SecondFirst"), "concatenation of the words in the wrong order is" +
                " in the resulting automaton language");
    }

    // White Box Testing:
}
