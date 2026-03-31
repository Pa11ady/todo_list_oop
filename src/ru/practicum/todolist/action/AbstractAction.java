package ru.practicum.todolist.action;

import ru.practicum.todolist.core.entity.Status;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public abstract class AbstractAction implements UserAction {
    private final String name;
    protected final TaskService taskService;
    protected final Input input;
    protected final Output out;

    public AbstractAction(String name, TaskService taskService, Input input, Output out) {
        this.name = name;
        this.taskService = taskService;
        this.input = input;
        this.out = out;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute() {
        if(!checkBefore()) {
            return true;
        }
        doAction();
        return true;
    }

    protected boolean checkBefore() {
        if (!taskService.hasTasks()) {
            out.println("Задач нет");
            return false;
        }
        return true;
    }
    abstract protected void doAction();

    protected static String getMenuStatusText() {
        StringBuilder sb = new StringBuilder("Выберите статус:\n");
        for (Status s : Status.values()) {
            sb.append(s.getCode()).append(". ").append(s.getName()).append("\n");
        }
        sb.append("Введите число");
        return sb.toString();
    }
}

