package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+", "Roey,Ariel"})
    public void orderDoesNotMatter(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();
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

    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+,(Amit)+","not...empty,'',notempty"})
    public void unitesAll(String exp1, String exp2, String exp3){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        RegExp regExp3 = new RegExp(exp3);

        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();
        Automaton third = regExp3.toAutomaton();

        List<Automaton> list = Arrays.asList(first, second, third);
        //act:
        Automaton a = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(first.subsetOf(a), "First language not in union");
        Assertions.assertTrue(second.subsetOf(a), "Second language not in union");
        Assertions.assertTrue(third.subsetOf(a), "Third language not in union");
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+,5", "(Roey)+,1"})
    public void sameAutomaton(String exp, int num_repetitions) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a1 = regExp.toAutomaton();

        List<Automaton> list = new ArrayList<>();
        for (int i=0; i < num_repetitions; i++){
            list.add(a1);
        }

        //act:
        Automaton a2 = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+"})
    public void emptyLanguage(String exp){
        //arrange:
        RegExp regExp1 = new RegExp(exp);
        Automaton a1 = regExp1.toAutomaton();
        Automaton empty = BasicAutomata.makeEmpty();
        List<Automaton> list = Arrays.asList(a1, empty);

        //act:
        Automaton a2 = BasicOperations.union(list);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }
}
