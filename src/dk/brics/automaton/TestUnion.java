package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestUnion {

    @Test
    public void emptyList(){
        //arrange:
        List<Automaton> empty = new ArrayList<>();

        //act:
        Automaton a = BasicOperations.union(empty);

        //assert:
        Assertions.assertTrue(a.isEmpty());
    }

    @Test
    public void orderDoesNotMatter(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list1 = Arrays.asList(first, second);
        List<Automaton> list2 = Arrays.asList(second, first);

        //act:
        Automaton a1 = BasicOperations.union(list1);
        Automaton a2 = BasicOperations.union(list2);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }

    @Test
    public void listWithNull(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list = Arrays.asList(first, null, second);

        //act + assert:
        Assertions.assertThrows(NullPointerException.class, () -> BasicOperations.union(list));

    }

    @Test
    public void unitesAll(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        Automaton third = BasicAutomata.makeString("Third");
        Automaton fourth = BasicAutomata.makeString("Fourth");
        List<Automaton> list = Arrays.asList(first, second, third, fourth);

        //act:
        Automaton a = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(a.run("First"), "First language not in union");
        Assertions.assertTrue(a.run("Second"), "Second language not in union");
        Assertions.assertTrue(a.run("Third"), "Third language not in union");
        Assertions.assertTrue(a.run("Fourth"), "Fourth language not in union");
    }

    @Test
    public void sameAutomaton() {
        //arrange:
        Automaton a1 = BasicAutomata.makeString("a");
        List<Automaton> list = Arrays.asList(a1, a1, a1, a1, a1);

        //act:
        Automaton a2 = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }

    @Test
    public void oneAutomaton(){
        //arrange:
        Automaton a1 = BasicAutomata.makeString("a");
        List<Automaton> list = Collections.singletonList(a1);

        //act:
        Automaton a2 = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
        Assertions.assertEquals(SpecialOperations.getFiniteStrings(a1), SpecialOperations.getFiniteStrings(a2));
    }
}
