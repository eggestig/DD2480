package com.mycompany.app;

import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Arrays;

public class CMV{

    public static boolean evalLIC(int LIC_num, Parameters param) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0(param);
            case(1):
                return LIC1(param);
            case(2):
                return LIC2(param);
            case(3):
                return LIC3(param);
            case(4):
                return LIC4(param);
            case(5):
                return LIC5(param);
            case(6):
                return LIC6(param);
            case(7):
                return LIC7(param);
            case(8):
                return LIC8(param);
            case(9):
                return LIC9(param);
            case(10):
                return LIC10(param);
            case(11):
                return LIC11(param);
            case(12):
                return LIC12(param);
            case(13):
                return LIC13(param);
            case(14):
                return LIC14(param);
            default:
                return false;
            }
    }

    public static boolean LIC0(Parameters params) {
        // TODO:
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.
        // (0 ≤ AREA1)

        if (params.getX_PTS().length < 2 || params.getY_PTS().length  < 2) {
            return false;
        }

        if (params.getLENGTH1() < 0)
            return false;

        for(int i = 0; i < params.getNUMPOINTS() - 1; i++) {
            if(LICutils.dist(params.getX_PTS()[i], params.getY_PTS()[i], params.getX_PTS()[i+1], params.getY_PTS()[i+1]) > params.getLENGTH1()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean LIC1(Parameters params){
        // Return true if there exists at least one set of three consecutive data points that cannot all be contained
        // within or on a circle of radius RADIUS1. (0 ≤ RADIUS1)
        // Else return false.

        if (params.getX_PTS().length < 3 || params.getY_PTS().length  < 3 ) {
            return false;
        }

        for(int i = 0; i < params.getNUMPOINTS() - 2; i++) {

            double x1 = params.getX_PTS()[i]; double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[i+1]; double y2 = params.getY_PTS()[i+1];
            double x3 = params.getX_PTS()[i+2]; double y3 = params.getY_PTS()[i+2];
    
            double a = LICutils.dist(x1, y1, x2, y2);
            double b = LICutils.dist(x2, y2, x3, y3);
            double c = LICutils.dist(x3, y3, x1, y1);

            if(LICutils.circumRadius(a, b, c) > params.getRADIUS1()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean LIC2(Parameters params) {
        // Return true if there exists at least one set of three consecutive data points
        // which form an angle such that: angle < (PI − EPSILON) or angle > (PI + EPSILON).
        // Return false if either the first point or the last point (or both) coincides with the vertex.
        // (0 ≤ EPSILON < PI)

        // The second of the three consecutive points is always the vertex of the angle.
        // If either the first point or the last point (or both) coincides with the vertex,
        // the angle is undefined and the LIC is not satisfied by those three points.

        if (params.getX_PTS().length < 3 || params.getY_PTS().length  < 3 ) {
            return false;
        }

        if (params.getEPSILON() < 0 || params.getEPSILON() > Math.PI) {
            return false;            
        }

        for(int i = 0; i < params.getNUMPOINTS() - 2; i++) {
                    
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[i+1]; // Vertex
            double y2 = params.getY_PTS()[i+1]; // Vertex
            double x3 = params.getX_PTS()[i+2];
            double y3 = params.getY_PTS()[i+2];
    
            double n1 = LICutils.euclideanNorm(x1, y1, x2, y2);
            double n2 = LICutils.euclideanNorm(x2, y2, x3, y3);
    
            // Calculate the cosine of the angle between the vectors
            double cosineAngle = LICutils.dotProduct(x1, y1, x2, y2, x3, y3) / (n1 * n2);
    
            // Calculate the angle in radians
            double angle = Math.acos(cosineAngle);
    
            if(angle < (Math.PI - params.getEPSILON()) || angle > (Math.PI + params.getEPSILON())) {
                return true;
            }        
        }
      
        return false;
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

        if (params.getX_PTS().length<2 || params.getY_PTS().length<2 || params.getNUMPOINTS() < 2) {
            System.out.println("Error");
            return false;
        }
        System.out.println("Continued !!!");

        for(int i = 0; i < params.getNUMPOINTS(); i++) {
            for(int j = i + 1; j < params.getNUMPOINTS(); j++) {
                if(params.getX_PTS()[j] - params.getX_PTS()[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Boolean LIC6(Parameters param) {
        // Return true if there exists at least one set of N_PTS consecutive data points
        // such that at least one of the points lies a distance greater than DIST from
        // the line joining the first and last of these N_PTS points.
        // Else return false.

        // If the first and last points of these N_PTS are identical,
        // then the calculated distance to compare with DIST will be the distance from the
        // coincident point to all other points of the N_PTS consecutive points.
        // The condition is not met when NUMPOINTS < 3.
        // (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        double dist = param.getDIST();
        int n_pts = param.getN_PTS();
        
        if(x_pts.length < 3 || y_pts.length < 3) {
            return false;
        }
        
        if(n_pts < 3 || n_pts > numPoints || dist < 0) {
            return false;
        }

        for(int i = 0; i < numPoints; i++) {
            if(i + n_pts - 1 >= numPoints) {
                return false;
            }
            for(int j = i + 1; j < i + n_pts - 1; j++) {
                double x1 = x_pts[i];
                double y1 = y_pts[i];
                double x2 = x_pts[j];
                double y2 = y_pts[j];
                double x3 = x_pts[i + n_pts - 1];
                double y3 = y_pts[i + n_pts - 1];
                if(x1 == x3 && y1 == y3) {
                    if(LICutils.dist(x1, y1, x2, y2) > dist) {
                        return true;
                    }
                } else if(LICutils.distToLine(x1, y1, x2, y2, x3, y3) > dist) {
                    return true;
                }
            }
        }

        return  false;
    }

    public static Boolean LIC7(Parameters param) {
        // Return true if there exists at least one set of two data points separated by
        // exactly K_PTS consecutive intervening points that are a distance greater than
        // the length, LENGTH1, apart.
        // Else return false.

        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ K_PTS ≤ (NUMPOINTS − 2)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        int k_pts = param.getK_PTS();
        double length1 = param.getLENGTH1();

        if(k_pts < 1 || k_pts > numPoints - 2 || length1 < 0) {
            return false;
        }

        if(x_pts.length < 3 || y_pts.length < 3) {
            return false;
        }
        

        System.out.flush();
        for(int i = 0; i < numPoints - k_pts - 1; i++) {
            if(LICutils.dist(x_pts[i], y_pts[i], x_pts[i + k_pts + 1], y_pts[i + k_pts + 1]) > length1) {

                return true;
            }
        }
        return false;
    }

    public static Boolean LIC8(Parameters param) {
        // Return true if there exists at least one set of three data points
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // Else return false.

        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ A_PTS, 1 ≤ B_PTS
        // A_PTS + B_PTS ≤ (NUMPOINTS−3)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        int a_pts = param.getA_PTS();
        int b_pts = param.getB_PTS();
        double radius1 = param.getRADIUS1();

        if(a_pts < 1 || b_pts < 1 || (a_pts + b_pts) > numPoints - 3) {
            return false;
        }

        if(radius1 < 0) {
            return false;
        }

        if(x_pts.length < 5 || y_pts.length < 5) {
            return false;
        }

        for(int i = 0; i < numPoints - (a_pts + b_pts); i++) {
            for(int j = i + a_pts; j < numPoints - b_pts; j++) {
                for(int k = j + b_pts; k < numPoints; k++) {

                    double x1 = x_pts[i]; double y1 = y_pts[i];
                    double x2 = x_pts[j]; double y2 = y_pts[j];
                    double x3 = x_pts[k]; double y3 = y_pts[k];
            
                    Double a = LICutils.dist(x1, y1, x2, y2);
                    Double b = LICutils.dist(x2, y2, x3, y3);
                    Double c = LICutils.dist(x3, y3, x1, y1);
                    if(LICutils.circumRadius(a, b, c) > radius1) {
                        return true;
                    }
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
        /* 
        * Specification requirements & Edge cases
        */
        if(1 > params.getC_PTS()) { return false; }
        if(1 > params.getD_PTS()) { return false; }
        if((params.getC_PTS() + params.getD_PTS()) > params.getNUMPOINTS() - 3) { return false; }
        if(params.getNUMPOINTS() < 5) { return false; }

        for(int i = 0; i < params.getNUMPOINTS() - (params.getC_PTS() + params.getD_PTS() + 2); i++) {
            int j = i + 1 + params.getC_PTS();
            int k = j + 1 + params.getD_PTS();
            
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[j]; // Vertex
            double y2 = params.getY_PTS()[j]; // Vertex
            double x3 = params.getX_PTS()[k];
            double y3 = params.getY_PTS()[k];

            if((x1 == x2 && y1 == y2) || (x3 == x2 && y3 == y2)) { return false; }

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
      
        /* 
        * Specification requirements & Edge cases
        */
        if(1 > params.getE_PTS()) { return false; }
        if(1 > params.getF_PTS()) { return false; }
        if((params.getE_PTS() + params.getF_PTS()) > params.getNUMPOINTS() - 3) { return false; }
        if(params.getNUMPOINTS() < 5) { return false; }

        for(int i = 0; i < params.getNUMPOINTS() - (params.getE_PTS() + params.getF_PTS() + 2); i++) {
            int j = i + 1 + params.getE_PTS();
            int k = j + 1 + params.getF_PTS();
                
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[j]; // Vertex
            double y2 = params.getY_PTS()[j]; // Vertex
            double x3 = params.getX_PTS()[k];
            double y3 = params.getY_PTS()[k];
                
            //System.out.println((0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))) + " > " + params.getAREA1());
            //System.out.flush(); 
            
            if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > params.getAREA1()) {
                return true;
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
      
        /* 
        * Specification requirements & Edge cases
        */
        if(1 > params.getG_PTS()) { return false; }
        if(params.getG_PTS() > params.getNUMPOINTS() - 2) { return false; }
        if(params.getNUMPOINTS() < 3) { return false; }

      
        for(int i = 0; i < params.getNUMPOINTS() - (params.getG_PTS() + 1); i++) {
            int j = i + 1 + params.getG_PTS();
            System.out.println((params.getX_PTS()[j] - params.getX_PTS()[i]) + " < 0 ");
            System.out.flush(); 
            if(params.getX_PTS()[j] - params.getX_PTS()[i] < 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean LIC12(Parameters param) {
        // Return true if there exists at least one set of two data points,
        // separated by exactly K_PTS consecutive intervening points,
        // which are a distance greater than the length, LENGTH1, apart.
        // In addition, there exists at least one set of two data points 
        // (which can be the same or different from the two data points just mentioned),
        // separated by exactly K_PTS consecutive intervening points,
        // that are a distance less than the length, LENGTH2, apart. 
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 0 ≤ LENGTH2

        int k_pts = param.getK_PTS();
        double length2 = param.getLENGTH2();
        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();

        
        if(numPoints < 3 || (!LIC7(param))) {return false;}
        if(length2 < 0 )return false;
        
        for(int i = 0; i < numPoints - (k_pts + 1); i++) {
            
            if(LICutils.dist( x_pts[i],  y_pts[i],  x_pts[i + k_pts +1],  y_pts[i + k_pts +1]) < length2) {
                return true;
            }
            
        }

        return false;
    }

    public static Boolean LIC13(Parameters param) {
        // Return true if there exists at least one set of three data points,
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // In addition, there exists at least one set of three data points
        // (which can be the same or different from the three data points just mentioned)
        // separated by exactly A_PTS and B_PTS consecutive intervening points, respectively,
        // that can be contained in or on a circle of radius RADIUS2. 
        
        // The condition is not met when NUMPOINTS < 5.
        // 0 ≤ RADIUS2
        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        if(numPoints < 5 || (!LIC8(param))) {return false;}
        if(param.getRADIUS2() < 0 )return false;

        int a_pts= param.getA_PTS();
        int b_pts= param.getB_PTS();
        

        for(int i = 0; i < numPoints - ( a_pts + b_pts+2); i++) {
 
            double x1 = x_pts[i]; double y1 = y_pts[i];
            double x2 = x_pts[i+a_pts+1]; double y2 = y_pts[i+a_pts+1];
            double x3 = x_pts[i+a_pts+b_pts+2]; double y3 = y_pts[i+a_pts+b_pts+2];
    
            double a = LICutils.dist(x1, y1, x2, y2);
            double b = LICutils.dist(x2, y2, x3, y3);
            double c = LICutils.dist(x3, y3, x1, y1);
    
            if(LICutils.circumRadius(a, b, c) <= param.getRADIUS2()) {
                return true;

            }
       }

        return false;
    }

    public static Boolean LIC14(Parameters param) {
        // Return true if there exists at least one set of three data points,
        // separated by exactly E_PTS and F_PTS consecutive intervening points,
        // respectively, that are the vertices of a triangle with area greater than AREA1.
        // In addition, there exist three data points
        // (which can be the same or different from the three data points just mentioned)
        // separated by exactly E_PTS and F_PTS consecutive intervening points, respectively,
        // that are the vertices of a triangle with area less than AREA2.
        // Both parts must be true for the LIC to be true.
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 5.
        // 0 ≤ AREA2
        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();

        if(numPoints < 5 || (!LIC10(param))) {return false;}
        if(param.getAREA2() < 0 )return false;

        int e_pts= param.getE_PTS();
        int f_pts= param.getF_PTS();
        
        for(int i = 0; i < numPoints - (e_pts + f_pts +2); i++) {
            
            double x1 =  x_pts[i];
            double y1 =  y_pts[i];
            double x2 =  x_pts[i+e_pts+1];
            double y2 =  y_pts[i+e_pts+1];
            double x3 =  x_pts[i+e_pts+f_pts+2];
            double y3 =  y_pts[i+e_pts+f_pts+2];

            if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) < param.getAREA2()) {
                return true;
            }

        }
        return false;
    }
    
    public CMV() {
        
    }

    public static boolean[] initCMV(Parameters param) {
      
        boolean[] cmv = new boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i, param);
        }

        return cmv;
    }
}
