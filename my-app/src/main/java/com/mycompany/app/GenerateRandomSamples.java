package com.mycompany.app;

import java.util.Random;

public class GenerateRandomSamples {
    static Random rand = new Random();

    // Generate a random boolean array of given length
    public static boolean[] generateRandomBooleanArray() {
        boolean[] randomArray = new boolean[15];
        for (int i = 0; i < 15; i++) {
            randomArray[i] = Math.random() < 0.5;
        }
        return randomArray;
    }

    // Generate a random LCM matrix with "ANDD", "ORR", and "NOTUSED"
    public static String[][] generateRandomLCM() {
        String[][] randomLCM = new String[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = i; j < 15; j++) {
                // Generate a random index (0, 1, or 2) for the operators
                int randomIndex = rand.nextInt(3);
                if (randomIndex == 0) {
                    randomLCM[i][j] = "ANDD";
                } else if (randomIndex == 1) {
                    randomLCM[i][j] = "ORR";
                } else {
                    randomLCM[i][j] = "NOTUSED";
                }
                // Set the symmetric element
                randomLCM[j][i] = randomLCM[i][j];
            }
        }
        return randomLCM;
    }

    // Generate a random CMV array with true or false values
    public static boolean[] generateRandomCMV() {
        return generateRandomBooleanArray();
    }
}
