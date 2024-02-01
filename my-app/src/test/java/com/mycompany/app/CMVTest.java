package com.mycompany.app;

import static org.junit.Assert.assertEquals;
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

    /* 
    * Test LIC 9 - Yes-instance => True - Angle meets the requirements,
    * Expect a yes instance to return true. 
    */
    @Test
    public void yes_instance_LIC_9_correct_angle() {
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

    /* 
    * Test LIC 9 - No-instance => False - Angle does not meet the requirement, 
    * Expect a no instance to return false, for any reason
    */
    @Test
    public void no_instance_LIC_9_wrong_angle() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setC_PTS(1);
        params.setD_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(0.0);
    
        assertFalse("LIC9", CMV.LIC9(params));
    }

    /* 
    * Test LIC 9 - No-instance => False - Due to C_PTS < 1, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_9_C_PTS_LT_1() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setC_PTS(0);
        params.setD_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);
    
        assertFalse("LIC9", CMV.LIC9(params));
    }

    /* 
    * Test LIC 9 - No-instance => False - Due to D_PTS < 1, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_9_D_PTS_LT_1() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setC_PTS(1);
        params.setD_PTS(0);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);
    
        assertFalse("LIC9", CMV.LIC9(params));
    }

    /* 
    * Test LIC 9 - No-instance => False - Due to C_PTS + D_PTS < NUMPOINTS - 3, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_9_C_PTS_D_PTS_LT_NUMPOINTS_3() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(10);
        params.setC_PTS(4);
        params.setD_PTS(4);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);
    
        assertFalse("LIC9", CMV.LIC9(params));
    }

    /* 
    * Test LIC 9 - No-instance => False - Due to NUMPOINTS < 5, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_9_NUMPOINTS_LT_5() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(4);
        params.setC_PTS(1);
        params.setD_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);
    
        assertFalse("LIC9", CMV.LIC9(params));
    }

    /* 
    * Test LIC 10 - Yes-instance => True - Triangle area meets the requirements,
    * Expect a yes instance to return true. 
    */
    @Test
    public void yes_instance_LIC_10_correct_angle() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setE_PTS(1);
        params.setF_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.4);
    
        assertTrue("LIC10", CMV.LIC10(params));
    }

    /* 
    * Test LIC 10 - No-instance => False - Triangle area does not meet the requirement, 
    * Expect a no instance to return false, for any reason
    */
    @Test
    public void no_instance_LIC_10_wrong_angle() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setE_PTS(1);
        params.setF_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.5);
    
        assertFalse("LIC10", CMV.LIC10(params));
    }

    /* 
    * Test LIC 10 - No-instance => False - Due to E_PTS < 1, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_10_E_PTS_LT_1() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setE_PTS(0);
        params.setF_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.4);
    
        assertFalse("LIC10", CMV.LIC10(params));
    }

    /* 
    * Test LIC 10 - No-instance => False - Due to F_PTS < 1, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_10_F_PTS_LT_1() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setE_PTS(1);
        params.setF_PTS(0);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.4);
    
        assertFalse("LIC10", CMV.LIC10(params));
    }

    /* 
    * Test LIC 10 - No-instance => False - Due to C_PTS + D_PTS < NUMPOINTS - 3, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_10_C_PTS_D_PTS_LT_NUMPOINTS_3() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(8);
        params.setE_PTS(4);
        params.setF_PTS(4);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.4);
    
        assertFalse("LIC10", CMV.LIC10(params));
    }

    /* 
    * Test LIC 10 - No-instance => False - Due to NUMPOINTS < 5, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_10_NUMPOINTS_LT_5() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(4);
        params.setE_PTS(1);
        params.setF_PTS(2);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setAREA1(7.4);
    
        assertFalse("LIC10", CMV.LIC10(params));
    }












/* 
    * Test LIC 11 - Yes-instance => True - Point positions meets the requirements,
    * Expect a yes instance to return true. 
    */
    @Test
    public void yes_instance_LIC_11_first_point_bigger() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setG_PTS(1);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -1.0, -4.0, -5.0, -6.0});
    
        assertTrue("LIC11", CMV.LIC11(params));
    }

    /* 
    * Test LIC 11 - No-instance => False - Point positions does not meet the requirement, 
    * Expect a no instance to return false, for any reason
    */
    @Test
    public void no_instance_LIC_11_first_point_not_bigger() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setG_PTS(1);
        params.setX_PTS(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
    
        assertFalse("LIC11", CMV.LIC11(params));
    }

    /* 
    * Test LIC 11 - No-instance => False - Due to G_PTS < 1, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_11_G_PTS_LT_1() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setG_PTS(0);
        params.setX_PTS(new double[]{1.0, 2.0, 2.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -1.0, -4.0, -5.0, -6.0});
    
        assertFalse("LIC11", CMV.LIC11(params));
    }

    /* 
    * Test LIC 11 - No-instance => False - Due to G_PTS < NUMPOINTS - 2, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_11_G_PTS_LT_NUMPOINTS_2() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setG_PTS(5);
        params.setX_PTS(new double[]{1.0, 2.0, 2.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -1.0, -4.0, -5.0, -6.0});
    
        assertFalse("LIC11", CMV.LIC11(params));
    }

    /* 
    * Test LIC 11 - No-instance => False - Due to NUMPOINTS < 3, 
    * Expect a no instance to return false due to the above edge case
    */
    @Test
    public void edge_cases_LIC_11_NUMPOINTS_LT_3() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(2);
        params.setG_PTS(1);
        params.setX_PTS(new double[]{1.0, 2.0, 2.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -1.0, -4.0, -5.0, -6.0});
    
        assertFalse("LIC11", CMV.LIC11(params));
    }














    //Test LIC 11
    //
    @Test
    public void valid_input_LIC_11_() {
        Parameters params = new Parameters();
        params.setNUMPOINTS(6);
        params.setG_PTS(1);
        params.setX_PTS(new double[]{1.0, 2.0, 0.0, 4.0, 5.0, 6.0});
        params.setY_PTS(new double[]{-1.0, -2.0, -3.0, -4.0, -5.0, -6.0});
        params.setEPSILON(-1.0);

        System.out.println("");
        System.out.println("[TEST]");
        System.out.println("");
        System.out.flush(); 

        CMV cmv = new CMV();
    
        assertTrue("LIC11", cmv.LIC11(params));
    }
}
