package comp1110.ass2;

import java.util.Set;
import java.util.HashMap;
import java.lang.String;

/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */
public class TwistGame {

  /**
   * Determine whether a piece or peg placement is well-formed according to the following:
   * - it consists of exactly four characters
   * - the first character is in the range a .. l (pieces and pegs)
   * - the second character is in the range 1 .. 8 (columns)
   * - the third character is in the range A .. D (rows)
   * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
   *
   * @param piecePlacement A string describing a single piece or peg placement
   * @return True if the placement is well-formed
   */
  public static boolean isPlacementWellFormed(String piecePlacement) {
    // FIXME Task 2: determine whether a piece or peg placement is well-formed
      String first = "abcdefghijkl";
      String second = "12345678";
      String third = "ABCD";
      String fourth = "01234567";
      //check if it consists of exactly four characters
    if (piecePlacement.length() != 4) return false;
    else {
        for (int i = 0; i < 4; i++) {
            //check if the first character is in the range a .. l (pieces and pegs)
            if (i == 0) {
                if (first.indexOf(piecePlacement.charAt(i)) == -1) {
                    return false;
                }

            }
            //check if the second character is in the range 1 .. 8 (columns)
            else if (i == 1) {
                if (second.indexOf(piecePlacement.charAt(i)) == -1) {
                    return false;
                }

            }
            //check if the third character is in the range A .. D (rows)
            else if (i == 2) {
                if (third.indexOf(piecePlacement.charAt(i)) == -1) {
                    return false;
                }

            }
            //check if the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
            else {
                //check peg
                if (piecePlacement.charAt(0) == 'i' || piecePlacement.charAt(0) == 'j' || piecePlacement.charAt(0) == 'k' || piecePlacement.charAt(0) == 'l') {
                    if (piecePlacement.charAt(3) != '0') {
                        return false;
                    }

                }
                //check pieces
                else {
                    if (fourth.indexOf(piecePlacement.charAt(3)) == -1) {
                        return false;
                    }

                }

            }

        }

        return true;
    }

  }

  /**
   * Determine whether a placement string is well-formed:
   * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
   * - each piece or peg placement is well-formed
   * - each piece or peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
   * - no piece or red peg appears more than once in the placement
   * - no green, blue or yellow peg appears more than twice in the placement
   *
   * @param placement A string describing a placement of one or more pieces and pegs
   * @return True if the placement is well-formed
   */
  public static boolean isPlacementStringWellFormed(String placement) {

      // //check if the placement string consists of exactly N four-character piece placements (where N = 1 .. 15);
      if (placement.length() % 4 != 0 || placement.length() / 4 > 15 || placement.length() / 4 < 1) {
          return false;
      }

      // check if each 4-character placement string is valid
      for (int j = 0; j < placement.length() / 4; j++) {
          if (isPlacementWellFormed(placement.substring(4 * j, 4 * j + 4)) == false) {
              return false;
          }

      }

      // check if each piece or peg placement occurs in the correct alphabetical order
      for (int k = 0; k < placement.length() / 4 - 1; k++) {
          if (placement.charAt(4 * k) > placement.charAt(4 * k + 4)) {
              return false;
          }
      }

      // check duplicates
      //find a string of the first letter of every four-letter
      String s = "";
      for (int m = 0; m < placement.length() / 4; m++) {
          s = s + placement.charAt(4 * m);
      }

      //establish a map of the first letter of every four-letter
      HashMap<String, Integer> map = new HashMap<String, Integer>();

      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          String key = String.valueOf(c);
          if (map.containsKey(key)) {
              map.put(key, map.get(key) + 1);
          } else {
              map.put(key, 1);
          }
      }

      //check the duplications of pegs and pieces
      // no piece or red peg can appear more than once in the placement
      //no green, blue or yellow peg appears more than twice in the placement

      for (String keys : map.keySet()) {
          if (keys.equals("j") || keys.equals("k") || keys.equals("l")) {
              if (map.get(keys) > 2) {
                  return false;
              }

          } else if (map.get(keys) > 1) {
              return false;
          }

      }
    // FIXME Task 3: determine whether a placement is well-formed
      return true;
  }

  /**
   * Determine whether a placement string is valid.  To be valid, the placement
   * string must be well-formed and each piece placement must be a valid placement
   * according to the rules of the game.
   * - pieces must be entirely on the board
   * - pieces must not overlap each other
   * - pieces may only overlap pegs when the a) peg is of the same color and b) the
   *   point of overlap in the piece is a hole.
   *
   * @param placement A placement sequence string
   * @return True if the placement sequence is valid
   */
  public static boolean isPlacementStringValid(String placement) {
    // FIXME Task 5: determine whether a placement string is valid
    return false;
  }

  /**
   * Given a string describing a placement of pieces and pegs, return a set
   * of all possible next viable piece placements.   To be viable, a piece
   * placement must be a valid placement of a single piece.  The piece must
   * not have already been placed (ie not already in the placement string),
   * and its placement must be valid.   If there are no valid piece placements
   * for the given placement string, return null.
   *
   * When symmetric placements of the same piece are viable, only the placement
   * with the lowest rotation should be included in the set.
   *
   * @param placement A valid placement string (comprised of peg and piece placements)
   * @return An set of viable piece placements, or null if there are none.
   */
  public static Set<String> getViablePiecePlacements(String placement) {
    // FIXME Task 6: determine the set of valid next piece placements
    return null;
  }

  /**
   * Return an array of all unique solutions for a given starting placement.
   *
   * Each solution should be a 32-character string giving the placement sequence
   * of all eight pieces, given the starting placement.
   *
   * The set of solutions should not include any symmetric piece placements.
   *
   * In the IQ-Twist game, valid challenges can have only one solution, but
   * other starting placements that are not valid challenges may have more
   * than one solution.  The most obvious example is the unconstrained board,
   * which has very many solutions.
   *
   * @param placement A valid piece placement string.
   * @return An array of strings, each 32-characters long, describing a unique
   * unordered solution to the game given the starting point provided by placement.
   */
  public static String[] getSolutions(String placement) {
    // FIXME Task 9: determine all solutions to the game, given a particular starting placement
    return null;
  }
}
