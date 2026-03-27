package ru.practicum.todolist.action;

import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class EditAction extends AbstractAction {
    public EditAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        todoList.find(id).ifPresentOrElse(
                task -> {
                    task.setName(name);
                    task.setPriority(priority);
                    todoList.update(task);
                    out.println("Задача изменена.");
                },
                () -> out.println("Задача не найдена.")
        );
    }
}
