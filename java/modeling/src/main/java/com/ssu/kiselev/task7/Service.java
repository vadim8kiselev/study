package com.ssu.kiselev.task7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Service {

    private double currentTime;

    private final int count;

    private final int queueSize;

    private final int deviceCount;

    private final ExponentialDistribution exponentialDistribution;

    private final Queue<Task> tasks;

    private final Queue<Task> serviceQueue;

    private final List<Device> devices;

    private final Queue<Task> resultQueue;

    private final Service nextService;

    public Service(Service nextService, int queueSize, int deviceCount, double lambda, double u) {
        this.currentTime = 0;

        this.nextService = nextService;

        this.count = 0;
        this.queueSize = queueSize;
        this.deviceCount = deviceCount;

        this.exponentialDistribution = new ExponentialDistribution(lambda, u);

        this.tasks = tasksInit(currentTime);
        this.serviceQueue = queueServiceInit();
        this.devices = devicesInit();
        this.resultQueue = resultQueueInit();
    }

    public Queue<Task> getResultQueue() {
        return resultQueue;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    private Queue<Task> tasksInit(double time) {
        if (count != 0) {
            Queue<Task> tasks = new PriorityQueue<>(count);

            for (int i = 0; i < count; i++) {
                time += exponentialDistribution.getForArrive();
                Task task = new Task(time);
                tasks.add(task);
            }

            return tasks;
        } else {
            return new PriorityQueue<>();
        }
    }

    private List<Device> devicesInit() {
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < deviceCount; i++) {
            devices.add(new Device(exponentialDistribution, this));
        }

        return devices;
    }

    private Queue<Task> queueServiceInit() {
        return new ArrayDeque<>(queueSize);
    }

    private Queue<Task> resultQueueInit() {
        return new ArrayDeque<>(count);
    }

    public void doIteration() {
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
        if (!isEmptyQueue()) {
            checkServiceQueue(task);
        }

        for (Device device : devices) {
            if (!isEmptyQueue()) {
                task = serviceQueue.peek();
            }

            if (task != null && device.isFinish()) {
                completeOldTask(device);
                executeNewTask(device, task);
                task = null;
            }
        }

        if (isEmptyQueue() && task != null) {
            refuseTask(task);
        }
    }

    private void checkServiceQueue(Task task) {
        if (serviceQueue.size() == queueSize) {
            refuseTask(task);
        } else {
            serviceQueue.add(task);
        }
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

            // update arrive time
            resolveTask.setArriveTime(0.0); // порчу предыдущую
            // push to next service
            if (nextService != null) {
                nextService.addTask(resolveTask);
            }
        }
    }

    private void executeNewTask(Device device, Task task) {
        if (task != null) {
            task.setStartResolveTime(currentTime);
            device.processTask(task);
            if (!isEmptyQueue()) {
                serviceQueue.remove();
            }
        }
    }

    public double getCurrentTime() {
        return currentTime;
    }

    private boolean isEmptyQueue() {
        return queueSize == 0;
    }

    private void refuseTask(Task task) {
        task.refuse();
        resultQueue.add(task);
    }

    public double getNextEventTime() {
        double arriveTime = Double.MAX_VALUE;
        Task peek = tasks.peek();
        if (peek != null) {
            arriveTime = peek.getArriveTime();
        }

        Map<Double, Device> deviceWorkTimeMap = createDeviceWorkTimeMap();
        Double deviceEventTime = Collections.min(deviceWorkTimeMap.keySet());

        return Math.min(arriveTime, deviceEventTime);
    }
}
