package ru.practicum.todolist;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;
import ru.practicum.todolist.io.ConsoleInput;
import ru.practicum.todolist.io.ConsoleOutput;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String> actions = List.of(
            "Добавить задачу",
            "Удалить задачу",
            "Отредактировать задачу",
            "Показать все задачи",
            "Фильтровать задачи по статусу",
            "Найти задачу по ключевому слову",
            "Изменить статус задачи",
            "Показать статистику",
            "Выход"
    );

    static TodoList todoList = new TodoList();
    static Input input;
    static Output out = new ConsoleOutput();


    public static void main(String[] args) {
        out.println("""
                Добро пожаловать в приложение "Управление списком задач"!
                """);
        Scanner scanner = new Scanner(System.in);
        input = new ConsoleInput(scanner);
        while (true) {
            out.println("**********");
            for (int i = 0; i < actions.size(); i++) {
                out.println(i + 1 + " " + actions.get(i));
            }
            out.println("**********\n");
            int choice = input.askInt("Выберите действие:", 1, actions.size());
            switch (choice) {
                case 1 -> {
                    out.println(actions.get(choice - 1));
                    createAction();
                }
                case 2 -> {
                    out.println(actions.get(choice - 1));
                    deleteAction();
                }
                case 3 -> {
                    out.println(actions.get(choice - 1));
                    editAction();
                }
                case 4 -> {
                    out.println(actions.get(choice - 1));
                    showAllAction();
                }
                case 5 -> {
                    out.println(actions.get(choice - 1));
                    filterByStatusAction();
                }
                case 6 -> {
                    out.println(actions.get(choice - 1));
                    findByKeywordAction();
                }
                case 7 -> {
                    out.println(actions.get(choice - 1));
                    changeStatusAction();
                }
                case 8 -> {
                    out.println(actions.get(choice - 1));
                    showStatisticsAction();
                }
                case 9 -> {
                    out.println(actions.get(choice - 1));
                    scanner.close();
                    return;
                }
            }
        }
    }

    private static void showStatisticsAction() {
    }

    private static void changeStatusAction() {
    }

    private static void findByKeywordAction() {
    }

    private static void filterByStatusAction() {
    }

    private static void showAllAction() {
        List<Task> tasks = todoList.findAll();
        for (Task task : tasks) {
            out.println(task);
        }
    }

    private static void editAction() {
        int id;
        if (todoList.isEmpty()) {
            out.println("Задач нет");
            return;
        }

        id = input.askInt("Введите номер ", 1, Short.MAX_VALUE);
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        todoList.find(id).ifPresentOrElse(
                (task) -> {
                    task.setName(name);
                    task.setPriority(priority);
                    todoList.update(task);
                    out.println("Задача изменена.");
                },
                () -> out.println("Задача не найдена.")
        );
    }

    private static void deleteAction() {
        if (todoList.isEmpty()) {
            out.println("Задач нет");
            return;
        }
        int id = input.askInt("Введите номер ", 1, Short.MAX_VALUE);
        if (todoList.delete(id)) {
            out.println("Задача удалена успешно.");
        } else {
            out.println("Ошибка удаления задачи.");
        }
    }

    private static void createAction() {
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        todoList.add(new Task(name, priority));
    }
}