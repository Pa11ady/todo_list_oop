package ru.practicum.todolist.action;

import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class CreateAction extends AbstractAction {

    public CreateAction(String name, TaskService taskService, Input input, Output out) {
        super(name, taskService, input, out);
    }

    @Override
    protected void doAction() {
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        taskService.add(name, priority);
        out.println("Задача добавлена!");
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}
