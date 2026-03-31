package ru.practicum.todolist.action;

import ru.practicum.todolist.core.entity.Status;
import ru.practicum.todolist.core.entity.Task;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;
import java.util.Map;

public class ShowStatisticsAction extends AbstractAction {
    public ShowStatisticsAction(String name, TaskService taskRepository, Input input, Output out) {
        super(name, taskRepository, input, out);
    }

    @Override
    protected void doAction() {
        out.println("Статистика задач:");
        Map<Status, Long> statsByStatus = taskService.getTaskStatsByStatus();
        statsByStatus.forEach((key, value) -> out.println(key.getName() + ": " + value));

        double avgPriority = taskService.getAveragePriority();
        if (avgPriority > 0) {
            out.println("Средний приоритет: " + String.format("%.2f", avgPriority));
        }

        out.println("Группировка задач по статусам с отображением списка каждой группы");
        Map<Status, List<Task>> tasksByStatus = taskService.getTasksGroupedByStatus();
        tasksByStatus.forEach((key, value) -> {
            out.println(key.getName());
            value.forEach(task -> out.println("\t" + task));
        });
    }
}

