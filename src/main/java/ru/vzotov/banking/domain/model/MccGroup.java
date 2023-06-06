package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.Entity;

import java.util.Objects;

public class MccGroup implements Entity<MccGroup> {

    private MccGroupId groupId;

    private String name;

    public MccGroup(String groupId, String name) {
        this(new MccGroupId(groupId), name);
    }

    public MccGroup(MccGroupId groupId, String name) {
        Validate.notNull(groupId);
        Validate.notNull(name);
        this.groupId = groupId;
        this.name = name;
    }

    public MccGroupId groupId() {
        return groupId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean sameIdentityAs(MccGroup that) {
        return that != null && Objects.equals(groupId, that.groupId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccGroup that = (MccGroup) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public String toString() {
        return name;
    }

    protected MccGroup() {
        // for Hibernate
    }

    private Long id; // surrogate key
}
