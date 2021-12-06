package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.banking.domain.model.OperationId;

public class OperationCreatedEvent {

    private final OperationId operationId;

    public OperationCreatedEvent(OperationId operationId) {
        Validate.notNull(operationId);
        this.operationId = operationId;
    }

    public OperationId operationId() {
        return operationId;
    }
}
