package ru.practicum.todolist.core;

import ru.practicum.todolist.exception.TaskDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class TodoList {
    private final List<Task> tasks = new ArrayList<>();
    private int ids = 1;

    public Task add(Task task) {
        task.setId(ids++);
        tasks.add(task);
        return task;
    }

    public void delete(int id) {
        if (!tasks.removeIf(task -> task.getId() == id)) {
            throw new TaskDeleteException("Не удалось удалить задачу id = " + id);
        }
    }

    public Optional<Task> update(Task task) {
        int index = indexOf(task.getId());
        if (index == -1) {
            return Optional.empty();
        }
        tasks.set(index, task);
        return Optional.of(task);
    }

    public List<Task> findAll() {
        return List.copyOf(tasks);
    }

    public Optional<Task> find(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findAny();
    }

    private int indexOf(int id) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findAny()
                .orElse(-1);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> findByKeyword(String word) {
        return tasks.stream()
                .filter(x -> x.getName().toLowerCase().contains(word.toLowerCase()))
                .toList();
    }
}
