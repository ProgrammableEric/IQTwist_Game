package comp1110.ass2;
/**
 * Choose a new starting statement, given a challenge level.  The method should select a randomized
 * statement from the n pre-defined solutions, being sure to select a statement with the correct
 * level of challenge.
 *
 * So, for example, if the challenge level is 0 (starter), then this function should use a randomized
 * index between 0 and n (inclusive) to return an objective from the statement array that is
 * level 0 difficulty.  On the other hand, if the difficulty is 3 (master), then this function
 * should use a randomized index between 36 and 47 (inclusive) to return an objective from the
 * OBJECTIVES array that is level 3 difficulty.

 *
 * @param difficulty The difficulty of the game (1 - starter, 2 - junior, 3 - expert, 4 - master)
 * @return An objective at the appropriate level of difficulty.
 * 
 * Author: Hua Guo
 */

public class Challenges {
    private String statement;   // the starting statement
    private int Number;    // the number of original game (1 .. n)

    public Challenges(String statement,int Number) {
        assert Number >= 1 && Number <= 48;
        this.statement = statement;
        this.Number = Number;
    }

    public static Challenges newChallenge(int difficulty) {
        assert difficulty >= 1 && difficulty <= 4;
        int r = 1;
        switch (difficulty) {
            case 1:
                r = (int) (Math.random() * 12);
                break;
            case 2:
                r = (int) (Math.random() * 12 + 12);
                break;

            case 3:
                r = (int) (Math.random() * 12 + 24);
                break;

            case 4:
                r = (int) (Math.random() * 12 + 36);
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

 /*
   * This array defines a set of 48 pre-defined puzzle objectives.
   *
   * There are four categories of objective, according to four difficulty levels, with
   * 12 objectives per difficulty level, organized within the array as follows:
  * Starter: 0-11
  * Junior: 12-23
  * Expert: 24-35
  * Master: 36-47
  */
    static Challenges[] CHALLENGES = {
            new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",1),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",2),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",3),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",4),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",5),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",6),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",7),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",8),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",9),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",10),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",11),
         new Challenges("f3C4i6B0j2B0j1C0k3C0l4B0l5C0",12),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",13),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",14),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",15),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",16),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",17),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",18),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",19),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",20),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",21),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",22),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",23),
         new Challenges("i6B0j2B0j1C0k3C0l4B0l5C0",24),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",25),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",26),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",27),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",28),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",29),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",30),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",31),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",32),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",33),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",34),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",35),
         new Challenges("j2B0j1C0k3C0l4B0l5C0",36),
         new Challenges("j1C0k3C0l4B0l5C0",37),
         new Challenges("j1C0k3C0l4B0l5C0",38),
         new Challenges("j1C0k3C0l4B0l5C0",39),
         new Challenges("j1C0k3C0l4B0l5C0",40),
         new Challenges("j1C0k3C0l4B0l5C0",41),
         new Challenges("j1C0k3C0l4B0l5C0",42),
         new Challenges("j1C0k3C0l4B0l5C0",43),
         new Challenges("j1C0k3C0l4B0l5C0",44),
         new Challenges("j1C0k3C0l4B0l5C0",45),
         new Challenges("j1C0k3C0l4B0l5C0",46),
         new Challenges("j1C0k3C0l4B0l5C0",47),
         new Challenges("j1C0k3C0l4B0l5C0",48),
 };

}