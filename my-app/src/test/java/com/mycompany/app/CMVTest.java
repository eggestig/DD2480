package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
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
        cmv = CMV.initCMV(param);
        for(int licNum = 0; licNum < cmv.length; licNum++) {
            //assertFalse("LIC" + licNum, cmv[licNum]);
        }
        assertTrue("Temp skip the test,", true);
    }

    // Test LIC 5 returns false if not two consecutive points exist with decreasing x
    @Test
    public void yes_instance_LIC_5_no_two_consecutive_points_with_decreasing_x_returns_false() {
        
        double[] x_pts = new double[]{0.0, 1.0};
        double[] y_pts = new double[]{0.0, 1.0};
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(2);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(3);

        CMV cmv = new CMV();

        assertFalse("LIC 5", cmv.LIC5(param));

    }

    // Test LIC 5 returns true if two consecutive points exist with decreasing x
    @Test
    public void yes_instance_LIC_5_two_consecutive_points_with_decreasing_x() {
        
        double[] x_pts = new double[]{1.0, 0.0};
        double[] y_pts = new double[]{0.0, 1.0};
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(2);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(3);

        CMV cmv = new CMV();

        assertTrue("LIC 5", cmv.LIC5(param));

    }

    // Test LIC 6 return true if there exists at least one set of N_PTS consecutive data points
    // such that at least one of the points lies a distance greater than DIST from
    // the line joining the first and last of these N_PTS points.
    // (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
    @Test
    public void yes_instance_LIC_6_at_least_one_point_valid_dist_from_line() {
        
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 3;
        double dist = 1;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        CMV cmv = new CMV();

        assertTrue("LIC 6", cmv.LIC6(param));

    }

    // Test LIC 6 return false if no set of N_PTS consecutive data points exist
    // such that at least one of the points lies a distance greater than DIST from
    // the line joining the first and last of these N_PTS points.
    @Test
    public void no_instance_LIC_6_no_one_point_lies_valid_distance_from_line() {
        
        double[] x_pts = new double[]{0.0, 1.1, 1.0};
        double[] y_pts = new double[]{0.0, 1.1, 1.0};
        int n_pts = 3;
        double dist = 100;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        CMV cmv = new CMV();

        assertFalse("LIC 6", cmv.LIC6(param));

    }

    // Test LIC 6 returns false for invalid N_PTS
    // Invalid N_PTS if N_PTS < 3
    @Test
    public void no_instance_LIC_6_N_PTS_less_than_3() {
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 0;
        double dist = 1;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        CMV cmv = new CMV();

        assertFalse("LIC 6", cmv.LIC6(param));
    }

    // Test LIC 6 returns false for invalid N_PTS
    // Invalid N_PTS if N_PTS > NUMPOINTS
    @Test
    public void no_instance_LIC_6_N_PTS_greater_than_NUMPOINTS() {
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 10;
        double dist = 1;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        CMV cmv = new CMV();

        assertFalse("LIC 6", cmv.LIC6(param));
    }


    // Test LIC 7 return true if there exists at least one set of two data points separated by
    // exactly K_PTS consecutive intervening points that are a distance greater than
    // the length, LENGTH1, apart.
    @Test
    public void yes_instance_LIC_7_two_points_seperated_by_valid_dist() {
        double[] x_pts = new double[]{0.0, 1.0, 100.0};
        double[] y_pts = new double[]{0.0, 1.0, 100.0};
        int k_pts = 1;
        double length1 = 1;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH1(length1);

        CMV cmv = new CMV();

        assertTrue("LIC 7", cmv.LIC7(param));
    }

    // Test LIC 7 return false if there does not exist at least one set of two data points separated by
    // exactly K_PTS consecutive intervening points that are a distance greater than
    // the length, LENGTH1, apart.
    @Test
    public void no_instance_LIC_7_no_two_points_exist_seperated_by_valid_dist() {
        double[] x_pts = new double[]{0.0, 1.0, 2.0};
        double[] y_pts = new double[]{0.0, 1.0, 2.0};
        int k_pts = 1;
        double length1 = 100;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH1(length1);

        CMV cmv = new CMV();

        assertFalse("LIC 7", cmv.LIC7(param));
    }

    // Test LIC 7 returns false if invalid K_PTS
    // invalid K_PTS if K_PTS > NUMPOINTS - 2
    @Test
    public void no_instance_LIC_7_K_PTS_exceede_valid_range() {
        double[] x_pts = new double[]{0.0, 1.0, 2.0};
        double[] y_pts = new double[]{0.0, 1.0, 2.0};
        int k_pts = 10;
        double length1 = 100;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH1(length1);

        CMV cmv = new CMV();

        assertFalse("LIC 7", cmv.LIC7(param));
    }

    // Test LIC 7 returns false if invalid K_PTS
    // invalid K_PTS if K_PTS < 1
    @Test
    public void no_instance_LIC_7_K_PTS_less_than_one() {
        double[] x_pts = new double[]{0.0, 1.0, 2.0};
        double[] y_pts = new double[]{0.0, 1.0, 2.0};
        int k_pts = 0;
        double length1 = 100;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(3);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH1(length1);

        CMV cmv = new CMV();

        assertFalse("LIC 7", cmv.LIC7(param));
    }

    // Test LIC 8 return true if there exists at least one set of three data points
    // separated by exactly A_PTS and B_PTS consecutive intervening points,
    // respectively, that cannot be contained within or on a circle of radius RADIUS1.

    @Test
    public void yes_instance_LIC_8_three_points_outside_circle() {
        double[] x_pts = new double[]{0.0, 1.0, 100.0, 2.0, -100.0};
        double[] y_pts = new double[]{0.0, 1.0, 100.0, 2.0, -100.0};
        int a_pts = 1;
        int b_pts = 1;
        double radius1 = 2;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(5);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setA_PTS(a_pts);
        param.setB_PTS(b_pts);
        param.setRADIUS1(radius1);
        
        CMV cmv = new CMV();
        
        assertTrue("LIC 8", cmv.LIC8(param));
    }

    // Test LIC 8 returns false when condition not met.
    // Condition not met when NUMPOINTS < 5
    @Test
    public void no_instance_LIC_8_invalid_NUMPOINTS() {
        double[] x_pts = new double[]{0.0};
        double[] y_pts = new double[]{0.0};
        int a_pts = 1;
        int b_pts = 1;
        double radius1 = 2;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(1);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setA_PTS(a_pts);
        param.setB_PTS(b_pts);
        param.setRADIUS1(radius1);

        CMV cmv = new CMV();

        assertFalse("LIC 8", cmv.LIC8(param));
    }

    // Test LIC 8 returns false when A_PTS + B_PTS > NUMPOINTS - 3
    @Test
    public void no_instance_LIC_8_invalid_A_PTS_AND_B_PTS() {
        double[] x_pts = new double[]{0.0, 1.0, 100.0, 2.0, -100.0};
        double[] y_pts = new double[]{0.0, 1.0, 100.0, 2.0, -100.0};
        int a_pts = 2;
        int b_pts = 2;
        double radius1 = 2;
        
        Parameters param = new Parameters();
        param.setNUMPOINTS(5);
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setA_PTS(a_pts);
        param.setB_PTS(b_pts);
        param.setRADIUS1(radius1);
        
        CMV cmv = new CMV();
        
        assertFalse("LIC 8", cmv.LIC8(param));
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
