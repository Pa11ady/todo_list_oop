package ru.practicum.todolist.action;

import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class ShowStatisticsAction extends AbstractAction {
    public ShowStatisticsAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        out.println(name() + "Разрабатывается");
    }
}
