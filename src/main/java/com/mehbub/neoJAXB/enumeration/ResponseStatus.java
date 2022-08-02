package com.mehbub.neoJAXB.enumeration;

public enum ResponseStatus {
    ERROR(0),
    SUCCESS(1),
    VALIDATION(2),
    EXCEPTION(3);

    private final int value;

    ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
