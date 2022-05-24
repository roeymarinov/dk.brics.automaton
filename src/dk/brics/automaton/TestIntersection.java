package dk.brics.automaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

public class TestIntersection {

    @ParameterizedTest
    @CsvSource({"x+y?,y?x+","(Roey)+,(Ariel)+", "Roey+,Ariel", "Roey,Ariel+"})
    public void orderDoesNotMatter(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();

        //act:
        Automaton a1 = BasicOperations.intersection(first, second);
        Automaton a2 = BasicOperations.intersection(second, first);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }


    @ParameterizedTest
    @CsvSource({"(Roey)+,(Ariel)+", "Roey+,Ariel", "Roey,Ariel+", "Roey,Ariel"})
    public void noIntersection(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();

        //act:
        Automaton a = BasicOperations.intersection(first, second);

        //assert:
        Assertions.assertTrue(a.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"x+y?,y?x+","Roey+,Roey", "Roey,Roey+"})
    public void someIntersection(String exp1, String exp2){
        //arrange:
        RegExp regExp1 = new RegExp(exp1);
        RegExp regExp2 = new RegExp(exp2);
        Automaton first = regExp1.toAutomaton();
        Automaton second = regExp2.toAutomaton();

        //act:
        Automaton a = BasicOperations.intersection(first, second);

        //assert:
        Assertions.assertTrue(a.subsetOf(first) && a.subsetOf(second), "intersection is not in both" +
                "languages");
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+"})
    public void sameLanguage(String exp) {
        //arrange:
        RegExp regExp1 = new RegExp(exp);
        Automaton first = regExp1.toAutomaton();
        RegExp regExp2 = new RegExp(exp);
        Automaton second = regExp2.toAutomaton();
        //act:
        Automaton a = BasicOperations.intersection(first, second);

        //assert:
        Assertions.assertTrue(a.subsetOf(first) && first.subsetOf(a));
    }

    @ParameterizedTest
    @CsvSource({"(Roey)+"})
    public void sameAutomaton(String exp) {
        //arrange:
        RegExp regExp = new RegExp(exp);
        Automaton a1 = regExp.toAutomaton();
        //act:
        Automaton a2 = BasicOperations.intersection(a1, a1);

        //assert:
        Assertions.assertTrue(a1.subsetOf(a2) && a2.subsetOf(a1));
    }

}
