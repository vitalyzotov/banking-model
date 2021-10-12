package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import ru.vzotov.ddd.shared.DomainEvent;

public class HoldOperationRemovedEvent implements DomainEvent<HoldOperationRemovedEvent> {

    private final HoldOperation hold;

    public HoldOperationRemovedEvent(HoldOperation hold) {
        Validate.notNull(hold);
        this.hold = hold;
    }

    public HoldOperation hold() {
        return hold;
    }

    @Override
    public boolean sameEventAs(HoldOperationRemovedEvent other) {
        return other != null && new EqualsBuilder().
                append(this.hold, other.hold).
                isEquals();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HoldOperationRemovedEvent event = (HoldOperationRemovedEvent) o;

        return sameEventAs(event);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(hold).
                toHashCode();
    }

}
