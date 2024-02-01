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
        Parameters param = new Parameters();
        double[] x_pts = new double[] {0,1,1,2};
        double[] y_pts = new double[] {0,1,0,0};
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);

    }

    @Test
    public void valid_input_LIC_3_true_area_more_than_param() {
        Parameters param = new Parameters();
        double[] x_pts = new double[] {0,1,3,5,7,9};
        double[] y_pts = new double[] {0,1,0,3,0,3};
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
    }

    @Test
    public void invalid_input_LIC_3_throws_error_too_few_points() {
        Parameters param = new Parameters();
        double[] x_pts = new double[] {0,1};
        double[] y_pts = new double[] {0,1};
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        
    }



}
