package dk.brics.automaton;

import org.junit.jupiter.api.*;


public class TestUtils {
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

    public static String repeatString(String str, int n){
        //empty string buffer
        StringBuilder str_bfr = new StringBuilder();
        for(int i=0; i<n ;i++)
        {
            //append string to string buffer n times
            str_bfr.append(str);
        }
        //converting string buffer back to string using toString() method
        str = str_bfr.toString();
        if (n==0){
            return "";
        }
        return str;
    }
}