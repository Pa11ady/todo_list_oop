package ru.practicum.todolist.action;

import ru.practicum.todolist.core.entity.Status;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class FilterAction extends AbstractAction {
    public FilterAction(String name, TaskService taskRepository, Input input, Output out) {
        super(name, taskRepository, input, out);
    }

    @Override
    protected void doAction() {
        out.println("Выберите фильтр для задач");
        String menuText = """
                1. Фильтровать по статусу
                2. Фильтровать по приоритету.
                Введите число""";
        int code = input.askInt(menuText, 1, 2);
        switch (code) {
            case 1 -> filterByStatus();
            case 2 -> filterByPriority();
        }
    }

    private void filterByStatus() {
        String menuText = getMenuStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        Status status = Status.fromCode(code);
        taskService.filterByStatus(status).forEach(out::println);
    }

    

    private void filterByPriority() {
        out.println("Введите диапазон приоритетов [1 - 10]");
        int minPriority = input.askInt("Минимальный приоритет", 1, 10);
        int maxPriority = input.askInt("Максимальный приоритет", minPriority, 10);
        taskService.filterByPriority(minPriority, maxPriority).forEach(out::println);
    }
}
