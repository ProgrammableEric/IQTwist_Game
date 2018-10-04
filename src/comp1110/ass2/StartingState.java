package comp1110.ass2;

import java.util.Random;

// this class is a dictionary of the starting state of the game (any locations for preset pegs and pieces)
// and some implementations. It could be multiple placement string patterns that will be selected by
// the player at the start of the game.
// implement initial viewer showing the below:
// there will be 5 difficulty levels: starter, junior, expert, master, wizard
// also include restart and back button in game board


public class StartingState {


    public static String Starter() {
        String[] scenes = {"26l27l28j29j30k31k", "2j9j10l11l12i18k", "2k4l6j10l12k14j", "12l13k20i21l", "9j13i18l19k20k25j29l", "5i13k21l29k"};
        Random i = new Random();
        int rand = i.nextInt(6);
        return scenes[rand];
    }

    public static String Junior() {
        String[] scenes = {"22j30i", "9i17l", "3i5l12l19j22k", "9k12i17l20j", "6l11k15k", "2j5i16j21l26k31l"};
        Random i = new Random();
        int rand = i.nextInt(6);
        return scenes[rand];
    }

    public static String Expert() {
        String[] scenes = {"10j12k25j29l30l", "9k11i22j28j", "4l7l18j26j29k", "0k3l21j23k26j", "10j11k18i19k", "5j9k10j"};
        Random i = new Random();
        int rand = i.nextInt(6);
        return scenes[rand];
    }

    public static String Master() {
        String[] scenes = {"15k20k21i", "5j8k14k18l21i27l", "3l9k13l19i", "6j13i14j16k20l24l", "2k5j26l28j", "3j9i14j18k26k", "4j14k21i25j", "2j7k12j18i24k"};
        Random i = new Random();
        int rand = i.nextInt(6);
        return scenes[rand];
        switch (pieceID) {
            case 'i':
                return Colour.RED;
            case 'j':
                return Colour.BLUE;
            case 'k':
                return Colour.GREEN;
            case 'l':
                return Colour.YELLOW;
    }

    public static String Wizard() {
        String[] scenes = {"19j23k28k", "10j17j22l", "3k14l17l", "12j18l22i28k", "6i8k23k", "2k4l26i28j", "0l9j13l22k30k", "17l20j21k", "11l13k28i", "4i18j21l", "12k20j28j", "4k20j29i"};
        Random i = new Random();
        int rand = i.nextInt(12);
        return scenes[rand];
    }

}



