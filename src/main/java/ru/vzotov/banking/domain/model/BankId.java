package ru.vzotov.banking.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

/**
 * BIC in Russia
 * <p>
 * Банковский идентификационный код (БИК)
 * </p>
 * <p>
 * Девятизначный уникальный код кредитной организации.
 * Он предназначен для идентификации участников расчетов и является обязательным элементом реквизитов любого банка.
 * БИК используется в платежных документах на территории России.
 * Он позволяет определить название, корреспондентский счет и региональное расположение банка,
 * а также подразделение ЦБ РФ, ответственное за его регистрацию и обслуживание.
 * </p>
 * <p>
 * первые две цифры обозначают код Российской Федерации («04»);
 * </p>
 * <p>
 * третья и четвертая — код региона РФ согласно «Общероссийскому классификатору объектов
 * административно-территориального деления» ОК 019-95 (ОКАТО), принятому постановлением госстандарта России
 * от 31 июля 1995 года № 413. Если эти цифры равны «00», значит, подразделение банка находится за пределами России;
 * </p>
 * <p>
 * пятая и шестая — условный номер подразделения расчетной сети ЦБ РФ, или условный номер структурного подразделения
 * Банка России. Код может принимать цифровые значения от «00» до «99»;
 * </p>
 * <p>
 * с седьмой по девятую — условный номер кредитной организации в подразделении расчетной сети Центробанка,
 * в котором открыт ее корреспондентский счет. Может принимать значения от «050» до «999».
 * </p>
 */
public class BankId implements ValueObject<BankId> {

    public static final BankId TINKOFF = new BankId("044525974");
    public static final BankId ALFABANK = new BankId("044525593");
    public static final BankId GAZPROMBANK = new BankId("044525823");
    public static final BankId OZON = new BankId("044525127");

    private String value;

    public BankId(String value) {
        Validate.notEmpty(value);
        Validate.isTrue(value.matches("[0-9]{9}"), "Bank identification number must be 9 digits:", value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public int orgNumber() {
        return Integer.parseInt(value.substring(6));
    }

    @Override
    public boolean sameValueAs(BankId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankId that = (BankId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    protected BankId() {
        // for Hibernate
    }
}
