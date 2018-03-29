package com.ssu.kiselev.task7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskManager {

    private Queue<Task> taskQueue;

    private int taskCount;

    private List<Service> services;

    private double currentTime = 0;

    public TaskManager(int taskCount, double lambda, double u) {
        this.taskCount = taskCount;
        this.taskQueue = tasksInit(currentTime, lambda);
        this.services = new ArrayList<>();
    }

    public void addService(Service service) {
        services.add(service);
    }

    private Queue<Task> tasksInit(double time, double lambda) {
        Queue<Task> tasks = new PriorityQueue<>(taskCount);

        for (int i = 0; i < taskCount; i++) {
            time += (-1d / lambda) * Math.log(Math.random());
            Task task = new Task(time);
            tasks.add(task);
        }

        return tasks;
    }

    public void run() {
        while (isContinueWorking()) {
            double arriveTaskTime = Double.MAX_VALUE;

            Task task = taskQueue.peek();
            if (task != null) {
                arriveTaskTime = task.getArriveTime();
            }

            Map<Double, Service> serviceEventsTime = getServiceEventsTime();
            double serviceEvent = Collections.min(serviceEventsTime.keySet());
            currentTime = Math.min(arriveTaskTime, serviceEvent);

            if (arriveTaskTime < serviceEvent) {
                Service service = chooseService();
                service.addTask(taskQueue.poll());
                service.doIteration();
            } else {
                Service service = serviceEventsTime.get(serviceEvent);
                service.doIteration();
            }
        }

        Queue<Task> allResults = new ArrayDeque<>();
        for (Service service : services) {
            allResults.addAll(service.getResultQueue());
        }

        int countRefused = 0;
        double timeWorking = 0;
        double queueWaiting = 0;
        for (Task task : allResults) {
            if (task.isRefuse()) {
                countRefused++;
            } else {
                timeWorking += task.getFinishResolveTime() - task.getArriveTime();
                queueWaiting += task.getStartResolveTime() - task.getArriveTime();
            }
        }
        int countResolved = allResults.size() - countRefused;

        System.out.println("Полное время работы: " + currentTime);
        System.out.println("Количество отмененных: " + countRefused);
        System.out.println("Количество: " + allResults.size());
        System.out.printf("Процент отмененных: %.2f%%\n", ((double) countRefused / allResults.size()) * 100);
        System.out.printf("Среднее время работы: %.10f\n", (timeWorking / countResolved));
        System.out.printf("Среднее время ожидания в очереди: %.10f\n", (queueWaiting / countResolved));
    }

    private boolean isContinueWorking() {
        return !taskQueue.isEmpty() || services.stream()
                .anyMatch((service -> service.getNextEventTime() != Double.MAX_VALUE));
    }

    private Map<Double, Service> getServiceEventsTime() {
        return services.stream()
                .collect(Collectors.toMap(Service::getNextEventTime, Function.identity(), (key, value) -> key));
    }

    private Service chooseService() {
        return services.iterator().next();
    }
}
