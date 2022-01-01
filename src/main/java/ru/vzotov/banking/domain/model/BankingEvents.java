package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.DomainEvent;

import java.util.Objects;

public final class BankingEvents {

    private BankingEvents() {
    }


    public static class OperationCreatedEvent implements DomainEvent<OperationCreatedEvent> {

        private final OperationId operationId;

        public OperationCreatedEvent(OperationId operationId) {
            Validate.notNull(operationId);
            this.operationId = operationId;
        }

        public OperationId operationId() {
            return operationId;
        }

        @Override
        public boolean sameEventAs(OperationCreatedEvent other) {
            return other != null && other.operationId.equals(this.operationId);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final OperationCreatedEvent event = (OperationCreatedEvent) o;

            return sameEventAs(event);
        }

        @Override
        public int hashCode() {
            return Objects.hash(operationId);
        }

    }

    public static class CardOperationCreatedEvent implements DomainEvent<CardOperationCreatedEvent> {

        private final OperationId operationId;

        public CardOperationCreatedEvent(OperationId operationId) {
            Validate.notNull(operationId);
            this.operationId = operationId;
        }

        public OperationId operationId() {
            return operationId;
        }

        @Override
        public boolean sameEventAs(CardOperationCreatedEvent other) {
            return other != null && other.operationId.equals(this.operationId);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final CardOperationCreatedEvent event = (CardOperationCreatedEvent) o;

            return sameEventAs(event);
        }

        @Override
        public int hashCode() {
            return Objects.hash(operationId);
        }

    }
}
