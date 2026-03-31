package ru.practicum.todolist.action;

import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class EditAction extends AbstractAction {
    public EditAction(String name, TaskService taskService, Input input, Output out) {
        super(name, taskService, input, out);
    }

    @Override
    protected void doAction() {
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        try {
            taskService.edit(id, name, priority);
            out.println("Задача изменёна!");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
