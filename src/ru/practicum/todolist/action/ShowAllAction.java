package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;

public class ShowAllAction extends AbstractAction {
    public ShowAllAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        List<Task> tasks = todoList.findAll();
        for (Task task : tasks) {
            out.println(task);
        }
    }
}
