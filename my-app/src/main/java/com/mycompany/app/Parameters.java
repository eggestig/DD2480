package com.mycompany.app;

public class Parameters {
    private int NUMPOINTS;
    private double[] X_PTS;
    private double[] Y_PTS;
    private double LENGTH1;
    private double RADIUS1;
    private double EPSILON;
    private double AREA1;
    private int Q_PTS;
    private int QUADS;
    private double DIST;
    private int N_PTS;
    private int K_PTS;
    private int A_PTS;
    private int B_PTS;
    private int C_PTS;
    private int D_PTS;
    private int E_PTS;
    private int F_PTS;
    private int G_PTS;
    private double LENGTH2;
    private double RADIUS2;
    private double AREA2;
    public final double PI = 3.1415926535;
    //public enum CONNECTORS { NOTUSED, ORR, AAND };
    public enum CONNECTORS_TEST { NOTUSED, ORR, AAND };
    public enum COMPTYPE { LT, EQ, GT } ;

    public void setNUMPOINTS(int NUMPOINTS) { this.NUMPOINTS = NUMPOINTS; }
    public void setX_PTS(double[] X_PTS) { this.X_PTS = X_PTS; }
    public void setY_PTS(double[] Y_PTS) { this.Y_PTS = Y_PTS; }
    public void setLENGTH1(double LENGTH1) { this.LENGTH1 = LENGTH1; }
    public void setRADIUS1(double RADIUS1) { this.RADIUS1 = RADIUS1; }
    public void setEPSILON(double EPSILON) { this.EPSILON = EPSILON; }
    public void setAREA1(double AREA1) { this.AREA1 = AREA1; }
    public void setQ_PTS(int Q_PTS) { this.Q_PTS = Q_PTS; }
    public void setQUADS(int QUADS) { this.QUADS = QUADS; }
    public void setDIST(double DIST) { this.DIST = DIST; ;}
    public void setN_PTS(int N_PTS) { this.N_PTS = N_PTS; }
    public void setK_PTS(int K_PTS) { this.K_PTS = K_PTS; }
    public void setA_PTS(int A_PTS) { this.A_PTS = A_PTS; }
    public void setB_PTS(int B_PTS) { this.B_PTS = B_PTS; }
    public void setC_PTS(int C_PTS) { this.C_PTS = C_PTS; }
    public void setD_PTS(int D_PTS) { this.D_PTS = D_PTS; }
    public void setE_PTS(int E_PTS) { this.E_PTS = E_PTS; }
    public void setF_PTS(int F_PTS) { this.F_PTS = F_PTS; }
    public void setG_PTS(int G_PTS) { this.G_PTS = G_PTS; }
    public void setLENGTH2(double LENGTH2) { this.LENGTH2 = LENGTH2; }
    public void setRADIUS2(double RADIUS2) { this.RADIUS2 = RADIUS2; }
    public void setAREA2(double AREA2) { this.AREA2 = AREA2; }

    public int getNUMPOINTS() { return this.NUMPOINTS; }
    public double[] getX_PTS() { return this.X_PTS; }
    public double[] getY_PTS() { return this.Y_PTS; }
    public double getLENGTH1() { return this.LENGTH1; }
    public double getRADIUS1() { return this.RADIUS1; }
    public double getEPSILON() { return this.EPSILON; }
    public double getAREA1() { return this.AREA1; }
    public int getQ_PTS() { return this.Q_PTS; }
    public int getQUADS() { return this.QUADS; }
    public double getDIST() { return this.DIST; }
    public int getN_PTS() { return this.N_PTS; }
    public int getK_PTS() { return this.K_PTS; }
    public int getA_PTS() { return this.A_PTS; }
    public int getB_PTS() { return this.B_PTS; }
    public int getC_PTS() { return this.C_PTS; }
    public int getD_PTS() { return this.D_PTS; }
    public int getE_PTS() { return this.E_PTS; }
    public int getF_PTS() { return this.F_PTS; }
    public int getG_PTS() { return this.G_PTS; }
    public double getLENGTH2() { return this.LENGTH2; }
    public double getRADIUS2() { return this.RADIUS2; }
    public double getAREA2() { return this.AREA2; }

    public Parameters deepCopy() {
        return new Parameters(
            this.NUMPOINTS,
            this.X_PTS,
            this.Y_PTS,
            this.LENGTH1,
            this.RADIUS1,
            this.EPSILON,
            this.AREA1,
            this.Q_PTS,
            this.QUADS,
            this.DIST,
            this.N_PTS,
            this.K_PTS,
            this.A_PTS,
            this.B_PTS,
            this.C_PTS,
            this.D_PTS,
            this.E_PTS,
            this.F_PTS,
            this.G_PTS,
            this.LENGTH2,
            this.RADIUS2,
            this.AREA2
        );
    }

    public Parameters() {
        this.NUMPOINTS = 0;
        this.X_PTS = new double[0];
        this.Y_PTS = new double[0];
        this.LENGTH1 = 0.0;
        this.RADIUS1 = 0.0;
        this.EPSILON = 0.0;
        this.AREA1 = 0.0;
        this.Q_PTS = 0;
        this.QUADS = 0;
        this.DIST = 0.0;
        this.N_PTS = 0;
        this.K_PTS = 0;
        this.A_PTS = 0;
        this.B_PTS = 0;
        this.C_PTS = 0;
        this.D_PTS = 0;
        this.E_PTS = 0;
        this.F_PTS = 0;
        this.G_PTS = 0;
        this.LENGTH2 = 0.0;
        this.RADIUS2 = 0.0;
        this.AREA2 = 0.0;
    }

    public Parameters(
        int NUMPOINTS,
        double[] X_PTS,
        double[] Y_PTS,
        double LENGTH1,
        double RADIUS1,
        double EPSILON,
        double AREA1,
        int Q_PTS,
        int QUADS,
        double DIST,
        int N_PTS,
        int K_PTS,
        int A_PTS,
        int B_PTS,
        int C_PTS,
        int D_PTS,
        int E_PTS,
        int F_PTS,
        int G_PTS,
        double LENGTH2,
        double RADIUS2,
        double AREA2) {
        this.NUMPOINTS = NUMPOINTS;
        this.X_PTS = X_PTS;
        this.Y_PTS = Y_PTS;
        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.G_PTS = G_PTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }
}