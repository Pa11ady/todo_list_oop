package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Status;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class ChangeStatusAction extends AbstractAction {
    public ChangeStatusAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String menuText = getMenuStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        todoList.find(id).ifPresentOrElse(
                task -> {
                    task.setStatus(Status.fromCode(code));
                    out.println("Статус задачи изменен.");
                },
                () -> out.println("Задача не найдена.")
        );
    }
}
