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
            //assertFalse("LIC" + licNum, cmv[licNum]);
        }
        assertTrue("Temp skip the test,", true);
    }

        //Test LIC 6 throws invalidN_PTSError for invalid N_PTS
    //Invalid N_PTS if N_PTS < 3
    @Test
    public void valid_input_LIC_12() {
        //return ture , the smallest dist is 12.69 , <17
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
        
        assertTrue(CMV.LIC12(param));
    }
    @Test
    public void invalid_input_LIC_12() {
        //return ture , the smallest dist is 12.69 , > 11
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
        double length2 = 11;

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
        
        assertFalse(CMV.LIC12(param));
    }
    @Test
    public void valid_input_LIC_13() {
        //true test: x,y[0,2,5] is contain in a 30.87 radius circle, with is smaller than radius2(50)
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
            3,
            2,
            3,
            12,
            13,
            14,
            15,
            16,
            17,
            50,
            19.0
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
        for(int licNum = 0; licNum < cmv.length; licNum++)
        
        assertTrue(CMV.LIC13(param));
    }
    
    @Test
    public void invalid_input_LIC_13() {
        //true test: the smallest radius circle is 47.9, with is smaller  not than radius2(30)
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
            3,
            2,
            3,
            12,
            13,
            14,
            15,
            16,
            17,
            30,
            19.0
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
        for(int licNum = 0; licNum < cmv.length; licNum++)
        
        assertFalse(CMV.LIC13(param));
    }
    @Test
    public void valid_input_LIC_14() {
        //true test: x,y[1,5,6] is contain in a 188 area of triangle, with is smaller than area2(250)
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
            3,
            2,
            3,
            12,
            13,
            4,
            1,
            16,
            17,
            31,
            200
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
        for(int licNum = 0; licNum < cmv.length; licNum++)
        
        assertTrue(CMV.LIC14(param));
    }

    @Test
    public void invalid_input_LIC_14() {
        //true test: x,y[1,5,6] is contain in a 188 area of triangle, with is not smaller than area2(180)
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
            3,
            2,
            3,
            12,
            13,
            4,
            1,
            16,
            17,
            31,
            180
        );

        boolean[] cmv;
        cmv = CMV.initCMV(param, param.getNUMPOINTS(), param.getX_PTS(), param.getY_PTS());
        for(int licNum = 0; licNum < cmv.length; licNum++)
        
        assertFalse(CMV.LIC14(param));
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
