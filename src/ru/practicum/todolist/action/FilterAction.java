package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Status;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class FilterAction extends AbstractAction {
    public FilterAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
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
        todoList.findAll().stream()
                .filter(x -> x.getStatus().equals(status))
                .forEach(out::println);
    }

    private void filterByPriority() {
        out.println("Введите диапазон приоритетов [1 - 10]");
        int minPriority = input.askInt("Минимальный приоритет", 1, 10);
        int maxPriority = input.askInt("Максимальный приоритет", minPriority, 10);
        todoList.findAll().stream()
                .filter(x -> minPriority <= x.getPriority()
                        && x.getPriority() <= maxPriority)
                .forEach(out::println);
    }
}
