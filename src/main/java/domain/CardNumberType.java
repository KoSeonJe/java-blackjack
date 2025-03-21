package domain;

import static util.ExceptionConstants.*;

import java.util.Arrays;
import java.util.List;

public enum CardNumberType {
    ACE(1, 11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private static final String INVALID_INDEX = "해당하는 카드 숫자의 인덱스가 존재하지 않습니다.";
    private static final int ACE_HIGH_CONVERSION_THRESHOLD = 10;

    private final List<Integer> cardNumbers;

    CardNumberType(int... cardNumbers) {
        this.cardNumbers = Arrays.stream(cardNumbers)
                .boxed()
                .toList();
    }

    public static int determineAceNumber(int restSum) {
        if (restSum <= ACE_HIGH_CONVERSION_THRESHOLD) {
            return CardNumberType.getAceHighNumber();
        }
        return CardNumberType.getAceLowNumber();
    }

    public static int getAceHighNumber() {
        return ACE.cardNumbers.getLast();
    }

    public static CardNumberType findByRandomIndex(int randomIndex) {
        validateIndex(randomIndex);
        return CardNumberType.values()[randomIndex];
    }

    public int getDefaultNumber() {
        return cardNumbers.getFirst();
    }

    public boolean isEqualTo(CardNumberType targetCardNumberType) {
        return this == targetCardNumberType;
    }

    private static void validateIndex(int index) {
        if (index >= CardNumberType.values().length) {
            throw new IllegalArgumentException(ERROR_HEADER + INVALID_INDEX);
        }
    }

    private static int getAceLowNumber() {
        return ACE.cardNumbers.getFirst();
    }
}
