package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.Entity;

import java.util.Objects;

public class Person implements Entity<Person> {

    private PersonId personId;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Имя, отображаемое в интерфейсе
     */
    private String displayName;

    public Person(PersonId personId, String firstName, String lastName, String displayName) {
        Validate.notNull(personId);
        Validate.notEmpty(firstName);
        Validate.notEmpty(lastName);
        Validate.notEmpty(displayName);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
    }

    public PersonId personId() {
        return personId;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String displayName() {
        return displayName;
    }

    @Override
    public boolean sameIdentityAs(Person that) {
        return that != null && Objects.equals(personId, that.personId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }

    protected Person() {
        // for Hibernate
    }

    private Long id; // surrogate key

}
