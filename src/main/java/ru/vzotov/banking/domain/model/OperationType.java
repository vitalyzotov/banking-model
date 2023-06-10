package ru.vzotov.banking.domain.model;

import ru.vzotov.ddd.shared.ValueObject;

public enum OperationType implements ValueObject<OperationType> {
    DEPOSIT('+'),
    WITHDRAW('-');

    private final char symbol;

    OperationType(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }

    @Override
    public boolean sameValueAs(OperationType other) {
        return this.equals(other);
    }

    public static OperationType of(char symbol) {
        for (OperationType opt : OperationType.values()) {
            if (opt.symbol == symbol) return opt;
        }
        throw new IllegalArgumentException("Unknown operation type");
    }
}
