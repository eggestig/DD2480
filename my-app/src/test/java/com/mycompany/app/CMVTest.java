package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;
import java.util.Arrays; 

/**
 * Unit test for simple App.
 */
public class CMVTest 
{
    /**
     * 
     * LIC 0 TESTS
     * 
     */

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
        assertFalse(CMV.LIC0(param));
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
        assertTrue(CMV.LIC0(param));
    }


    /**
     * 
     * LIC 1 TESTS
     * 
     */

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
        assertTrue(CMV.LIC1(param));
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
        assertFalse(CMV.LIC1(param));
    }


    /**
     * 
     * LIC 2 TESTS
     * 
     */
    

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
        assertFalse(CMV.LIC2(params));

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
        assertFalse(CMV.LIC2(params));
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
        assertFalse(CMV.LIC2(params));
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
        assertTrue(CMV.LIC2(params));
    }


    /**
     * 
     * LIC 3 TESTS
     * 
     */

    
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


    /**
     * 
     * LIC 4 TESTS
     * 
     */

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


    /**
     * 
     * LIC 5 TESTS
     * 
     */


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

        

        assertFalse("LIC 5", CMV.LIC5(param));

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
        assertTrue("LIC 5", CMV.LIC5(param));

    }


    /**
     * 
     * LIC 6 TESTS
     * 
     */


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
        assertTrue("LIC 6", CMV.LIC6(param));

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
        assertFalse("LIC 6", CMV.LIC6(param));

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
        assertFalse("LIC 6", CMV.LIC6(param));
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
        assertFalse("LIC 6", CMV.LIC6(param));
    }


    /**
     * 
     * LIC 7 TESTS
     * 
     */

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
        assertTrue("LIC 7", CMV.LIC7(param));
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
        assertFalse("LIC 7", CMV.LIC7(param));
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
        assertFalse("LIC 7", CMV.LIC7(param));
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
        assertFalse("LIC 7", CMV.LIC7(param));
    }


    /**
     * 
     * LIC 8 TESTS
     * 
     */


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
        assertTrue("LIC 8", CMV.LIC8(param));
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
        assertFalse("LIC 8", CMV.LIC8(param));
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
        assertFalse("LIC 8", CMV.LIC8(param));
    }
     

    /**
     * 
     * LIC 9 TESTS
     * 
     */

  
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


    /**
    * 
    * LIC 10 TESTS
    * 
    */


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


    /**
    * 
    * LIC 11 TESTS
    * 
    */


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


    /**
     * 
     * LIC 12 TESTS
     * 
     */

    
    //Test LIC 6 throws invalidN_PTSError for invalid N_PTS
    //Invalid N_PTS if N_PTS < 3
    @Test
    public void valid_input_LIC_12() {
        //return ture , the smallest dist is 12.69 , <17
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};
        int k_pts = 3;
        double length2 = 17;
        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH2(length2);
        param.setNUMPOINTS(10);
        
        assertTrue(CMV.LIC12(param));
    }
    @Test
    public void invalid_input_LIC_12() {
        //return false , the smallest dist is 12.69 , > 11
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};
        int k_pts = 3;
        double length2 = 11;

        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setK_PTS(k_pts);
        param.setLENGTH2(length2);
        param.setNUMPOINTS(10);

        assertFalse(CMV.LIC12(param));
    }


    /**
     * 
     * LIC 13 TESTS
     * 
     */


    @Test
    public void valid_input_LIC_13() {
        //true test: x,y[0,2,5] is contain in a 30.87 radius circle, with is smaller than radius2(50)
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};
        
        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setA_PTS(2);
        param.setB_PTS(3);
        param.setRADIUS2(50); 
        param.setNUMPOINTS(10);     
        assertTrue(CMV.LIC13(param));
    }
    
    @Test
    public void invalid_input_LIC_13() {
        //false test: the smallest radius circle is 47.9, with is smaller  not than radius2(30)
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};
        
        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setA_PTS(2);
        param.setB_PTS(3);
        param.setRADIUS2(30); 
        param.setNUMPOINTS(10); 
        
        assertFalse(CMV.LIC13(param));
    }
    

    /**
    * 
    * LIC 14 TESTS
    * 
    */


    @Test
    public void valid_input_LIC_14() {
        //true test: x,y[1,5,6] is contain in a 188 area of triangle, with is smaller than area2(250)
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};

        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setAREA2(250);
        param.setE_PTS(4);
        param.setF_PTS(1);
        param.setNUMPOINTS(10);

        assertTrue(CMV.LIC14(param));
    }

    @Test
    public void invalid_input_LIC_14() {
        //false test: x,y[1,5,6] is contain in a 188 area of triangle, with is not smaller than area2(180)
                
        double[] x_pts = new double[]{37.84,12.56,65.23,88.11,42.79,9.33, 51.45,76.02,23.67,94.88};
        double[] y_pts = new double[]{5.72,68.94,33.18,79.57,17.41,54.26,91.09,6.75,62.88,8.05};

        Parameters param = new Parameters();
        param.setX_PTS(x_pts);
        param.setY_PTS(y_pts);
        param.setAREA2(180);
        param.setE_PTS(4);
        param.setF_PTS(1);
        param.setNUMPOINTS(10);
        
        assertFalse(CMV.LIC14(param));
    }
}
