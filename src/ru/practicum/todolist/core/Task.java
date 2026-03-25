package ru.practicum.todolist.core;

import java.time.LocalDateTime;

public class Task {
    private int id = 0;
    private String name;
    private int priority;
    private Status status = Status.UNCOMPLETED;
    private final LocalDateTime created = LocalDateTime.now();
    private LocalDateTime completedAt;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.COMPLETED) {
            this.completedAt = LocalDateTime.now();
        } else  {
            this.completedAt = null;
        }
        // Для IN_PROGRESS completedAt логично тоже сбрасывать, возможно опечатка в тз
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", created=" + created +
                ", completedAt=" + completedAt +
                '}';
    }
}
