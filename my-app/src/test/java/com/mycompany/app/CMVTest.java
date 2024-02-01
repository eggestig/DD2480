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
     * LIC 0 is provided with only one point and
     * should return false as the distance between
     * "2" points is undefinable.
     */
    @Test
    public void invalid_input_LIC_0_one_point()
    {
        Parameters param = new Parameters();
        param.setNUMPOINTS(1);
        param.setX_PTS(new double[]{1});
        param.setY_PTS(new double[]{1});

        CMV cmv = new CMV();
        assertFalse(cmv.LIC0(param));
    }

    /**
     * LIC 0 is given two points with dist > (length = 10),
     * and should return false.
     */
    @Test
    public void valid_Input_LIC_0_two_points_greater_dist()
    {
        Parameters param = new Parameters();
        param.setNUMPOINTS(2);
        param.setX_PTS(new double[]{1, 20});
        param.setY_PTS(new double[]{1, 20});
        param.setLENGTH1(10);

        CMV cmv = new CMV();
        assertTrue(cmv.LIC0(param));
    }

    /**
     * LIC 1 is provided with 3 points not all within a radius of
     * radius1 = 1 and should return True. 
     */
    @Test
    public void valid_Input_LIC_1_points_in_circle()
    {
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(new double[]{2, 4, 6});
        param.setY_PTS(new double[]{2, 6, 8});
        param.setRADIUS1(1);

        CMV cmv = new CMV();
        assertTrue(cmv.LIC1(param));
    }
    
    /**
     * The Radius cannot be a negative number, and
     * should return false.
     */
    @Test
    public void valid_Input_LIC_1_negative_radius()
    {
        Parameters param = new Parameters();
        param.setRADIUS1(-1);

        CMV cmv = new CMV();
        assertFalse(cmv.LIC1(param));
    }


    /**
     * This test should return false as only one point
     * is provided and an angle cant be defined.
     */
    @Test
    public void invalid_input_LIC_2_one_point()
    {
        
        Parameters params = new Parameters();
        params.setNUMPOINTS(2);
        params.setX_PTS(new double[]{1,2});
        params.setY_PTS(new double[]{1,2});
        params.setEPSILON(1);

        CMV cmv = new CMV();
        assertFalse(cmv.LIC2(params));

    }
      /**
     * This test should return false as epsilon
     * is too big
     */
    @Test
    public void invalid_input_LIC_2_tooBigEpsilon()
    {
        Parameters params = new Parameters();
        params.setEPSILON(4);

        CMV cmv = new CMV();
        assertFalse(cmv.LIC2(params));
    }

      /**
     * This test should return false as only one point
     * is provided and an angle cant be defined.
     */
    @Test
    public void invalid_input_LIC_2_negativeEpsilon()
    {
        Parameters params = new Parameters();
        params.setEPSILON(-1);

        CMV cmv = new CMV();
        assertFalse(cmv.LIC2(params));
    }

    /**
     * LIC 2 is provided with 3 data points forming
     * a 45 degree angel and should return true for
     * epsilon = pi/2.
     */
    @Test
    public void valid_input_LIC_2_45dAngel()
    {   
        Parameters params = new Parameters();
        params.setNUMPOINTS(3);
        params.setX_PTS(new double[]{0, 0, -1});
        params.setY_PTS(new double[]{0, 1, 0});
        params.setEPSILON(Math.PI / 2);

        CMV cmv = new CMV();
        assertTrue(cmv.LIC2(params));
    }

    //Test LIC 9 throws InvalidParameterException for invalid N_PTS
    //Invalid N_PTS if N_PTS < 3
    @Test
    public void valid_input_LIC_9_() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setC_PTS(1);
        params.setD_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);

        System.out.println("");
        System.out.println("[TEST]");
        System.out.println("");
        System.out.flush(); 

        CMV cmv = new CMV();
    
        assertTrue("LIC9", cmv.LIC9(params));
    }
}
