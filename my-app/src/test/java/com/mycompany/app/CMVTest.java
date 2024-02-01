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

    @Test
    public void valid_input_LIC_3_false_area_less_than_param() {
        Parameters params = new Parameters();
        double[] x_pts = new double[] {0,1,1,2};
        double[] y_pts = new double[] {0,1,0,0};
        params.setX_PTS(x_pts);
        params.setY_PTS(y_pts);
        params.setNUMPOINTS(4);
        params.setAREA1(2);
        assertFalse("LIC3", CMV.LIC3(params));
    }

    @Test
    public void valid_input_LIC_3_true_area_more_than_param() {
        Parameters params = new Parameters();
        double[] x_pts = new double[] {0,1,3,5,7,9};
        double[] y_pts = new double[] {0,1,0,3,0,3};
        params.setX_PTS(x_pts);
        params.setY_PTS(y_pts);
        params.setNUMPOINTS(6);
        params.setAREA1(2);
        assertTrue("LIC3", CMV.LIC3(params));
    }

    @Test
    public void invalid_input_LIC_3_throws_error_too_few_points() {
        Parameters params = new Parameters();
        double[] x_pts = new double[] {0,1};
        double[] y_pts = new double[] {0,1};
        params.setX_PTS(x_pts);
        params.setY_PTS(y_pts);
        params.setNUMPOINTS(2);
        params.setAREA1(2);
        assertFalse("LIC3", CMV.LIC3(params));
    }

    @Test
    public void valid_input_LIC_4_true_points_in_different_quadrants() {
        Parameters params = new Parameters();
        params.setX_PTS(new double[] {1,-1,-1,1});
        params.setY_PTS(new double[] {1,1,-1,-1});
        params.setNUMPOINTS(4);
        params.setQUADS(2);
        params.setQ_PTS(3);
        assertTrue("LIC4", CMV.LIC4(params));
    }

    @Test
    public void valid_input_LIC_4_false_all_points_in_same_quadrant() {
        Parameters params = new Parameters();
        params.setX_PTS(new double[] {0,1,3,0});
        params.setY_PTS(new double[] {0,9,5,1});
        params.setNUMPOINTS(4);
        params.setQUADS(2);
        params.setQ_PTS(2);
        assertFalse("LIC4", CMV.LIC4(params));
        
    }

    @Test
    public void invalid_input_LIC_4_throws_error_too_few_points() {
        Parameters params = new Parameters();
        params.setX_PTS(new double[] {});
        params.setY_PTS(new double[] {});
        params.setQ_PTS(1);
        params.setNUMPOINTS(0);
        assertFalse("LIC4", CMV.LIC4(params));
    }

    @Test
    public void invalid_input_LIC_4_throws_error_Q_is_nonpositive() {
        Parameters params = new Parameters();
        params.setQUADS(-1);
        assertFalse("LIC4", CMV.LIC4(params));
    }

    @Test
    public void valid_input_true_LIC_5() {
        Parameters params = new Parameters();
        params.setX_PTS(new double[] {5,4,3,2,1});
        params.setY_PTS(new double[] {1,2,3,4,5});
        params.setNUMPOINTS(5);
        System.out.println("True case LIC5");
        assertTrue("LIC5", CMV.LIC5(params));
    }

    @Test
    public void valid_input_false_LIC_5() {
        Parameters params = new Parameters();
        params.setX_PTS(new double[] {1,2,3,4,5});
        params.setY_PTS(new double[] {1,2,3,4,5});
        params.setNUMPOINTS(5);
        assertFalse("LIC5", CMV.LIC5(params));
    }

    @Test
    public void invalid_input_LIC_5_too_few_points() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(1);
        params.setX_PTS(new double[] {1});
        params.setY_PTS(new double[] {1});
        assertFalse("LIC5", CMV.LIC5(params));
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

        assertTrue("LIC9", CMV.LIC9(params));
    }
}
