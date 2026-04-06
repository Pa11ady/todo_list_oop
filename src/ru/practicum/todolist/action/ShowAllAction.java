package ru.practicum.todolist.action;

import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class ShowAllAction extends AbstractAction {
    public ShowAllAction(String name, TaskService taskRepository, Input input, Output out) {
        super(name, taskRepository, input, out);
    }

    @Override
    protected void doAction() {
        out.println("Выберите сортировку для задач");
        String menuText = """
                1. Сортировка по приоритету (по убыванию)
                2. Сортировкой по дате создания.
                Введите число""";
        int code = input.askInt(menuText, 1, 2);
        switch (code) {
            case 1 ->taskService.findAllSortByPriority()
                    .forEach(out::println);
            case 2 -> taskService.findAllSortByCreated()
                    .forEach(out::println);
        }
    }
}
