package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mycompany.app.Parameters;
import com.mycompany.app.CMV;
import java.util.Random;
import java.util.Arrays; 

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
        Parameters param;
        int numpoints;
        double[] x_pts;
        double[] y_pts;


        Random rand = new Random();

        numpoints = rand.nextInt(100 - 2) + 2;
        x_pts = new double[numpoints];
        y_pts = new double[numpoints];

        Arrays.fill(x_pts, 0);
        Arrays.fill(y_pts, 1);

        param = new Parameters(
            numpoints,
            x_pts,
            y_pts,
            1.0,
            2.0,
            3.0,
            4.0,
            5,
            6,
            7.0,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17.0,
            18.0,
            19.0
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
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

        Parameters param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setLENGTH1(length1);
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
        
        
        Parameters param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setLENGTH1(length1);
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

        Parameters param = param.deepCopy();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setLENGTH1(radius1);
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
