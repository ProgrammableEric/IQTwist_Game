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
        String[] scenes = {"h4B2l3D0l4D0j5D0j6D0k7D0k8D0","j3A0j2B0l3B0l4B0i5B0h8B7k3C0","k3A0l5A0j7A0l3B0k5B0j7B0b2C0"};
        Random i = new Random();
        int rand = i.nextInt(3);
        return scenes[rand];
    }

    public static String Junior() {
        String[] scenes = {"j7C0i7D0","i2B0l2C0","i4A0l6A0l5B0j4C0k7C0"};
        Random i = new Random();
        int rand = i.nextInt(3);
        return scenes[rand];
    }

    public static String Expert() {
        String[] scenes = {"j3B0k5B0j2D0l6D0l7D0","k2B0i4B0j7C0j5D0","l5A0l8A0j3C0j3D0k6D0"};
        Random i = new Random();
        int rand = i.nextInt(3);
        return scenes[rand];
    }

    public static String Master() {
        String[] scenes = {"k8B0k5C0i6C0","j6A0k1B0k7B0l3C0i6C0l4D0","l4A0k2B0l6B0i4C0"};
        Random i = new Random();
        int rand = i.nextInt(3);
        return scenes[rand];

    }

    public static String Wizard() {
        String[] scenes = {"j4C0k8C0k5D0","j3B0j2C0l7C0","k4A0l7B0l2C0"};
        Random i = new Random();
        int rand = i.nextInt(3);
        return scenes[rand];
    }

}



