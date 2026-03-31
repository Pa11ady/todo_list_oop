package ru.practicum.todolist.core.exception;

public class TaskDeleteException extends RuntimeException {
    public TaskDeleteException(String message) {
        super(message);
    }
}
