package com.ssu.kiselev.task6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Service {

    private double currentTime;

    private final int count;

    private final int queueSize;

    private final int deviceCount;

    private final Queue<Task> tasks;

    private final Queue<Task> serviceQueue;

    private final List<Device> devices;

    private final Queue<Task> resultQueue;

    public Service(int count, int queueSize, int deviceCount, double lambda, double u) {
        this.currentTime = 0;

        this.count = count;
        this.queueSize = queueSize;
        this.deviceCount = deviceCount;

        this.tasks = tasksInit(currentTime, lambda);
        this.serviceQueue = queueServiceInit();
        this.devices = devicesInit(u);
        this.resultQueue = resultQueueInit();
    }

    public void run() {
        while (isContinueWorking()) {
            Task task = tasks.peek();

            double taskTime = getTaskArriveTime(task);
            Map<Double, Device> deviceEndTimes = createDeviceWorkTimeMap();
            double minDeviceEndTime = Collections.min(deviceEndTimes.keySet());

            currentTime = Math.min(taskTime, minDeviceEndTime);
            if (taskTime < minDeviceEndTime) {
                processArriveTaskEvent();
            } else {
                Device device = deviceEndTimes.get(minDeviceEndTime);
                processDeviceResolveTaskEvent(device);
            }
        }

        double timeWorking = 0;
        double queueWaiting = 0;
        for (Task task : resultQueue) {
            timeWorking += task.getFinishResolveTime() - task.getArriveTime();
            queueWaiting += task.getStartResolveTime() - task.getArriveTime();
        }
        int countResolved = resultQueue.size();

        System.out.println("Полное время работы сервиса: " + currentTime);
        System.out.println("Количество: " + resultQueue.size());
        System.out.printf("Срденее время работы: %.10f\n", (timeWorking / countResolved));
        System.out.printf("Среднее время ожидания в очереди: %.10f\n", (queueWaiting / countResolved));
    }

    private Queue<Task> tasksInit(double time, double lambda) {
        if (count != 0) {
            Queue<Task> tasks = new PriorityQueue<>(count);

            for (int i = 0; i < count; i++) {
                time += (-1d / lambda) * Math.log(Math.random());
                Task task = new Task(time);
                tasks.add(task);
            }

            return tasks;
        } else {
            return new PriorityQueue<>();
        }
    }

    private List<Device> devicesInit(double u) {
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            devices.add(new Device(this, u));
        }

        return devices;
    }

    private Queue<Task> queueServiceInit() {
        return new ArrayDeque<>(queueSize);
    }

    private Queue<Task> resultQueueInit() {
        return new ArrayDeque<>(count);
    }

    private boolean isContinueWorking() {
        return !tasks.isEmpty() || !serviceQueue.isEmpty() || devices.stream().anyMatch(device -> !device.isFinish());
    }

    private double getTaskArriveTime(Task task) {
        return task == null ? Double.MAX_VALUE : task.getArriveTime();
    }

    private Map<Double, Device> createDeviceWorkTimeMap() {
        return devices.stream().collect(Collectors.toMap(Device::getEndTime, Function.identity(), (key, value) -> key));
    }

    private void processArriveTaskEvent() {
        Task task = tasks.poll();
        checkServiceQueue(task);

        for (Device device : devices) {
            task = serviceQueue.peek();

            if (task != null && device.isFinish()) {
                completeOldTask(device);
                executeNewTask(device, task);
                task = null;
            }
        }
    }

    private void checkServiceQueue(Task task) {
        serviceQueue.add(task);
    }

    private void processDeviceResolveTaskEvent(Device device) {
        Task task = serviceQueue.peek();

        completeOldTask(device);
        executeNewTask(device, task);
    }

    private void completeOldTask(Device device) {
        Task resolveTask = device.resolveTask();
        if (resolveTask != null) {
            resultQueue.add(resolveTask);
        }
    }

    private void executeNewTask(Device device, Task task) {
        if (task != null) {
            task.setStartResolveTime(currentTime);
            device.processTask(task);
            serviceQueue.remove();
        }
    }

    public double getCurrentTime() {
        return currentTime;
    }
}
