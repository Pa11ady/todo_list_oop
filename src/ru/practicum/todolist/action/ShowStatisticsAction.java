package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Status;
import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowStatisticsAction extends AbstractAction {
    public ShowStatisticsAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        out.println("Статистика задач:");
        List<Task> tasks = todoList.findAll();
        Map<Status, Long> groupByStatus = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
        Arrays.stream(Status.values()).forEach(status -> groupByStatus.putIfAbsent(status, 0L));
        groupByStatus.forEach((key, value) -> out.println(key.getName() + ": " + value));

        tasks.stream()
                .mapToInt(Task::getPriority)
                .average()
                .ifPresent(x -> out.println("Средний приоритет:" + String.format("%.2f", x)));
        out.println("Группировка задач по статусам с отображением списка каждой группы");
        Map<Status, List<Task>> tasksByStatus = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        Arrays.stream(Status.values())
                .forEach(status -> tasksByStatus.putIfAbsent(status, new ArrayList<>()));

        tasksByStatus.forEach((key, value) -> {
           out.println(key.getName());
           value.forEach(task -> out.println("\t" + task));
        });
    }
}
