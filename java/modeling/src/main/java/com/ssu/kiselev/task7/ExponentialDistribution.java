package com.ssu.kiselev.task7;

public class ExponentialDistribution {

    private final double lambda;

    private final double u;

    public ExponentialDistribution(double lambda, double u) {
        this.lambda = lambda;
        this.u = u;
    }

    public double getForArrive() {
        return getRandom(this.lambda);
    }

    public double getForExecution() {
        return getRandom(this.u);
    }

    private double getRandom(double value) {
        return (-1d / value) * Math.log(Math.random());
    }
}
