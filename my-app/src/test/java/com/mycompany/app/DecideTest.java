package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import java.util.InputMismatchException;
import org.junit.Test;



public class DecideTest {

    @Test
    public void yes_instance() {     
        Decide decide = new Decide("src/TestFiles/MainYESTest1.in");
        assertTrue(decide.decide().equals("YES"));
    }

    @Test
    public void no_instance(){
        Decide decide = new Decide("src/TestFiles/MainNOTest2.in");
        assertTrue(decide.decide().equals("NO"));
    }

    @Test(expected = InputMismatchException.class)
    public void input_mismatch() throws Exception{
        Decide decide = new Decide("src/TestFiles/MainINVALIDTest3.in");
        decide.decide();
    }

    @Test(expected = NullPointerException.class)
    public void invalid_filepath() throws Exception{
        Decide decide = new Decide("my-app/src/TestFiles/MainINVALIDTest3.in");
        decide.decide();
    }
}
