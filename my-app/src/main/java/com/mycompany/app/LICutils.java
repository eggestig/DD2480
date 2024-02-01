package com.mycompany.app;

public class LICutils {

    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    public static double distToLine(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x2 - x3) * (y2 - y1) - (x2 - x1) * (y3 - y2)) / dist(x2, y2, x3, y3);
    }

    public static double circumRadius(double a, double b, double c) {
        return (a * b * c) / Math.sqrt((a + b + c) * (b + c - a) * (c + a - b) * (a + b - c));
    }

    public static double dotProduct(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2);
    }

    public static double euclideanNorm(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double getQuadrant(double x, double y) {
        if (x >= 0 && y >= 0) {
            return 1;
        } else if (x < 0 && y >= 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        }
        return 4;
    }
}
