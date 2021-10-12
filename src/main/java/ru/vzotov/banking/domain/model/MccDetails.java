package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.util.Objects;

@AggregateRoot
public class MccDetails implements Entity<MccDetails> {

    private MccCode mcc;

    private String name;

    private MccGroup group;

    public MccDetails(MccCode mcc, String name) {
        this(mcc, name, null);
    }

    public MccDetails(MccCode mcc, String name, MccGroup group) {
        Validate.notNull(mcc);
        Validate.notNull(name);
        this.mcc = mcc;
        this.name = name;
        this.group = group;
    }

    public MccCode mcc() {
        return mcc;
    }

    public String name() {
        return name;
    }

    public MccGroup group() {
        return group;
    }

    @Override
    public boolean sameIdentityAs(MccDetails that) {
        return that != null && Objects.equals(mcc, that.mcc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccDetails that = (MccDetails) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mcc);
    }

    @Override
    public String toString() {
        return name;
    }

    protected MccDetails() {
        // for Hibernate
    }

}
