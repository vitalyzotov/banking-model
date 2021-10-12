package ru.vzotov.banking.domain.model;

import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class BudgetCategoryId implements ValueObject<BudgetCategoryId> {

    private Long id;

    public BudgetCategoryId(Long id) {
        this.id = id;
    }

    public static BudgetCategoryId of(String name) {
        long h = 1125899906842597L; // prime
        int len = name.length();

        for (int i = 0; i < len; i++) {
            h = 31 * h + name.charAt(i);
        }
        return new BudgetCategoryId(Math.abs(h));
    }

    public Long id() {
        return id;
    }

    @Override
    public boolean sameValueAs(BudgetCategoryId that) {
        return that != null && Objects.equals(id, that.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetCategoryId that = (BudgetCategoryId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    protected BudgetCategoryId() {
        // for Hibernate
    }

}
