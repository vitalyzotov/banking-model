package ru.vzotov.banking.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

/**
 * MCC код (англ. Merchant Category Code — "код категории продавца") — четырёхзначный номер,
 * классифицирующий вид деятельности торговой точки при операции оплаты по банковским картам.
 * Компании присваивается код MCC, когда та начинает принимать к оплате карты.
 * Как правило, код назначается автоматически банком, предоставляющим услугу эквайринга
 * (приём к оплате банковских карт в качестве средства оплаты товаров или услуг),
 * при установке и настройке POS-терминала на основе анкеты о роде торговой деятельности.
 * В случае, когда торговое предприятие имеет несколько направлений деятельности,
 * MCC присваивается по основному направлению. Также возможна ситуация, когда в одной точке могут быть
 * терминалы разных банков с разными MCC.
 */
public class MccCode implements ValueObject<MccCode> {

    private String value;

    public MccCode(String value) {
        Validate.notNull(value);
        Validate.isTrue(value.matches("[0-9]{4}"), "Wrong MCC code: ", value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean sameValueAs(MccCode mccCode) {
        return mccCode != null && Objects.equals(value, mccCode.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccCode mccCode = (MccCode) o;
        return sameValueAs(mccCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    protected MccCode() {
        // for Hibernate
    }
}
