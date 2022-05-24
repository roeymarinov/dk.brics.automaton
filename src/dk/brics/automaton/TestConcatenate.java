package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static dk.brics.automaton.TestUtils.repeatString;

public class TestConcatenate {

    @Test
    public void emptyList(){
        //arrange:
        List<Automaton> empty = new ArrayList<>();

        //act:
        Automaton a = BasicOperations.concatenate(empty);

        //assert:
        Assertions.assertTrue(a.isEmptyString());
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+", "Roey,Ariel"})
    public void orderMatters(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();
        List<Automaton> list = Arrays.asList(first, second);
        String firstExample = first.getShortestExample(true);
        String secondExample = second.getShortestExample(true);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run(firstExample + secondExample), "concatenation of the words in the right order is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a.run(secondExample + firstExample), "concatenation of the words in the wrong order is" +
                " in the resulting automaton language");
    }

    @Test
    public void listWithNull(){
        //arrange:
        Automaton first = BasicAutomata.makeString("First");
        Automaton second = BasicAutomata.makeString("Second");
        List<Automaton> list = Arrays.asList(first, null, second);

        //act + assert:
        Assertions.assertThrows(NullPointerException.class, () -> BasicOperations.concatenate(list));
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+,(Amit)+","not...empty,'',notempty"})
    public void concatenatesAll(String exp1, String exp2, String exp3){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        RegExp regExp3 = new RegExp(exp3);

        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();
        Automaton third = regExp3.toAutomaton();

        List<Automaton> list = Arrays.asList(first, second, third);

        String firstExample = first.getShortestExample(true);
        String secondExample = second.getShortestExample(true);
        String thirdExample = third.getShortestExample(true);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.run(firstExample + secondExample + thirdExample), "concatenation of all of the words is " +
                "not in the resulting automaton language");
        Assertions.assertFalse(a.run(firstExample + secondExample), "concatenation of only some of the words is" +
                " in the resulting automaton language");
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+,5","(Roey)+,1"})
    public void sameAutomaton(String exp, int num_repetitions){
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a = regExp.toAutomaton();
        List<Automaton> list = new ArrayList<>();
        for (int i=0; i < num_repetitions; i++){
            list.add(a);
        }

        //act:
        Automaton a_concat = BasicOperations.concatenate(list);
        Automaton a_repeat = BasicOperations.repeat(a, num_repetitions, num_repetitions);

        //assert:
        Assertions.assertTrue(a_concat.subsetOf(a_repeat) && a_repeat.subsetOf(a_concat));
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+"})
    public void emptyLanguage(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton a1 = regExp1.toAutomaton();
        Automaton a2 = regExp2.toAutomaton();
        Automaton empty = BasicAutomata.makeEmpty();
        List<Automaton> list = Arrays.asList(a1, a2, empty);

        //act:
        Automaton a = BasicOperations.concatenate(list);

        //assert:
        Assertions.assertTrue(a.isEmpty());
    }
}
