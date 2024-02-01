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
            case(1):
                return LIC1();
            case(2):
                return LIC2();
            default:
                return false;
            }
    }

    private static Boolean LIC0() {
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        if (param.LENGTH1 < 0)
            return false;

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                if(utils.dist(x_pts[i], y_pts[i], x_pts[j], y_pts[j]) > param.LENGTH1) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC1(){
        // Return true if there exists at least one set of three consecutive data points that cannot all be contained
        // within or on a circle of radius RADIUS1. (0 ≤ RADIUS1)
        // Else return false.

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                for(int k = j + 1; k < numPoints; k++) {

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

    private static Boolean LIC2() {
        // Return true if there exists at least one set of three consecutive data points
        // which form an angle such that: angle < (PI − EPSILON) or angle > (PI + EPSILON).
        // Return false if either the first point or the last point (or both) coincides with the vertex.
        // (0 ≤ EPSILON < PI)

        // The second of the three consecutive points is always the vertex of the angle.
        // If either the first point or the last point (or both) coincides with the vertex,
        // the angle is undefined and the LIC is not satisfied by those three points.

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                for(int k = j + 1; k < numPoints; k++) {
                    
                    int x1 = x_pts[i];
                    int y1 = y_pts[i];
                    int x2 = x_pts[j]; // Vertex
                    int y2 = y_pts[j]; // Vertex
                    int x3 = x_pts[k];
                    int y3 = y_pts[k];
            
                    Double n1 = utils.euclideanNorm(x1, y1, x2, y2);
                    Double n2 = utils.euclideanNorm(x2, y2, x3, y3);
            
                    // Calculate the cosine of the angle between the vectors
                    Double cosineAngle = utils.dotProduct(x1, y1, x2, y2, x3, y3) / (n1 * n2);
            
                    // Calculate the angle in radians
                    Double angle = Math.acos(cosineAngle);
            
                    if(angle < (Math.PI - param.EPSILON) || angle > (Math.PI + param.EPSILON)) {
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