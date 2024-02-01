package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mycompany.app.Parameters;
import java.util.Random;
import java.util.Arrays; 

/**
 * Unit test for simple App.
 */
public class ParametersTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void valid_input_Parameters_consistent()
    {
        Parameters param;
        int numpoints;
        double[] x_pts;
        double[] y_pts;


        Random rand = new Random();

        numpoints = rand.nextInt(100 - 2) + 2;
        x_pts = new double[numpoints];
        y_pts = new double[numpoints];

        // Fill arrays
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

        assertEquals("NUMPOINTS",  numpoints, param.getNUMPOINTS()); 
        assertArrayEquals("X_PTS", x_pts,     param.getX_PTS(), 0.000001); 
        assertArrayEquals("Y_PTS", y_pts,     param.getY_PTS(), 0.000001); 
        assertEquals("LENGTH1",    1.0,       param.getLENGTH1(), 0.000001); 
        assertEquals("RADIUS1",    2.0,       param.getRADIUS1(), 0.000001); 
        assertEquals("EPSILON",    3.0,       param.getEPSILON(), 0.000001); 
        assertEquals("AREA1",      4.0,       param.getAREA1(), 0.000001); 
        assertEquals("Q_PTS",      5,         param.getQ_PTS()); 
        assertEquals("QUADS",      6,         param.getQUADS()); 
        assertEquals("DIST",       7.0,       param.getDIST(), 0.000001); 
        assertEquals("N_PTS",      8,         param.getN_PTS()); 
        assertEquals("K_PTS",      9,         param.getK_PTS()); 
        assertEquals("A_PTS",      10,        param.getA_PTS()); 
        assertEquals("B_PTS",      11,        param.getB_PTS()); 
        assertEquals("C_PTS",      12,        param.getC_PTS()); 
        assertEquals("D_PTS",      13,        param.getD_PTS()); 
        assertEquals("E_PTS",      14,        param.getE_PTS()); 
        assertEquals("F_PTS",      15,        param.getF_PTS()); 
        assertEquals("G_PTS",      16,        param.getG_PTS()); 
        assertEquals("LENGTH2",    17.0,      param.getLENGTH2(), 0.000001); 
        assertEquals("RADIUS2",    18.0,      param.getRADIUS2(), 0.000001); 
        assertEquals("AREA2",      19.0,      param.getAREA2(), 0.000001); 
    }
}
