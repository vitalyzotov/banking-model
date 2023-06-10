package ru.vzotov.banking.domain.model;

import java.util.stream.Stream;

public enum CardNumberType {
    VISA("Visa",
            13, 19, CardNumberSpacing.SPACING_4_4_4_4,
            "4"),

    VISA_ELECTRON("Visa Electron",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "4026", "417500", "4405", "4508", "4844", "4913", "4917"),

    AMERICAN_EXPRESS("American Express",
            15, 15, CardNumberSpacing.SPACING_4_6_5,
            "34", "37"),

    CHINA_UNIONPAY("China UnionPay",
            16, 19, CardNumberSpacing.SPACING_4_4_4_4,
            "62"),

    MIR("Мир",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "2200", "2201", "2202", "2203", "2204"),

    MASTERCARD("MasterCard",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "51", "52", "53", "54", "55"),

    MAESTRO_12("Maestro",
            12, 12, CardNumberSpacing.SPACING_4_4_5,
            "5018", "5020", "5038", "6304", "6759", "6761", "6763", "6799"),
    MAESTRO_15("Maestro",
            15, 15, CardNumberSpacing.SPACING_4_6_5,
            "5018", "5020", "5038", "6304", "6759", "6761", "6763", "6799"),
    MAESTRO_16("Maestro",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "5018", "5020", "5038", "6304", "6759", "6761", "6763", "6799"),
    MAESTRO_19("Maestro",
            19, 19, CardNumberSpacing.SPACING_4_4_4_4_3,
            "5018", "5020", "5038", "6304", "6759", "6761", "6763", "6799"),

    DINERS_CLUB("Diners Club",
            14, 14, CardNumberSpacing.SPACING_4_6_4,
            "300", "301", "302", "303", "304", "305", "309", "36", "38", "39"),
    DINERS_CLUB_US("Diners Club",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "54", "55"),

    DISCOVER("Discover",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "6011", "622", "644", "645", "646", "647", "648", "649", "65"),

    JCB("JCB",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "35"),
    UATP("UATP",
            15, 15, CardNumberSpacing.SPACING_4_5_6,
            "1"),
    DANKORT("Dankort",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "5019"),
    INTERPAYMENT("Unterpayment",
            16, 16, CardNumberSpacing.SPACING_4_4_4_4,
            "636"),
    SOLO_16("Solo", 16, 16, CardNumberSpacing.SPACING_4_4_4_4, "6334", "6767"),
    SOLO_19("Solo", 19, 19, CardNumberSpacing.SPACING_4_4_4_4_3, "6334", "6767"),
    SWITCH("Switch", 16, 16, CardNumberSpacing.SPACING_4_4_4_4, "4903", "4905", "4911", "4936", "564182", "633110", "6333", "6759"),
    LASER("Laser", 16, 16, CardNumberSpacing.SPACING_4_4_4_4, "6304", "6706", "6771", "6709"),
    LASER_18("Laser", 18, 18, CardNumberSpacing.SPACING_4_4_4_6, "6304", "6706", "6771", "6709"),
    BANKCARD("Bankcard", 16, 16, CardNumberSpacing.SPACING_4_4_4_4, "5610", "560221", "560222", "560223", "560224", "560225"),
    UNKNOWN("Unknown", 12, 19, CardNumberSpacing.SPACING_4_4_4_4);

    private final String name;
    private final int minLength;
    private final int maxLength;
    private final String[] iin;
    private final int[] spacing;

    CardNumberType(String name, int minLength, int maxLength, int[] spacing, String... iin) {
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.iin = iin == null ? new String[0] : iin;
        this.spacing = spacing;
    }

    public String displayName() {
        return name;
    }

    public int[] spacing() {
        return spacing;
    }

    public static CardNumberType of(String cardNumber) {
        final int l = cardNumber.length();
        for (CardNumberType type : CardNumberType.values()) {
            if (UNKNOWN.equals(type)) return type;
            if (l >= type.minLength && l <= type.maxLength) {
                if (Stream.of(type.iin).anyMatch(cardNumber::startsWith)) return type;
            }
        }
        return UNKNOWN;
    }

    private static class CardNumberSpacing {
        private static final int[] SPACING_4_4_5 = new int[]{4, 4, 5};
        private static final int[] SPACING_4_5_6 = new int[]{4, 5, 6};
        private static final int[] SPACING_4_6_4 = new int[]{4, 6, 4};
        private static final int[] SPACING_4_6_5 = new int[]{4, 6, 5};
        private static final int[] SPACING_4_4_4_4 = new int[]{4, 4, 4, 4};
        private static final int[] SPACING_4_4_4_6 = new int[]{4, 4, 4, 6};
        private static final int[] SPACING_4_4_4_4_3 = new int[]{4, 4, 4, 4, 3};
    }
}
