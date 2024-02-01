package com.mycompany.app;

import java.util.HashSet;

public class CMV {

    private static int numPoints;
    private static Parameters param;
    private static int[] x_pts;
    private static int[] y_pts;
    private static LICutils utils = new LICutils();

    private static Boolean evalLIC(int LIC_num) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0();
            default:
                return false;
            }
    }

    private static Boolean LIC0() {
        // TODO:
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        return false;
    }

    public static Boolean[] initCMV(Parameters PARAM, int NUMPOINTS, int[] xpts, int[] ypts) {

        numPoints = NUMPOINTS;
        param = PARAM;
        x_pts = xpts;
        y_pts = ypts;
        Boolean[] cmv = new Boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i);
        }

        return cmv;
    }
}