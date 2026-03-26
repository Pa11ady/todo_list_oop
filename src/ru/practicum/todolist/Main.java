package ru.practicum.todolist;

import ru.practicum.todolist.core.Task;
import ru.practicum.todolist.core.TodoList;

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
    static Scanner input;


    public static void main(String[] args) {
        System.out.println("""
                Добро пожаловать в приложение "Управление списком задач"!
                """);
        input = new Scanner(System.in);
        while (true) {
            System.out.println("**********");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(i + 1 + " " + actions.get(i));
            }
            System.out.println("**********\n");
            System.out.println("Выберите действие:");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println(actions.get(choice - 1));
                    createAction();
                }
                case 2 -> {
                    System.out.println(actions.get(choice - 1));
                    deleteAction();
                }
                case 3 -> {
                    System.out.println(actions.get(choice - 1));
                    editAction();
                }
                case 4 -> {
                    System.out.println(actions.get(choice - 1));
                    showAllAction();
                }
                case 5 -> {
                    System.out.println(actions.get(choice - 1));
                    filterByStatusAction();
                }
                case 6 -> {
                    System.out.println(actions.get(choice - 1));
                    findByKeywordAction();
                }
                case 7 -> {
                    System.out.println(actions.get(choice - 1));
                    changeStatusAction();
                }
                case 8 -> {
                    System.out.println(actions.get(choice - 1));
                    showStatisticsAction();
                }
                case 9 -> {
                    System.out.println(actions.get(choice - 1));
                    input.close();
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
            System.out.println(task);
        }
    }

    private static void editAction() {
        int id;
        if (todoList.isEmpty()) {
            System.out.println("Задач нет");
            return;
        }
        do {
            System.out.println("Введите номер больше 0");
            id = input.nextInt();
            input.nextLine();
        } while (id <= 0);

        System.out.println("Введите название задачи:");
        String name = input.nextLine();
        System.out.println("Введите приоритет (число)");
        int priority = input.nextInt();
        input.nextLine();
        todoList.find(id).ifPresentOrElse(
                (task) -> {
                    task.setName(name);
                    task.setPriority(priority);
                    todoList.update(task);
                },
                () -> System.out.println("Задача не найдена")
        );
    }

    private static void deleteAction() {
        int id;
        if (todoList.isEmpty()) {
            System.out.println("Задач нет");
            return;
        }
        do {
            System.out.println("Введите номер больше 0");
            id = input.nextInt();
        } while (id <= 0);
        input.nextLine();
        if (todoList.delete(id)) {
            System.out.println("Задача удалена успешно.");
        } else {
            System.out.println("Ошибка удаления задачи.");
        }
    }

    private static void createAction() {
        System.out.println("Введите название задачи:");
        String name = input.nextLine();
        System.out.println("Введите приоритет (число)");
        int priority = input.nextInt();
        input.nextLine();
        todoList.add(new Task(name, priority));
    }
}