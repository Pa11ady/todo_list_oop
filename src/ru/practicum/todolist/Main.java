package ru.practicum.todolist;

import ru.practicum.todolist.action.*;
import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.ConsoleInput;
import ru.practicum.todolist.io.ConsoleOutput;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        initTestData(todoList);
        Scanner scanner = new Scanner(System.in);
        Input input = new ConsoleInput(scanner);
        Output out = new ConsoleOutput();

        out.println("Добро пожаловать в приложение \"Управление списком задач\"!");

        int choice;
        List<UserAction> actions = List.of(
                new CreateAction("Добавить задачу", todoList, input, out),
                new DeleteAction("Удалить задачу", todoList, input, out),
                new EditAction("Отредактировать задачу", todoList, input, out),
                new ShowAllAction("Показать все задачи", todoList, input, out),
                new FilterByStatusAction("Фильтровать задачи по статусу", todoList, input, out),
                new FindByKeywordAction("Найти задачи по ключевому слову", todoList, input, out),
                new ChangeStatusAction("Изменить статус задачи", todoList, input, out),
                new ShowStatisticsAction("Показать статистику", todoList, input, out),
                new ExitAction());
        do {
            printMenu(actions, out);
            choice = input.askInt("Выберите действие:", 1, actions.size());
        } while (actions.get(choice - 1).execute());
        scanner.close();
    }

    private static void initTestData(TodoList todoList) {
        todoList.add(new Task("aaa", 1));
        todoList.add(new Task("bbb aaa", 2));
        todoList.add(new Task("ccc", 3));
        todoList.add(new Task("dddd", 4));
        todoList.add(new Task("eee", 5));
    }

    private static void printMenu(List<UserAction> actions, Output out) {
        out.println("**********");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + 1 + ". " + actions.get(i).name());
        }
        out.println("**********\n");
    }
}
