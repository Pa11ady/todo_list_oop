package ru.practicum.todolist;

import ru.practicum.todolist.core.Status;
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
        if (todoList.isEmpty()) {
            out.println("Задач нет");
            return;
        }
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String menuText = getStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        todoList.find(id).ifPresentOrElse(
                task -> {
                    task.setStatus(Status.fromCode(code));
                    out.println("Статус задачи изменен.");
                },
                () -> out.println("Задача не найдена.")
        );
    }

    private static void findByKeywordAction() {
        if (todoList.isEmpty()) {
            out.println("Задач нет");
            return;
        }
        String word = input.askStr("Введите ключевое слово:");
        List<Task> found = todoList.findByKeyword(word);
        if (found.isEmpty()) {
            out.println("По вашему запросу ничего не найдено");
        } else {
            found.forEach(out::println);
        }
    }

    private static void filterByStatusAction() {
        if (todoList.isEmpty()) {
            out.println("Задач нет");
            return;
        }
        String menuText = getStatusText();
        int code = input.askInt(menuText, 0, Status.values().length - 1);
        Status status = Status.fromCode(code);
        out.println("Введите диапазон приоритетов [1 - 10]");
        int minPriority = input.askInt("Минимальный приоритет", 1, 10);
        int maxPriority = input.askInt("Максимальный приоритет", minPriority, 10);
        todoList.findAll().stream()
                .filter(x -> x.getStatus().equals(status) && minPriority <=x.getPriority()
                && x.getPriority() <= maxPriority)
                .forEach(out::println);
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

        id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
        String name = input.askStr("Введите название задачи:");
        int priority = input.askInt("Введите приоритет (число)",1,10);
        todoList.find(id).ifPresentOrElse(
                task -> {
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
        int id = input.askInt("Введите номер задачи", 1, Short.MAX_VALUE);
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

    private static String getStatusText() {
        StringBuilder sb = new StringBuilder("Выберите статус:\n");
        for (Status s : Status.values()) {
            sb.append(s.getCode()).append(". ").append(s.getName()).append("\n");
        }
        sb.append("Введите число");
        return sb.toString();
    }
}
