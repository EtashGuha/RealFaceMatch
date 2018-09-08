package com.example.etashguha.realfacematch;

public class GradientDescent {
    double [] Theta, y;
    double [][] X;
    int numIterations;
    double alphaRate;
    public GradientDescent(double [] y, double [][] X, double alphaRate, int numIterations){
        this.Theta =new double [X[0].length + 1];
        for(int i = 0; i < Theta.length; i++){
            Theta[i] = Math.random();
        }
        this.X = X;
        this.y = y;
        this.alphaRate = alphaRate;
        this.numIterations = numIterations;
    }

    public double [] train() {
        int count = 0;
        while (count < numIterations) {
            for (int i = 0; i < Theta.length; i++) {
                for (int j = 0; j < y.length; j++) {
                    double h = 0;
                    for(int k = 0; k < Theta.length; k++){
                        h += Theta[k] * X[j][k];
                    }
                    Theta[i] -= alphaRate * (h/40000 - y[j]/100) * X[j][i];
                    System.out.println("calculating");
                }
            }
            count++;
        }
        return Theta;
    }
}
