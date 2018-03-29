package com.ssu.kiselev.task7;

public class Task implements Comparable<Task> {

    private double arriveTime;

    private double startResolveTime;

    private double finishResolveTime;

    private boolean isRefuse = false;

    public Task(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    public void setStartResolveTime(double startResolveTime) {
        this.startResolveTime = startResolveTime;
    }

    public double getStartResolveTime() {
        return startResolveTime;
    }

    public void setFinishResolveTime(double finishResolveTime) {
        this.finishResolveTime = finishResolveTime;
    }

    public double getFinishResolveTime() {
        return finishResolveTime;
    }

    public void refuse() {
        isRefuse = true;
        startResolveTime = Double.POSITIVE_INFINITY;
        finishResolveTime = Double.POSITIVE_INFINITY;
    }

    public boolean isRefuse() {
        return isRefuse;
    }

    @Override
    public int compareTo(Task task) {
        return Double.compare(this.getArriveTime(), task.getArriveTime());
    }
}
