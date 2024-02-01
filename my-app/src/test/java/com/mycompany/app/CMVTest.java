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

        //Test LIC 6 throws invalidN_PTSError for invalid N_PTS
    //Invalid N_PTS if N_PTS < 3
    @Test
    public void valid_input() {
        //return ture because dist between x,y[5] and x,y[9] < 17
        double[] x_pts = new double[]{
            37.84,
            12.56,
            65.23,
            88.11,
            42.79,
            9.33,
            51.45,
            76.02,
            23.67,
            94.88
        };
        double[] y_pts = new double[]{
            5.72,
            68.94,
            33.18,
            79.57,
            17.41,
            54.26,
            91.09,
            36.75,
            62.88,
            8.05};
        
        int k_pts = 3;
        double length2 = 17;

        Parameters param = new Parameters(
            10,
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
            k_pts,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            length2,
            18.0,
            19.0
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
        for(int licNum = 0; licNum < cmv.length; licNum++)
        //System.out.println(CMV.LIC12());
        
        assertTrue(CMV.LIC12());
        

        
        //cmv = CMV.initCMV(param);

        // assert test throws custom error
    }
   
}
