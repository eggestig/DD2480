package com.mycompany.app;

import java.util.HashSet;

public class CMV {

    private static int numPoints;
    private static Parameters param;
    private static double[] x_pts;
    private static double[] y_pts;
    private static LICutils utils = new LICutils();

    private static boolean evalLIC(int LIC_num) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(3):
                return LIC3();
            case(4):
                return LIC4();
            case(5):
                return LIC5();
            default:
                return false;
            }
    }

    private static Boolean LIC3() {
        // Return true if there exists at least one set of three consecutive data points
        // that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        // (0 ≤ AREA1)
        if (x_pts.length<3 || y_pts.length<3) {
            return false;
        }

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                for(int k = j + 1; k < numPoints; k++) {
                
                    double x1 = x_pts[i];
                    double y1 = y_pts[i];
                    double x2 = x_pts[j];
                    double y2 = y_pts[j];
                    double x3 = x_pts[k];
                    double y3 = y_pts[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > param.AREA1) {
                        return true;
                    }
                
                }
            }
        }

        return false;
    }

    // ************ Verify that this method works as intended, not sure if it's implemented correctly *****//
    private static Boolean LIC4() {
        // Return true if there exists at least one set of Q_PTS consecutive data points 
        // that lie in more than QUADS quadrants.
        // Else return false.

        // Where there is ambiguity as to which quadrant contains a given point,
        // priority of decision will be by quadrant number, i.e., I, II, III, IV.
        // For example, the data point (0,0) is in quadrant I, the point (-l,0) is in quadrant II, 
        // the point (0,-l) is in quadrant III, the point (0,1) is in quadrant I and the point (1,0) is in quadrant I.
        // (2 ≤ Q_PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)

        for(int i = 0; i < numPoints - param.Q_PTS; i++) {
        
            // Set to store unique quadrants
            HashSet<Integer> quadrants = new HashSet<>();

            for (int p = i; p < param.Q_PTS; p++) {
                int x = x_pts[p];
                int y = y_pts[p];

                // Determine the quadrant for the current point
                int quadrant = utils.getQuadrant(x, y);

                quadrants.add(quadrant);
            }

            if(quadrants.size() > param.QUADS) {
                return true;
            }

        }

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
    

    public static boolean[] initCMV(Parameters PARAM, int NUMPOINTS, double[] xpts, double[] ypts) {

        numPoints = NUMPOINTS;
        param = PARAM;
        x_pts = xpts;
        y_pts = ypts;
        boolean[] cmv = new boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i);
        }

        return cmv;
    }
}
