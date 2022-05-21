package dk.brics.automaton;

import org.junit.jupiter.api.*;


public class TestBasicOperations {
    @Test
    public void sanityCheck(){
        //arrange:
        Automaton a = BasicAutomata.makeString("Roey");
        Automaton b = BasicAutomata.makeEmpty();

        //act:
        Automaton c = BasicOperations.intersection(a, b);

        //assert:
        Assertions.assertTrue(c.isEmpty());
    }


}