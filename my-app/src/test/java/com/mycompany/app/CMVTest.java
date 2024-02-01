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

     /**
     * LIC 0 is given two consecutive points with dist > (0 = length) apart,
     * and should evaluate to true.
     */
    @Test
    public void valid_input_LIC_0_two_points_greater_dist()
    {
        double length1 = 0;
        double[] x_pts = new double[]{0, 1};
        double[] y_pts = new double[]{0, 2};

        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setLenght1(length1);
        Boolean[] cmv = CMV.initCMV(param);

        assertTrue(cmv[0]);

    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void valid_Input_LIC_0_two_points_smaller_dist()
    {
        double length1 = 10;
        double[] x_pts = new double[]{0, 0};
        double[] y_pts = new double[]{0, 0};

        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setLenght1(length1);
        Boolean[] cmv = CMV.initCMV(param);

        assertFalse(cmv[0]);
    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void invalid_valid_input_LIC_0_condition()
    {

    }

    /**
     * LIC 1 is provided with 3 cons 
     */
    @Test
    public void valid_Input_LIC_1_points_in_circle()
    {
        double[] x_pts = new double[]{6, 2, 4};
        double[] y_pts = new double[]{6, 2, 4};
        double radius1 = 10;

        param = param.deepCopy();
        param.setNUMPOINTS(3);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setLenght1(radius1);
        Boolean[] cmv = CMV.initCMV(param);

        assertTrue(cmv[1]);
    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void valid_input_LIC_1_condition2()
    {

    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void invalid_valid_Input_LIC_1_condition()
    {

    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void valid_valid_input_LIC_2_condition1()
    {

    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void valid_valid_Input_LIC_2_condition2()
    {

    }

    /**
     * Test initializing CMV with all false LIC
     */
    @Test
    public void invalid_valid_Input_LIC_2_condition1()
    {

    }

}
