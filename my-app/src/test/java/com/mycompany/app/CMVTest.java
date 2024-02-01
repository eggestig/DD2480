package com.mycompany.app;

import static org.junit.Assert.assertEquals;
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
    public void testCMV() {
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
        cmv = CMV.initCMV(param);
        for(int licNum = 0; licNum < cmv.length; licNum++) {
            assertFalse("LIC" + licNum, cmv[licNum]);
        }
    }

    // Test LIC 5 returns false if not two consecutive points exist with decreasing x
    @Test
    public void valid_input_LIC_5_no_two_consecutive_points_with_decreasing_x_returns_false() {
        
        double[] x_pts = new double[]{0.0, 1.0};
        double[] y_pts = new double[]{0.0, 1.0};
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);

        cmv = CMV.initCMV(param);

        assertFalse("LIC 5", cmv[5]);

    }

    // Test LIC 5 returns true if two consecutive points exist with decreasing x
    @Test
    public void valid_input_LIC_5_two_consecutive_points_with_decreasing_x_returns_true() {
        
        double[] x_pts = new double[]{1.0, 0.0};
        double[] y_pts = new double[]{0.0, 1.0};
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);

        Boolean[] cmv;
        cmv = CMV.initCMV(param);

        assertTrue("LIC 5", cmv[5]);

    }

    // Test LIC 6 return true if there exists at least one set of N_PTS consecutive data points
    // such that at least one of the points lies a distance greater than DIST from
    // the line joining the first and last of these N_PTS points.
    // (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
    @Test
    public void valid_input_LIC_6_n_points_exist_where_one_point_lies_valid_distance_from_line_joining_first_and_last_n_points_returns_true() {
        
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 3;
        double dist = 1;
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        Boolean[] cmv;
        cmv = CMV.initCMV(param);

        assertTrue("LIC 5", cmv[5]);

    }

    // Test LIC 6 return false if no set of N_PTS consecutive data points exist
    // such that at least one of the points lies a distance greater than DIST from
    // the line joining the first and last of these N_PTS points.
    @Test
    public void valid_input_LIC_6_n_points_no_one_point_lies_valid_distance_from_line_joining_first_and_last_n_points_returns_false() {
        
        double[] x_pts = new double[]{0.0, 1.1, 1.0};
        double[] y_pts = new double[]{0.0, 1.1, 1.0};
        int n_pts = 3;
        double dist = 100;
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        Boolean[] cmv;
        cmv = CMV.initCMV(param);

        assertFalse("LIC 5", cmv[5]);

    }

    //Test LIC 6 throws invalidN_PTSError for invalid N_PTS
    //Invalid N_PTS if N_PTS < 3
    @Test
    public void invalid_input_LIC_6_N_PTS_less_than_3_throws_invalid_N_PTS_error() {
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 0;
        double dist = 1;
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        Boolean[] cmv;
        cmv = CMV.initCMV(param);

        // assert test throws custom error
    }

    //Test LIC 6 returns false for invalid N_PTS
    //Invalid N_PTS if N_PTS > NUMPOINTS
    @Test
    public void valid_input_LIC_6_N_PTS_greater_than_NUMPOINTS_returns_false() {
        double[] x_pts = new double[]{0.0, 100.0, 1.0};
        double[] y_pts = new double[]{0.0, 100.0, 1.0};
        int n_pts = 4;
        double dist = 1;
        
        param = param.deepCopy();
        param.setNUMPOINTS(2);
        param.setXpoints(x_pts);
        param.setYpoints(y_pts);
        param.setN_PTS(n_pts);
        param.setDIST(dist);

        Boolean[] cmv;
        cmv = CMV.initCMV(param);

        assertFalse("LIC 5", cmv[5]);
    }
}
