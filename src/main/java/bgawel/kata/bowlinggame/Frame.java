package bgawel.kata.bowlinggame;

class Frame {

    static final int NUMBER_OF_PINS = 10;

    private int rollIndex;

    public Frame(final int rollIndex) {
        this.rollIndex = rollIndex;
    }

    public int score(final int[] rolls) {
        int firstAttempt = firstAttempt(rolls);
        int points = firstAttempt;
        if (isStrike(firstAttempt)) {
            points += strikeBonus(rolls);
        } else {
            int secondAttempt = secondAttempt(rolls);
            points += secondAttempt;
            if (isSpare(firstAttempt, secondAttempt)) {
                points += spareBonus(rolls);
            }
        }
        return points;
    }

    private int firstAttempt(final int[] rolls) {
        return rolls[rollIndex];
    }

    private int secondAttempt(final int[] rolls) {
        return rolls[rollIndex + 1];
    }

    static boolean isStrike(final int hitPins) {
        return hitPins == NUMBER_OF_PINS;
    }

    private int strikeBonus(final int[] rolls) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    private boolean isSpare(final int firstAttempt, final int secondAttempt) {
        return firstAttempt + secondAttempt == NUMBER_OF_PINS;
    }

    private int spareBonus(final int[] rolls) {
        return rolls[rollIndex + 2];
    }
}
