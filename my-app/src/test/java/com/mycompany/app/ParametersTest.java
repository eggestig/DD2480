package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mycompany.app.Parameters;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class ParametersTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testParamtersSame()
    {
        Parameters param;
        Boolean[] cmv = new Boolean[15];
        int NUMPOINTS;
        int[] x_pts;
        int[] y_pts;

        Random rand = new Random();

        NUMPOINTS = rand.nextInt(100 - 2) + 2;
        int p_pts = rand.nextInt(NUMPOINTS - 2) + 2;
        x_pts = new int[NUMPOINTS];
        y_pts = new int[NUMPOINTS];

        double epsilon = (double) rand.nextInt(3);

        int q_pts = rand.nextInt(NUMPOINTS);
        int quad = rand.nextInt(10);

        param = new Parameters(
            100.0,
            10.0,
            epsilon,
            1000.0,
            q_pts, 
            quad,
            100.0,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            p_pts,
            100.0,
            50.0,
            100.0
        );

        assertEquals("length1,", 100.0, param.LENGTH1, 0.000001); 
        assertEquals("radius1,", 10.0, param.RADIUS1, 0.000001); 
        assertEquals("epsilon,", epsilon, param.EPSILON, 0.000001); 
        assertEquals("area1,", 1000.0, param.AREA1, 0.000001); 
        assertEquals("q_pts,", q_pts, param.Q_PTS, 0.000001); 
        assertEquals("quads,", quad, param.QUADS, 0.000001); 
        assertEquals("dist,", 100.0, param.DIST, 0.000001); 
        assertEquals("n_pts,", p_pts, param.N_PTS); 
        assertEquals("k_pts,", p_pts, param.K_PTS); 
        assertEquals("a_pts,", p_pts, param.A_PTS); 
        assertEquals("b_pts,", p_pts, param.B_PTS); 
        assertEquals("c_pts,", p_pts, param.C_PTS); 
        assertEquals("d_pts,", p_pts, param.D_PTS); 
        assertEquals("e_pts,", p_pts, param.E_PTS); 
        assertEquals("f_pts,", p_pts, param.F_PTS); 
        assertEquals("g_pts,", p_pts, param.G_PTS); 
        assertEquals("length2,", 100.0, param.LENGTH2, 0.000001); 
        assertEquals("radius2,", 50.0, param.RADIUS2, 0.000001); 
        assertEquals("area2", 100.0, param.AREA2, 0.000001); 
    }
}
