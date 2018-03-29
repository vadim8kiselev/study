package com.ssu.kiselev.task7;

public class Device {

    private final Service service;

    private Task currentTask;

    private double resolveTime;

    private final ExponentialDistribution exponentialDistribution;

    public Device(ExponentialDistribution exponentialDistribution, Service service) {
        this.exponentialDistribution = exponentialDistribution;
        this.service = service;
    }

    public void processTask(Task task) {
        this.currentTask = task;
        this.resolveTime = this.currentTask.getStartResolveTime() + this.exponentialDistribution.getForExecution();
    }

    public Task resolveTask() {
        if (this.currentTask != null) {
            Task task = this.currentTask;
            task.setFinishResolveTime(this.service.getCurrentTime());
            clear();

            return task;
        }

        return null;
    }

    private void clear() {
        this.currentTask = null;
        this.resolveTime = 0;
    }

    public boolean isFinish() {
        return this.currentTask == null || getEndTime() <= this.service.getCurrentTime();
    }

    public double getEndTime() {
        if (this.currentTask == null) {
            return Double.MAX_VALUE;
        }

        return this.resolveTime;
    }
}
