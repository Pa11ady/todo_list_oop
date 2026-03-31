package ru.practicum.todolist.exception;

public class TaskDeleteException extends RuntimeException {
    public TaskDeleteException(String message) {
        super(message);
    }
}
