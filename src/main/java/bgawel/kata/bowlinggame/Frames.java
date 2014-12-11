package bgawel.kata.bowlinggame;

import static bgawel.kata.bowlinggame.Frame.isStrike;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int NUMBER_OF_FRAMES = 10;

    private Frames() {}

    public static List<Frame> make(final int rolls[]) {
        List<Frame> frames = new ArrayList<>();
        int rollIndex = 0;
        for (int i = 0; i < NUMBER_OF_FRAMES; ++i) {
            frames.add(new Frame(rollIndex));
            if (!isStrike(rolls[rollIndex++])) {
                ++rollIndex;
            }
        }
        return frames;
    }
}
