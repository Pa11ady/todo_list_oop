package ru.practicum.todolist.action;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;

public class FindByKeywordAction extends AbstractAction {
    public FindByKeywordAction(String name, TodoList todoList, Input input, Output out) {
        super(name, todoList, input, out);
    }

    @Override
    protected void doAction() {
        String word = input.askStr("Введите ключевое слово:");
        List<Task> found = todoList.findByKeyword(word);
        if (found.isEmpty()) {
            out.println("По вашему запросу ничего не найдено");
        } else {
            found.forEach(out::println);
        }
    }
}
