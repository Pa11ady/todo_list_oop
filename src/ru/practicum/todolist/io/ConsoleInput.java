package ru.practicum.todolist.io;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int askInt(String msg, int minInt, int maxInt) {
        int number;
        do {
            System.out.println(msg);
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Введите число.");
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number < minInt || number > maxInt);
        scanner.nextLine();
        return number;
    }

    @Override
    public String askStr(String msg) {
        String result;
        do {
            System.out.print(msg);
            result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Ввод не может быть пустым.");
            }
        } while (result.isEmpty());
        return result;
    }
}
