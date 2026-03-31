package ru.practicum.todolist.action;

import ru.practicum.todolist.core.entity.Task;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;

public class FindByKeywordAction extends AbstractAction {
    public FindByKeywordAction(String name, TaskService taskRepository, Input input, Output out) {
        super(name, taskRepository, input, out);
    }

    @Override
    protected void doAction() {
        String word = input.askStr("Введите ключевое слово:");
        List<Task> found = taskService.findByKeyword(word);
        if (found.isEmpty()) {
            out.println("По вашему запросу ничего не найдено");
        } else {
            found.forEach(out::println);
        }
    }
}
