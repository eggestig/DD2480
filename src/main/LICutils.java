package main;


public class LICutils {

    public static Double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    public static Double distToLine(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x2 - x3) * (y2 - y1) - (x2 - x1) * (y3 - y2)) / dist(x2, y2, x3, y3);
    }

    public static Double circumRadius(Double a, Double b, Double c) {
        return (a * b * c) / Math.sqrt((a + b + c) * (b + c - a) * (c + a - b) * (a + b - c));
    }

    public static int dotProduct(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2);
    }

    public static Double euclideanNorm(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static int getQuadrant(int x, int y) {
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
