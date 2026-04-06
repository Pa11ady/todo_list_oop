package ru.practicum.todolist.io;

public interface Input {
    int askInt(String msg, int minInt, int maxInt);

    String askStr(String msg);
}
