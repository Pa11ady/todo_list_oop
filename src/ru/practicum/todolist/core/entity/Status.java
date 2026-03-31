package ru.practicum.todolist.core.entity;

import java.util.Arrays;

public enum Status {
    UNCOMPLETED(0, "Невыполнена"),
    IN_PROGRESS(1, "В процессе"),
    COMPLETED(2, "Выполнена");

    private final int code;
    private final String name;

    Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Status fromCode(int code) {
        return Arrays.stream(values())
                .filter(status -> status.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Неизвестный код: " + code));
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
