package com.ssu.kiselev.task6;

public class Task implements Comparable<Task> {

    private final double arriveTime;

    private double startResolveTime;

    private double finishResolveTime;

    public Task(double arriveTime) {
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

    @Override
    public int compareTo(Task task) {
        return Double.compare(this.getArriveTime(), task.getArriveTime());
    }
}
