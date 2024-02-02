package com.mycompany.app;

import static org.junit.Assert.assertTrue;

import java.util.InputMismatchException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class DecideTest {

    @Test
    public void yes() {
        Decide decide = new Decide("my-app/src/TestFiles/MainYESTest1.in");
        try {
            assertTrue(decide.decide().equals("YES"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void no() {
        Decide decide = new Decide("my-app/src/TestFiles/MainNOTest2.in");
        try {
            assertTrue(decide.decide().equals("NO"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void invalid() {
        Decide decide = new Decide("my-app/src/TestFiles/MainINVALIDTest3.in");
        exception.expect(NullPointerException.class);
        decide.decide();

        // try {
        //     decide.decide();
        //     exception.expect(InputMismatchException.class);
        // } catch (Exception e) {
        //     exception.expect(InputMismatchException.class);
        // }
    }
}
