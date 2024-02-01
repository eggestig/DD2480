package com.mycompany.app;

public class PUM extends GenerateRandomSamples {


    private boolean[][] matrix;
    private CONNECTORS[][] LCM;
    private boolean[] CMV;

    // Constructor
    public PUM() {
        // Initialize a 15x15 matrix
        matrix = new boolean[15][15];
    }

    public boolean[][] getPUM() {
        return matrix;
    }

    public void receive_LCM(CONNECTORS[][] lcm) {
        if (lcm.length != 15 || lcm[0].length != 15) {
            // Throw an exception or handle the error as needed
            throw new IllegalArgumentException("Invalid LCM dimensions. Should be 15x15.");
        }

        // Copy the contents of lcm to LCM
        LCM = new CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            System.arraycopy(lcm[i], 0, LCM[i], 0, 15);
        }

    }

    public void receive_CMV(boolean[] cmv){
        if (cmv.length != 15) {
            // Throw an exception or handle the error as needed
            throw new IllegalArgumentException("Invalid CMV length. Should be 15.");
        }

        // Copy the contents of cmv to CMV
        CMV = new boolean[15];
        System.arraycopy(cmv, 0, CMV, 0, 15);
    }

    public void generate_PUM() {
        if (LCM == null || CMV == null) {
            // Throw an exception or handle the error as needed
            throw new IllegalStateException("LCM or CMV is not initialized.");
        }

        // Initialize a 15x15 matrix for PUM
        matrix = new boolean[15][15];

        // Iterate over the matrix and compute PUM values
        for (int i = 0; i < 15; i++) {
            for (int j = i; j < 15; j++) {
                // If LCM[i,j] is NOTUSED, set PUM[i,j] to true
                if (LCM[i][j].equals(CONNECTORS.NOTUSED)) {
                    matrix[i][j] = true;
                    matrix[j][i] = true;
                } else {
                    // Compute PUM[i,j] based on the boolean operator in LCM[i,j]
                    boolean result = computeResult(CMV[i], CMV[j], LCM[i][j]);
                    matrix[i][j] = result;
                    matrix[j][i] = result;
                }
            }
        }
    }

    // Helper method to compute the result based on the boolean operator
    private boolean computeResult(boolean operand1, boolean operand2, CONNECTORS operator) {
        switch (operator) {
            case ANDD:
                return operand1 && operand2;
            case ORR:
                return operand1 || operand2;
            default:
                // Throw an exception or handle the error as needed for unknown operators
                throw new IllegalArgumentException("Invalid boolean operator: " + operator);
        }
    }

    //display PUM  --Data in diagonal will display with * instead of real value(doesn't matter)
    public void displayPUM() {
        if (matrix == null) {
            // Throw an exception or handle the error as needed
            throw new IllegalStateException("PUM matrix is not initialized.");
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i==j) {
                    System.out.print("|*    |");
                    continue;
                }
                System.out.print(matrix[i][j] ? "|true |" : "|false|");
            }
            System.out.println();
        }
    }

    //Need to change it to string version, because it have 3 state but boolean only have true or false
    public String[][] getStringPUM() {
        if (matrix == null) {
            // Throw an exception or handle the error as needed
            throw new IllegalStateException("PUM matrix is not initialized.");
        }

        String[][] stringPUM = new String[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i==j) {
                    stringPUM[i][j] = "*";
                    continue;
                }
                if (matrix[i][j]) {
                    stringPUM[i][j] = "true";
                } else {
                    stringPUM[i][j] = "false";
                }
            }
        }

        return stringPUM;
    }
}
