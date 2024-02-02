package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputParser {
    private Parameters params;
    private CONNECTORS[][] LCM;
    private boolean[] PUV;

    final String path;

    public InputParser(String path) {
        this.path = path;
    }

    public void Parse() {
        File file = new File(path);
        try {
			Scanner scanner = new Scanner(file);

            int NUMPOINTS =  scanner.nextInt();     
            System.out.println("NUM POINTS: " + NUMPOINTS);

            double[] X_PTS = new double[NUMPOINTS];
            double[] Y_PTS  = new double[NUMPOINTS];


            for (int i = 0; i < NUMPOINTS; i++) {
                if (scanner.hasNextDouble()) {
                    X_PTS[i] = scanner.nextDouble();
                }
            } 

            for (int i = 0; i < NUMPOINTS; i++) {
                if (scanner.hasNextDouble()) {
                    Y_PTS[i] = scanner.nextDouble();
                }
            } 


            double LENGTH1 = scanner.nextDouble();       
            double RADIUS1 = scanner.nextDouble();                 
            double EPSILON = scanner.nextDouble();                 
            double AREA1 = scanner.nextDouble();               
            int Q_PTS = scanner.nextInt();                 
            int QUADS= scanner.nextInt();               
            double DIST= scanner.nextDouble();

            int N_PTS = scanner.nextInt(); 
               
            int K_PTS = scanner.nextInt();
            int A_PTS = scanner.nextInt();
            int B_PTS = scanner.nextInt();
            int C_PTS = scanner.nextInt();
            int D_PTS = scanner.nextInt();
            int E_PTS = scanner.nextInt();
            int F_PTS = scanner.nextInt();
            int G_PTS = scanner.nextInt();
            
            double LENGTH2 = scanner.nextDouble();
            double RADIUS2 = scanner.nextDouble();
            double AREA2 = scanner.nextDouble();
            System.out.println("AREA2: " + AREA2);
               
            Parameters params = new Parameters(
                NUMPOINTS,
                X_PTS,
                Y_PTS,
                LENGTH1,
                RADIUS1,
                EPSILON,
                AREA1,
                Q_PTS,
                QUADS,
                DIST,
                N_PTS,
                K_PTS,
                A_PTS,
                B_PTS,
                C_PTS,
                D_PTS,
                E_PTS,
                F_PTS,
                G_PTS,
                LENGTH2,
                RADIUS2,
                AREA2);

            CONNECTORS[][] LCM = new CONNECTORS[15][15];
            boolean[] PUV = new boolean[15];

            // Ghost line
            scanner.nextLine();
            for (int i = 0; i < LCM.length; i++) {
                String line = scanner.nextLine();                
                String[] splitLine = line.split(" ");

                for (int j = 0; j < splitLine.length; j++) {
                    LCM[i][j] = CONNECTORS.valueOf(splitLine[j]);
                }
            }

            for (int i=0; i<15;i++) {
                PUV[i] = scanner.nextBoolean();
            }

			scanner.close();

            this.params = params;
            this.LCM = LCM;
            this.PUV = PUV;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public CONNECTORS[][] getLCM() {
        return LCM;
    }

    public boolean[] getPUV() {
        return PUV;
    }

    public Parameters getParameters() {
        return params;
    }
}
