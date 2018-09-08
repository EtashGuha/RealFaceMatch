package com.example.etashguha.realfacematch;

public class GradientDescent {
    double [] Theta, y;
    double [][] X;
    int numIterations;
    double alphaRate;
    public GradientDescent(double [] y, double [][] X, double alphaRate, int numIterations){
        this.Theta =new double [X[0].length];
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
                if (i == 16) {
                    System.out.print("");
                }
                for (int j = 0; j < y.length; j++) {
                    double h = 0;
                    for(int k = 0; k < Theta.length; k++){
                        h += Theta[k] * X[j][k];
                    }
                    Theta[i] -= alphaRate * (h/(1000 * 226)- y[j]/100) * X[j][i]/(256^2);
                    if(Double.isNaN(Theta[i])){
                        System.out.println("test");

                    }

                }
            }
            count++;
        }
        return Theta;
    }
}
