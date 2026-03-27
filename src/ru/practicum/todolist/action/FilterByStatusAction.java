package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Status;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class FilterByStatusAction extends AbstractAction {
    public FilterByStatusAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        String menuText = getStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        Status status = Status.fromCode(code);
        out.println("Введите диапазон приоритетов [1 - 10]");
        int minPriority = input.askInt("Минимальный приоритет", 1, 10);
        int maxPriority = input.askInt("Максимальный приоритет", minPriority, 10);
        todoList.findAll().stream()
                .filter(x -> x.getStatus().equals(status) && minPriority <=x.getPriority()
                        && x.getPriority() <= maxPriority)
                .forEach(out::println);
    }
}
