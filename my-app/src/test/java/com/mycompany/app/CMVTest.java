package com.mycompany.app;

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

    @Test
    public void valid_input_LIC_3_false_area_less_than_param() {
        int[] x_pts = new int[] {0,1,1,2};
        int[] y_pts = new int[] {0,1,0,0};
    }

    @Test
    public void valid_input_LIC_3_true_area_more_than_param() {
        int[] x_pts = new int[] {0,1,3,5,7,9};
        int[] y_pts = new int[] {0,1,0,3,0,3};


    }

    @Test
    public void invalid_input_LIC_3_throws_error_too_few_points() {
        int[] x_pts = new int[] {0,1};
        int[] y_pts = new int[] {0,1};
        
    }



}
