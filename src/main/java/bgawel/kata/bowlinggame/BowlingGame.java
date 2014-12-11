package bgawel.kata.bowlinggame;

/**
 * Bowling Game Kata
 *
 * @see http://butunclebob.com/files/downloads/Bowling%20Game%20Kata.ppt
 *
 * @author bgawel
 */
public class BowlingGame {

    private static final int MAX_NUMBER_OF_ROLLS = 21;

    private int[] rolls = new int[MAX_NUMBER_OF_ROLLS];
    private int currentRoll = 0;

    public void roll(final int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int totalScore = 0;
        for (Frame frame : Frames.make(rolls)) {
            totalScore += frame.score(rolls);
        }
        return totalScore;
    }
}
