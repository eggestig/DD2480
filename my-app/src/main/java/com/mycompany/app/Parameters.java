package com.mycompany.app;


public class Parameters {

    public final Double LENGTH1;
    public final Double RADIUS1;
    public final Double EPSILON;
    public final Double AREA1;
    public final int Q_PTS;
    public final int QUADS;
    public final Double DIST;
    public final int N_PTS;
    public final int K_PTS;
    public final int A_PTS;
    public final int B_PTS;
    public final int C_PTS;
    public final int D_PTS;
    public final int E_PTS;
    public final int F_PTS;
    public final int G_PTS;
    public final Double LENGTH2;
    public final Double RADIUS2;
    public final Double AREA2;

    public Parameters(
            Double length1,
            Double radius1,
            Double epsilon,
            Double area1,
            int q_pts,
            int quads,
            Double dist,
            int n_pts,
            int k_pts,
            int a_pts,
            int b_pts,
            int c_pts,
            int d_pts,
            int e_pts,
            int f_pts,
            int g_pts,
            Double length2,
            Double radius2,
            Double area2
            ) {

        this.LENGTH1 = length1;
        this.RADIUS1 = radius1;
        this.EPSILON = epsilon;
        this.AREA1 = area1;
        this.Q_PTS = q_pts;
        this.QUADS = quads;
        this.DIST = dist;
        this.N_PTS = n_pts;
        this.K_PTS = k_pts;
        this.A_PTS = a_pts;
        this.B_PTS = b_pts;
        this.C_PTS = c_pts;
        this.D_PTS = d_pts;
        this.E_PTS = e_pts;
        this.F_PTS = f_pts;
        this.G_PTS = g_pts;
        this.LENGTH2 = length2;
        this.RADIUS2 = radius2;
        this.AREA2 = area2;

    }
}