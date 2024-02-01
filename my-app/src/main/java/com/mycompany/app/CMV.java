package com.mycompany.app;

import java.util.HashSet;
import java.util.Arrays;

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
                return LIC3(param);
            case(4):
                return LIC4(param);
            case(5):
                return LIC5(param);
            case(9):
                return LIC9(param);
            case(10):
                return LIC10(param);
            case(11):
                return LIC11(param);
            default:
                return false;
            }
    }

    public static Boolean LIC3(Parameters params) {
        // Return true if there exists at least one set of three consecutive data points
        // that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        // (0 ≤ AREA1)

        if (params.getX_PTS().length<3 || params.getY_PTS().length<3) {
            return false;
        }

        for(int i = 0; i < params.getNUMPOINTS(); i++) {
            for(int j = i + 1; j < params.getNUMPOINTS(); j++) {
                for(int k = j + 1; k < params.getNUMPOINTS(); k++) {
                
                    double x1 = params.getX_PTS()[i];
                    double y1 = params.getY_PTS()[i];
                    double x2 = params.getX_PTS()[j];
                    double y2 = params.getY_PTS()[j];
                    double x3 = params.getX_PTS()[k];
                    double y3 = params.getY_PTS()[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > params.getAREA1()) {
                        return true;
                    }
                
                }
            }
        }
        return false;
    }

    // ************ Verify that this method works as intended, not sure if it's implemented correctly *****//
    public static Boolean LIC4(Parameters params) {
        // Return true if there exists at least one set of Q_PTS consecutive data points 
        // that lie in more than QUADS quadrants.
        // Else return false.

        // Where there is ambiguity as to which quadrant contains a given point,
        // priority of decision will be by quadrant number, i.e., I, II, III, IV.
        // For example, the data point (0,0) is in quadrant I, the point (-l,0) is in quadrant II, 
        // the point (0,-l) is in quadrant III, the point (0,1) is in quadrant I and the point (1,0) is in quadrant I.
        // (2 ≤ Q_PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)

        if (params.getQUADS()<1 || params.getX_PTS().length<1 || params.getY_PTS().length<1) {
            return false;        
        }

        for(int i = 0; i <= params.getNUMPOINTS() - params.getQ_PTS(); i++) {
        
            // Set to store unique quadrants
            HashSet<Double> quad_set = new HashSet<>();

            for (int p = 0; p < params.getQ_PTS(); p++) {
                double x = params.getX_PTS()[i+p];
                double y = params.getY_PTS()[i+p];

                // Determine the quadrant for the current point
                double quadrant = LICutils.getQuadrant(x, y);
                quad_set.add(quadrant);
                if(quad_set.size() > params.getQUADS()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean LIC5(Parameters params) {
        // Return true if there exists at least one set of two consecutive data points,
        // (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0
        // (where i = j-1).
        // Else return false.

        for(int i = 0; i < params.getNUMPOINTS(); i++) {
            for(int j = i + 1; j < params.getNUMPOINTS(); j++) {
                if(params.getX_PTS()[j] - params.getX_PTS()[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean LIC9(Parameters params) {
        // Return true if there exists at least one set of three data points separated by
        // exactly C_PTS and D_PTS consecutive intervening points, respectively,
        // that form an angle such that:
        // angle < (PI − EPSILON) or angle > (PI + EPSILON)
        // Else return false.

        // The second point of the set of three points is always the vertex of the angle.
        // If either the first point or the last point (or both) coincide with the vertex,
        // the angle is undefined and the LIC is not satisfied by those three points. 
        // When NUMPOINTS < 5, the condition is not met.
        // 1 ≤ C_PTS, 1 ≤ D_PTS
        // C_PTS + D_PTS ≤ NUMPOINTS−3

        //TODO: Throw errors here if invalid input

        for(int i = 0; i < params.getNUMPOINTS() - (params.getC_PTS() + params.getD_PTS() + 2); i++) {
            int j = i + 1 + params.getC_PTS();
            int k = j + 1 + params.getD_PTS();
            
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[j]; // Vertex
            double y2 = params.getY_PTS()[j]; // Vertex
            double x3 = params.getX_PTS()[k];
            double y3 = params.getY_PTS()[k];

            double n1 = LICutils.euclideanNorm(x1, y1, x2, y2);
            double n2 = LICutils.euclideanNorm(x2, y2, x3, y3);

            // Calculate the cosine of the angle between the vectors
            double cosineAngle = LICutils.dotProduct(x1, y1, x2, y2, x3, y3) / (Math.abs(n1) * Math.abs(n2));

            // Calculate the angle in radians
            double angle = Math.acos(cosineAngle);
    
            if(angle < (Math.PI - params.getEPSILON()) || angle > (Math.PI + params.getEPSILON())) {
                //System.out.println(angle  + "<" + (Math.PI - params.getEPSILON()) + " || " + angle + ">" + (Math.PI + params.getEPSILON()));
                //System.out.flush(); 
                return true;
            }
        }

        return false;
    }
                    

    public static Boolean LIC10(Parameters params) {
        // Return true if there exists at least one set of three data points
        // separated by exactly E_PTS and F_PTS consecutive intervening points,
        // respectively, that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ E_PTS, 1 ≤ F_PTS
        // E_PTS + F_PTS ≤ NUMPOINTS − 3


        //TODO: Throw errors here if invalid input
        if(params.getX_PTS().length < 5 || params.getY_PTS().length < 5) {return false;}

        for(int i = 0; i < params.getNUMPOINTS() + (params.getE_PTS() + params.getF_PTS()); i++) {
            for(int j = i + params.getE_PTS(); j < params.getNUMPOINTS() - params.getF_PTS(); j++) {
                for(int k = j + params.getF_PTS(); k < params.getNUMPOINTS(); k++) {
                
                    double x1 = params.getX_PTS()[i];
                    double y1 = params.getY_PTS()[i];
                    double x2 = params.getX_PTS()[j]; // Vertex
                    double y2 = params.getY_PTS()[j]; // Vertex
                    double x3 = params.getX_PTS()[k];
                    double y3 = params.getY_PTS()[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > params.getAREA1()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public static Boolean LIC11(Parameters params) {
        // Return true if there exists at least one set of two data points,
        // (X[i],Y[i]) and (X[j],Y[j]), separated by exactly G_PTS
        // consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ G_PTS ≤ NUMPOINTS−2

        //TODO: Throw errors here if invalid input

        if(params.getX_PTS().length < 3 || params.getY_PTS().length < 3) {return false;}

        for(int i = 0; i < params.getNUMPOINTS() - params.getG_PTS(); i++) {
            for(int j = i + params.getG_PTS(); j < params.getNUMPOINTS(); j++) {
                if(params.getX_PTS()[j] - params.getX_PTS()[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }
    

    public static boolean[] initCMV(Parameters params, int NUMPOINTS, double[] xpts, double[] ypts) {

        numPoints = NUMPOINTS;
        param = params;
        x_pts = xpts;
        y_pts = ypts;
        boolean[] cmv = new boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i);
        }

        return cmv;
    }
}
