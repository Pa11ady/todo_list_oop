package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.Comparator;

public class ShowAllAction extends AbstractAction {
    public ShowAllAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
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
            case 1 -> todoList.findAll().stream()
                    .sorted(Comparator.comparing(Task::getPriority).reversed())
                    .forEach(out::println);
            case 2 -> todoList.findAll().stream()
                    .sorted(Comparator.comparing(Task::getCreated))
                    .forEach(out::println);
        }
    }
}
