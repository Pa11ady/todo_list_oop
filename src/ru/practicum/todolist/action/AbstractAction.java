package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Status;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

public abstract class AbstractAction implements UserAction {
    private final String name;
    protected final TodoList todoList;
    protected final Input input;
    protected final Output out;

    public AbstractAction(String name, TodoList todoList, Input input, Output out) {
        this.name = name;
        this.todoList = todoList;
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
            return false;
        }
        doAction();
        return true;
    }

    protected boolean checkBefore() {
        if (todoList.isEmpty()) {
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

