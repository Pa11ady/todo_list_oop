package ru.practicum.todolist.io;

public class ConsoleOutput implements Output {
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}
