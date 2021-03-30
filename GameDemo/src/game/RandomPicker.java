package game;

import java.util.Random;

public class RandomPicker {

    public static int getRandom() {
        //generate random integer between 0 and 3 to be used in generating random spawn locations for the enemies in the boss level.
        Random random = new Random();
        return random.nextInt(4);
    }
}
