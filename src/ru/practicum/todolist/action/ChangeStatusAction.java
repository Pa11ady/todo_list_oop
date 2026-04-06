package ru.practicum.todolist.action;

import ru.practicum.todolist.core.entity.Status;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public class ChangeStatusAction extends AbstractAction {

    public ChangeStatusAction(String name, TaskService taskService, Input input, Output out) {
        super(name, taskService, input, out);
    }

    @Override
    protected void doAction() {
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String menuText = getMenuStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        try {
            taskService.changeStatus(id, Status.fromCode(code));
            out.println("Статус задачи изменён!");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
