package ru.practicum.todolist.core.service;

import ru.practicum.todolist.core.entity.Status;
import ru.practicum.todolist.core.entity.Task;
import ru.practicum.todolist.core.exception.NotFoundException;
import ru.practicum.todolist.core.exception.TaskDeleteException;
import ru.practicum.todolist.core.repository.TaskRepository;

import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean hasTasks() {
        return !taskRepository.isEmpty();
    }

    public void changeStatus(int id, Status status) {
        taskRepository.find(id).ifPresentOrElse(
                task -> task.setStatus(status),
                () -> {
                    throw new NotFoundException("Задача с  id " + id + " не найдена");
                }
        );
    }

    public void add(String name, int priority) {
        taskRepository.create(new Task(name, priority));
    }

    public void delete(int id) {
        taskRepository.find(id).orElseThrow(
                () -> new NotFoundException("Задача с  id " + id + " не найдена"));
        if (!taskRepository.delete(id)) {
            throw new TaskDeleteException("Задача с  id " + id + " не удалена");
        }
    }

    public void edit(int id, String name, int priority) {
        taskRepository.find(id).ifPresentOrElse(
                task -> {
                    task.setName(name);
                    task.setPriority(priority);
                    taskRepository.update(task);
                },
                () -> {
                    throw new NotFoundException("Задача с  id " + id + " не найдена");
                }
        );
    }

    // Можно выделить фильтр в параметр, но код в одну строку смысла не вижу
    public List<Task> filterByStatus(Status status) {
        return taskRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(status))
                .toList();
    }

    public List<Task> filterByPriority(int minPriority, int maxPriority) {
        return taskRepository.findAll().stream()
                .filter(x -> minPriority <= x.getPriority()
                        && x.getPriority() <= maxPriority)
                .toList();
    }

    public List<Task> findByKeyword(String word) {
        return taskRepository.findByKeyword(word);
    }

    public List<Task> findAllSortByPriority() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .toList();
    }

    public List<Task> findAllSortByCreated() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getCreated))
                .toList();
    }

    public Map<Status, Long> getTaskStatsByStatus() {
        Map<Status, Long> groupByStatus = taskRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
        Arrays.stream(Status.values()).forEach(status -> groupByStatus.putIfAbsent(status, 0L));
        return groupByStatus;
    }

    public double getAveragePriority() {
        return taskRepository.findAll().stream()
                .mapToInt(Task::getPriority)
                .average().orElse(0.0);
    }

    public Map<Status, List<Task>> getTasksGroupedByStatus() {
        Map<Status, List<Task>> tasksByStatus = taskRepository.findAll().stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        Arrays.stream(Status.values())
                .forEach(status -> tasksByStatus.putIfAbsent(status, new ArrayList<>()));
        return tasksByStatus;
    }
}
