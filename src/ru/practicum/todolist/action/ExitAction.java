package ru.practicum.todolist.action;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute() {
        return false;
    }
}
