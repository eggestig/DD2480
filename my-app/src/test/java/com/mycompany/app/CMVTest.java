package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mycompany.app.Parameters;
import com.mycompany.app.CMV;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class CMVTest 
{
    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void testCMV()
    {
        int NUMPOINTS = 10;
        int[] x_pts = new int[NUMPOINTS];
        int[] y_pts = new int[NUMPOINTS];
        Parameters param = new Parameters
                            (
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            0, 
                            0,
                            0.0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0.0,
                            0.0,
                            0.0
                            );

        for(int i = 0; i < NUMPOINTS; i++) {
            x_pts[i] = i;
            y_pts[i] = i;
        }
        Boolean[] cmv;
        cmv = CMV.initCMV(param, NUMPOINTS, x_pts, y_pts);
        for(int licNum = 0; licNum < cmv.length; licNum++) {
            assertFalse("LIC" + licNum, cmv[licNum]);
        }
    }
}
