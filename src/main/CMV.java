package main;

import java.util.HashSet;
import static main.LICutils.*;

public class CMV {

    private static int numPoints;
    private static Parameters param;
    private static int[] x_pts;
    private static int[] y_pts;

    private static Boolean evalLIC(int LIC_num) {

        switch(LIC_num) {
            case(0):
                return LIC0();
            case(1):
                return LIC1();
            case(2):
                return LIC2();
            case(3):
                return LIC3();
            case(4):
                return LIC4();
            case(5):
                return LIC5();
            case(6):
                return LIC6();
            case(7):
                return LIC7();
            case(8):
                return LIC8();
            case(9):
                return LIC9();
            case(10):
                return LIC10();
            case(11):
                return LIC11();
            case(12):
                return LIC12();
            case(13):
                return LIC13();
            case(14):
                return LIC14();

            default:
                return false;
            }
    }

    private static Boolean LIC0() {
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                if(dist(x_pts[i], y_pts[i], x_pts[j], y_pts[j]) > param.LENGTH1) {
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
            
                    Double a = dist(x1, y1, x2, y2);
                    Double b = dist(x2, y2, x3, y3);
                    Double c = dist(x3, y3, x1, y1);
            
                    if(circumRadius(a, b, c) > param.RADIUS1) {
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
            
                    Double n1 = euclideanNorm(x1, y1, x2, y2);
                    Double n2 = euclideanNorm(x2, y2, x3, y3);
            
                    // Calculate the cosine of the angle between the vectors
                    Double cosineAngle = dotProduct(x1, y1, x2, y2, x3, y3) / (n1 * n2);
            
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

    private static Boolean LIC3() {
        // Return true if there exists at least one set of three consecutive data points
        // that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        // (0 ≤ AREA1)

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                for(int k = j + 1; k < numPoints; k++) {
                
                    int x1 = x_pts[i];
                    int y1 = y_pts[i];
                    int x2 = x_pts[j];
                    int y2 = y_pts[j];
                    int x3 = x_pts[k];
                    int y3 = y_pts[k];

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
                int quadrant = getQuadrant(x, y);

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
                if(distToLine(x_pts[j], y_pts[j], x_pts[i], y_pts[i], x_pts[i + param.N_PTS], y_pts[i + param.N_PTS]) > param.DIST) {
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
            if(dist(x_pts[i], y_pts[i], x_pts[i + param.K_PTS + 1], y_pts[i + param.K_PTS + 1]) > param.LENGTH1) {
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
            
                    Double a = dist(x1, y1, x2, y2);
                    Double b = dist(x2, y2, x3, y3);
                    Double c = dist(x3, y3, x1, y1);
            
                    if(circumRadius(a, b, c) > param.RADIUS1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static Boolean LIC9() {
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

        for(int i = 0; i < numPoints - (param.C_PTS + param.D_PTS); i++) {
            for(int j = i + param.C_PTS; j < numPoints - param.D_PTS; j++) {
                for(int k = j + param.D_PTS; k < numPoints; k++) {
                    
                    int x1 = x_pts[i];
                    int y1 = y_pts[i];
                    int x2 = x_pts[j]; // Vertex
                    int y2 = y_pts[j]; // Vertex
                    int x3 = x_pts[k];
                    int y3 = y_pts[k];
            
                    Double n1 = euclideanNorm(x1, y1, x2, y2);
                    Double n2 = euclideanNorm(x2, y2, x3, y3);
            
                    // Calculate the cosine of the angle between the vectors
                    Double cosineAngle = dotProduct(x1, y1, x2, y2, x3, y3) / (n1 * n2);
            
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

    private static Boolean LIC10() {
        // Return true if there exists at least one set of three data points
        // separated by exactly E_PTS and F_PTS consecutive intervening points,
        // respectively, that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ E_PTS, 1 ≤ F_PTS
        // E_PTS + F_PTS ≤ NUMPOINTS − 3

        if(x_pts.length < 5 || y_pts.length < 5) {return false;}

        for(int i = 0; i < numPoints + (param.E_PTS + param.F_PTS); i++) {
            for(int j = i + param.E_PTS; j < numPoints - param.F_PTS; j++) {
                for(int k = j + param.F_PTS; k < numPoints; k++) {
                
                    int x1 = x_pts[i];
                    int y1 = y_pts[i];
                    int x2 = x_pts[j];
                    int y2 = y_pts[j];
                    int x3 = x_pts[k];
                    int y3 = y_pts[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > param.AREA1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static Boolean LIC11() {
        // Return true if there exists at least one set of two data points,
        // (X[i],Y[i]) and (X[j],Y[j]), separated by exactly G_PTS
        // consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ G_PTS ≤ NUMPOINTS−2

        if(x_pts.length < 3 || y_pts.length < 3) {return false;}

        for(int i = 0; i < numPoints - param.G_PTS; i++) {
            for(int j = i + param.G_PTS; j < numPoints; j++) {
                if(x_pts[j] - x_pts[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC12() {
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

        if(x_pts.length < 3 || y_pts.length < 3 || Boolean.TRUE.equals(!LIC7())) {return false;}
        
        for(int i = 0; i < numPoints - param.K_PTS - 1; i++) {
            if(dist(x_pts[i], y_pts[i], x_pts[i + param.K_PTS + 1], y_pts[i + param.K_PTS + 1]) < param.LENGTH2) {
                return true;
            }
        }

        return false;
    }

    private static Boolean LIC13() {
        // Return true if there exists at least one set of three data points,
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // In addition, there exists at least one set of three data points
        // (which can be the same or different from the three data points just mentioned)
        // separated by exactly A_PTS and B_PTS consecutive intervening points, respectively,
        // that can be contained in or on a circle of radius RADIUS2. 
        
        // The condition is not met when NUMPOINTS < 5.
        // 0 ≤ RADIUS2

        if(x_pts.length < 5 || y_pts.length < 5 || Boolean.TRUE.equals(!LIC8())) {return false;}

        for(int i = 0; i < numPoints - (param.A_PTS + param.B_PTS); i++) {
            for(int j = i + param.A_PTS; j < numPoints - param.B_PTS; j++) {
                for(int k = j + param.B_PTS; k < numPoints; k++) {

                    int x1 = x_pts[i]; int y1 = y_pts[i];
                    int x2 = x_pts[j]; int y2 = y_pts[j];
                    int x3 = x_pts[k]; int y3 = y_pts[k];
            
                    Double a = dist(x1, y1, x2, y2);
                    Double b = dist(x2, y2, x3, y3);
                    Double c = dist(x3, y3, x1, y1);
            
                    if(circumRadius(a, b, c) <= param.RADIUS2) {
                        return true;
                    }
                }
            }
       }

        return false;
    }

    private static Boolean LIC14() {
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

        if(x_pts.length < 5 || y_pts.length < 5 || Boolean.TRUE.equals(!LIC10())) {return false;}

        for(int i = 0; i < numPoints + (param.E_PTS + param.F_PTS); i++) {
            for(int j = i + param.E_PTS; j < numPoints - param.F_PTS; j++) {
                for(int k = j + param.F_PTS; k < numPoints; k++) {
                
                    int x1 = x_pts[i];
                    int y1 = y_pts[i];
                    int x2 = x_pts[j];
                    int y2 = y_pts[j];
                    int x3 = x_pts[k];
                    int y3 = y_pts[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) < param.AREA2) {
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