package com.ssu.kiselev.task7;

public class Device {

    private final Service service;

    private Task currentTask;

    private double resolveTime;

    public Device(Service service) {
        this.service = service;
    }

    public void processTask(Task task, double u) {
        this.currentTask = task;
        this.resolveTime = this.currentTask.getStartResolveTime() + (-1d / u) * Math.log(Math.random());
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
