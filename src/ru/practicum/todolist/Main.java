package ru.practicum.todolist;

import ru.practicum.todolist.action.*;
import ru.practicum.todolist.core.entity.Task;
import ru.practicum.todolist.core.repository.TaskRepository;
import ru.practicum.todolist.core.service.TaskService;
import ru.practicum.todolist.io.ConsoleInput;
import ru.practicum.todolist.io.ConsoleOutput;
import ru.practicum.todolist.io.Input;
import ru.practicum.todolist.io.Output;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);
        initTestData(taskRepository);
        Scanner scanner = new Scanner(System.in);
        Input input = new ConsoleInput(scanner);
        Output out = new ConsoleOutput();

        out.println("Добро пожаловать в приложение \"Управление списком задач\"!");

        int choice;
        List<UserAction> actions = List.of(
                new CreateAction("Добавить задачу", taskService, input, out),
                new DeleteAction("Удалить задачу", taskService, input, out),
                new EditAction("Отредактировать задачу", taskService, input, out),
                new ShowAllAction("Показать все задачи", taskService, input, out),
                new FilterAction("Фильтровать задачи", taskService, input, out),
                new FindByKeywordAction("Найти задачи по ключевому слову", taskService, input, out),
                new ChangeStatusAction("Изменить статус задачи", taskService, input, out),
                new ShowStatisticsAction("Показать статистику", taskService, input, out),
                new ExitAction());
        do {
            printMenu(actions, out);
            choice = input.askInt("Выберите действие:", 1, actions.size());
        } while (actions.get(choice - 1).execute());
        scanner.close();
    }

    private static void initTestData(TaskRepository taskRepository) {
        taskRepository.create(new Task("aaa", 1));
        taskRepository.create(new Task("bbb aaa", 2));
        taskRepository.create(new Task("ccc", 3));
        taskRepository.create(new Task("dddd", 4));
        taskRepository.create(new Task("eee", 5));
    }

    private static void printMenu(List<UserAction> actions, Output out) {
        out.println("**********");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + 1 + ". " + actions.get(i).name());
        }
        out.println("**********\n");
    }
}
