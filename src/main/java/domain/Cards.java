package domain;

import static domain.CardNumberType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int VALID_MAX_SUM_LIMIT = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean isEqualAndMoreMaxSum() {
        return calculateSumWithLowAce() >= VALID_MAX_SUM_LIMIT;
    }

    public boolean isAceCountEqualTo(int targetCount) {
        int aceCount = (int) cards.stream()
                .filter(card -> card.isEqualCardNumberType(ACE))
                .count();
        return aceCount == targetCount;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addAll(Cards receivedCards) {
        cards.addAll(receivedCards.getCards());
    }

    public int calculateSum() {
        if (hasNotAce()) {
            return calculateSumWithoutAce();
        }
        int cardsSumWithoutAce = calculateSumWithoutAce();
        int aceCount = calculateAceCount();

        return calculateSumWithAces(aceCount, cardsSumWithoutAce);
    }

    public int calculateSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isEqualCardNumberType(ACE))
                .mapToInt(Card::getDefaultNumber)
                .sum();
    }

    private int calculateSumWithAces(int aceCount, int cardsWithoutAceSum) {
        int result = cardsWithoutAceSum;
        for (int count = 0; count < aceCount; count++) {
            result += getAceNumber(result);
        }
        return result;
    }

    private int calculateSumWithLowAce() {
        return cards.stream()
                .map(Card::cardNumberType)
                .mapToInt(CardNumberType::getDefaultNumber)
                .sum();
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(card -> card.isEqualCardNumberType(ACE))
                .count();
    }

    private boolean hasNotAce() {
        return cards.stream()
                .noneMatch(card -> card.isEqualCardNumberType(ACE));
    }
}
