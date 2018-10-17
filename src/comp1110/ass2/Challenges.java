package comp1110.ass2;
/**
 * Choose a new starting statement, given a difficulty level.  The method should select a randomized
 * statement from the n pre-defined solutions, being sure to select a statement with the correct
 * level of difficulty.
 *
 * So, for example, if the difficulty level is 1 (starter), then this function should use a randomized
 * index between 0 and 11 (inclusive) to return an challenge from the statement array that is
 * level 1 difficulty.  On the other hand, if the difficulty is 4 (master), then this function
 * should use a randomized index between 36 and 47 (inclusive) to return an objective from the
 * OBJECTIVES array that is level 4 difficulty.

 *
 *  The difficulty of the game (1 - starter, 2 - junior, 3 - expert, 4 - master)
 * @return A challenge at the appropriate level of difficulty.
 *
 * Author: Hua Guo
 */

public class Challenges {
    private String statement;   // the starting statement
    private int Number;         // the number of original game (1 .. 48)

    /**
     * Construct the challenges.
     * @param statement  A string represnting the starting placement
     * @param Number The challenge number from the original board game,
     *               a value from 1 .. 48.
     */
    public Challenges(String statement,int Number) {
        assert Number >= 1 && Number <= 48;
        this.statement = statement;
        this.Number = Number;
    }


    /**
     * Choose a new challenge, given a difficulty level.  The method should select a randomized
     * objective from the 48 pre-defined solutions, being sure to select a challenge with the correct
     * level of difficulty.
     *
     * So, for example, if the difficulty is 1 (starter), then this function should use a randomized
     * index between 0 and 11 (inclusive) to return an objective from the CHALLENGES array that is
     * level 1 difficulty.  On the other hand, if the difficulty is 4 (master), then this function
     * should use a randomized index between 36 and 47 (inclusive) to return an objective from the
     * OBJECTIVES array that is level 4 difficulty.
     * <p>
     *
     * @param difficulty The difficulty of the game (1 - starter, 2 - junior, 3 - expert, 4 - master)
     * @return A challenge at the appropriate level of difficulty.
     */

    public static Challenges newChallenge(int difficulty) {
        assert difficulty >= 1 && difficulty <= 4;
        int r = 0;
        switch (difficulty) {
            case 1:
                r = (int) (Math.random() * 3);
                break;
            case 2:
                r = (int) (Math.random() * 3 + 3);
                break;

            case 3:
                r = (int) (Math.random() * 3 + 6);
                break;

            case 4:
                r = (int) (Math.random() * 3 + 9);
                break;
        }

        return CHALLENGES[r];
    }

    /**
     * @return the problem number for this challenge
     */
    public int getNumber() {
        return Number;
    }


    /**
     * @return the statement for this challenge
     */
    public String getStatement() {
        return statement;
    }

    /**
     * This array defines a set of 48 pre-defined challenges.
     *
     * There are four categories of challenge, according to four difficulty levels, with
     * 12 objectives per difficulty level, organized within the array as follows:
     * Starter: 0-11
     * Junior: 12-23
     * Expert: 24-35
     * Master: 36-47
     */

    static Challenges[] CHALLENGES = {
         new Challenges("h4B2j5D0j6D0k7D0k8D0l3D0l4D0",1),
         new Challenges("h8B7i5B0j3A0j2B0k3C0l3B0l4B0",2),
         new Challenges("b2C0j7A0j7B0k3A0k5B0l3B0l5A0",3),

         new Challenges("j3B0j2D0k5B0l6D0l7D0",4),
         new Challenges("i4B0j7C0j5D0k2B0",5),
         new Challenges("j3C0j3D0k6D0l5A0l8A0",6),

         new Challenges("i6C0k8B0k5C0",7),
         new Challenges("i6C0j6A0k1B0k7B0l3C0l4D0",8),
         new Challenges("i4C0k2B0l4A0l6B0",9),

         new Challenges("k8B0k5C0i6C0",10),
         new Challenges("j6A0k1B0k7B0l3C0i6C0l4D0",11),
         new Challenges("l4A0k2B0l6B0i4C0",12),

 };

    public  String getSolution (){
        switch (this.Number) {
            case 1: return "a1B5b7A1c4A0d4C0e7C1f1A0g2B7h4B2";
            case 2: return "a5C4b5A7c1A2d1B3e2C4f6A0g3B5h8B7";
            case 3: return "a1B5b2C0c5D0d7A7e5A1f1A0g3A1h5C0";
            case 4: return "";
            case 5: return "";
            case 6: return "";
            default: return "";


        }

    }

}