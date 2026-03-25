package ru.practicum.todolist.core;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    public Task add(Task task) {
        System.out.println("Добавил" + task);
        return task;
    }

    public boolean delete(int index) {
        System.out.println("Удаляем " + index);
        return false;
    }

    public Task update(int index, Task task) {
        System.out.println("Обновляем " + index + " "+ task);
        return task;
    }

    public int getSize() {
        return 0;
    }

    public List<Task> findAll() {
        return new ArrayList<Task>();
    }

    public Task find(int index) {
        return null;
    }
}
