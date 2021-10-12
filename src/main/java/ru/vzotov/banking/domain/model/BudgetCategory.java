package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.util.Objects;

/**
 * Категория бюджета. Категории бюджета относятся к банковским операциям.
 */
@AggregateRoot
public class BudgetCategory implements Entity<BudgetCategory> {

    private BudgetCategoryId id;

    private String name;

    private Integer color;

    private String icon;

    public BudgetCategory(BudgetCategoryId id, String name) {
        this(id, name, null, null);
    }

    public BudgetCategory(BudgetCategoryId id, String name, Integer color) {
        this(id, name, color, null);
    }

    public BudgetCategory(BudgetCategoryId id, String name, Integer color, String icon) {
        Validate.notNull(id);
        Validate.notNull(name);
        Validate.notEmpty(name);

        this.id = id;
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public BudgetCategoryId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void rename(String newName) {
        Validate.notEmpty(newName);
        this.name = newName;
    }

    public Integer color() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String icon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean sameIdentityAs(BudgetCategory other) {
        return other != null && new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BudgetCategory that = (BudgetCategory) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected BudgetCategory() {
        // for Hibernate
    }
}
