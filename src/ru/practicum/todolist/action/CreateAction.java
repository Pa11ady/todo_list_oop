package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class CreateAction extends AbstractAction {
    public CreateAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        todoList.add(new Task(name, priority));
        out.println("Задача добавлена!");
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}
