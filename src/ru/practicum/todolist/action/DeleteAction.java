package ru.practicum.todolist.action;

import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class DeleteAction extends AbstractAction {
    public DeleteAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        try {
            todoList.delete(id);
            out.println("Задача удалена успешно.");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
