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
            case(5):
                return LIC5();
            case(6):
                return LIC6();
            case(7):
                return LIC7();
            case(8):
                return LIC8();
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

    private static Boolean LIC5() {
        // Return true if there exists at least one set of two consecutive data points,
        // (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0
        // (where i = j-1).
        // Else return false.

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                if(x_pts[j] - x_pts[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC6() {
        // Return true if there exists at least one set of N_PTS consecutive data points
        // such that at least one of the points lies a distance greater than DIST from
        // the line joining the first and last of these N_PTS points.
        // Else return false.

        // If the first and last points of these N_PTS are identical,
        // then the calculated distance to compare with DIST will be the distance from the
        // coincident point to all other points of the N_PTS consecutive points.
        // The condition is not met when NUMPOINTS < 3.
        // (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)

        if(x_pts.length < 3 || y_pts.length < 3) {return false;}

        for(int i = 0; i < numPoints - param.N_PTS; i++) {
            for(int j = i + 1; j < param.N_PTS; j++) {
                if(utils.distToLine(x_pts[j], y_pts[j], x_pts[i], y_pts[i], x_pts[i + param.N_PTS], y_pts[i + param.N_PTS]) > param.DIST) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC7() {
        // Return true if there exists at least one set of two data points separated by
        // exactly K_PTS consecutive intervening points that are a distance greater than
        // the length, LENGTH1, apart.
        // Else return false.

        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ K_PTS ≤ (NUMPOINTS − 2)

        if(x_pts.length < 3 || y_pts.length < 3) {return false;}
        
        for(int i = 0; i < numPoints - param.K_PTS - 1; i++) {
            if(utils.dist(x_pts[i], y_pts[i], x_pts[i + param.K_PTS + 1], y_pts[i + param.K_PTS + 1]) > param.LENGTH1) {
                return true;
            }
        }

        return false;
    }

    private static Boolean LIC8() {
        // Return true if there exists at least one set of three data points
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // Else return false.

        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ A_PTS, 1 ≤ B_PTS
        // A_PTS + B_PTS ≤ (NUMPOINTS−3)

        if(x_pts.length < 5 || y_pts.length < 5) {return false;}

        for(int i = 0; i < numPoints - (param.A_PTS + param.B_PTS); i++) {
            for(int j = i + param.A_PTS; j < numPoints - param.B_PTS; j++) {
                for(int k = j + param.B_PTS; k < numPoints; k++) {

                    int x1 = x_pts[i]; int y1 = y_pts[i];
                    int x2 = x_pts[j]; int y2 = y_pts[j];
                    int x3 = x_pts[k]; int y3 = y_pts[k];
            
                    Double a = utils.dist(x1, y1, x2, y2);
                    Double b = utils.dist(x2, y2, x3, y3);
                    Double c = utils.dist(x3, y3, x1, y1);
            
                    if(utils.circumRadius(a, b, c) > param.RADIUS1) {
                        return true;
                    }
                }
            }
        }

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