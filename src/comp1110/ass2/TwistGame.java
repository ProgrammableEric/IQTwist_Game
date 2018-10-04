package comp1110.ass2;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.String;
import java.util.HashSet;
import java.util.Iterator;
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
   *
   * Author: Hua Guo
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
   *
   * Author: Hua Guo
   */
  public static boolean isPlacementStringWellFormed(String placement) {

      //check if the placement string consists of exactly N four-character piece placements (where N = 1 .. 15);
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
      // find a string of the first letter of every four-letter
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
      //no piece or red peg can appear more than once in the placement
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
   *
   * Author： Chunze Fu (u5885811)
   */
  public static boolean isPlacementStringValid(String placement) {

      Node[] nodes = new Node[32];


      for (int i =0; i < nodes.length; i++){
          nodes[i] = new Node(0, null, null);
          }

      // check if the placement string is well formed
      if (!isPlacementStringWellFormed(placement)) {
          //System.out.println("This is not a valid placement string !");
          return false;
      }

      for (int j = 0; j < placement.length() / 4; j++){

          String temp = placement.substring(j*4,(j+1)*4);
          //System.out.println(temp);

          // if the given string represents a piece
          if (isPiece(temp)){
              //System.out.println("got a piece");
              //System.out.println(temp.charAt(0));

              // initialize a new piece
            Piece newPiece = new Piece(temp);
            int keyPos = newPiece.keyPosition;
            int[] positions = newPiece.positions;
            int[] pieceValues = newPiece.pieceValues;


            // check if the piece is entirely on board
            if ( !newPiece.isOnBoard() ) {
                //System.out.println("A piece has been put outside the game board boundary");
                return false;
            }


            // put the piece onto the game board, check if there's clash between pieces
            for (int i = 0; i < newPiece.positions.length; i++){
                int id = newPiece.keyPosition + newPiece.positions[i];
                if (!nodes[id].pieceHere()) {
                    nodes[id].piece = newPiece;
                    nodes[id].pieceValue = newPiece.pieceValues[i];
                } else{
                    //System.out.println(id);
                    //System.out.println("Pieces clash! ");
                    return false;
                }
            }
            // put the peg onto the board
          } else if (isPeg(temp)){
              //System.out.println("got a peg");
              Peg newPeg = new Peg(temp);
              int id2 = newPeg.position;
              nodes[id2].peg = newPeg;

          } else {
              //System.out.println("here");
              return false;}

      }

      // check if each Piece and Peg matches according to the game rule
        for (int j = 0; j < nodes.length; j++){
          if (nodes[j].pegHere()){
              if (nodes[j].pieceHere()){
                  if (nodes[j].peg.colour != nodes[j].piece.colour ) {
                      //System.out.println("Bad peg placement! ");
                      return false;}
                  if (nodes[j].pieceValue != 2)
                      {//System.out.println("Bad peg placement! ");
                      return false;}
              }
          }
        }


        return true;

    // FIXME Task 5: determine whether a placement string is valid

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
   *
   * Author： Chunze Fu (u5885811)
   */
  public static Set<String> getViablePiecePlacements(String placement) {

    Set <String> ans = new HashSet<String>();
    //ans = null;

    Set <String> pieceIDs = new HashSet<String>();
    for (char i = 'a'; i <= 'h'; i++){pieceIDs.add(String.valueOf(i));}


    for (int j = 0; j < placement.length() / 4; j++){

          String temp = placement.substring(j*4,(j+1)*4);
          if (isPiece(temp)){
              pieceIDs.remove(String.valueOf(temp.charAt(0)));
          }
    }

    String temp2 = "";

    for (String k : pieceIDs){
        for (char col = '1'; col <= '8'; col++) {
            for (char row = 'A'; row <= 'D'; row++) {
                for (char ori = '0'; ori <= '7'; ori++) {

                    String putPiece = k + col + row + ori;

                    // combine the selected piece into the original piece placement string

                    temp2 = GetNewPlacement(placement,putPiece);

                    // check symmetry
                    if (isPlacementStringValid(temp2)) {
                        switch (putPiece.charAt(0)){

                            case 'a': case 'd': case 'g':
                                ans.add(putPiece);break;

                            case 'c': case 'h':

                                if (putPiece.charAt(3) >= '2' && putPiece.charAt(3) < '4') {

                                    if (!(ans.contains(symmetry(putPiece)))) { ans.add(putPiece);break;}
                                    else break;

                                } else if (putPiece.charAt(3) >= '0' && putPiece.charAt(3) < '2') { ans.add(putPiece); break;}

                                else break;

                            case 'b':


                                if (putPiece.charAt(3) == '2' || putPiece.charAt(3) == '3' ||
                                        putPiece.charAt(3) == '6' || putPiece.charAt(3) == '7'){

                                    if (!(ans.contains(symmetry(putPiece)))) {ans.add(putPiece);break;}
                                    else break;

                                } else {ans.add(putPiece); break;}


                            case 'e': case 'f':

                                if (putPiece.charAt(3) >= '4' && putPiece.charAt(3) < '8') {

                                    if (!ans.contains(symmetry(putPiece))) {ans.add(putPiece);break;}
                                    else break;

                                } else if (putPiece.charAt(3) >= '0' && putPiece.charAt(3) < '4') {ans.add(putPiece); break;}

                                else break;

                        }


                        }
                    }
                }
            }
        }



    // FIXME Task 6: determine the set of valid next piece placements
      if (ans.isEmpty())return null;
      else return ans;

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
   * Author: Hua Guo
   *
   * @param placement A valid piece placement string.
   * @return An array of strings, each 32-characters long, describing a unique
   * unordered solution to the game given the starting point provided by placement.
   */

  public static ArrayList<String> solutions = new ArrayList<>();

  public static String[] getSolutions(String placement) {

      // find all solutions for this initial placement
      String[] solu = Allsolution(placement);

      solutions.clear();
      return solu;


    // FIXME Task 9: determine all solutions to the game, given a particular starting placement
  }


  /** check if the game is complete by examining piece states and piece orientations
   *
   * @param pieceState          A string of 8 integer representing the state of each piece. For each piece it is either
   *                            encoded as -1 (not placed), or is the node index of its key position. (refer to Node class.)
   * @param pieceOrientation    A string of 8 integer representing the state of each piece. Denoted as int 0 - 7
   * @param pegPlacementString  String representing all the peg placements from initial placement.
   *
   * @return  true if the game if complete.
   *
   * Autho: Chunze Fu (u5885811)
   */
  public static boolean updateAndCheck (int[] pieceState, int[] pieceOrientation, String pegPlacementString){

        String toCheck = "";
        boolean onBoard = true;
        boolean finished;

        for (int i = 0; i < pieceState.length; i ++){
            if (pieceState[i] == -1) onBoard = false;
        }

        if (onBoard){
            for (int i = 0; i < pieceState.length; i ++){
                toCheck += String.valueOf((char) ('a' + i)) + Piece.posEncode(pieceState[i]) + String.valueOf(pieceOrientation[i]);
            }
            finished = isPlacementStringValid(toCheck + pegPlacementString);
            return finished;

        } else return false;

    }

  // check if a 4-character placement string represents a piece
  public static boolean isPiece (String piecePlacement){
      return (piecePlacement.charAt(0) >= 'a' && piecePlacement.charAt(0) <= 'h');
  }

  // check if a 4-character placement string represents a piece
  public static boolean isPeg (String piecePlacement){
      return (piecePlacement.charAt(0) >= 'i' && piecePlacement.charAt(0) <= 'l');
  }

  /** given an input string representing a piece, compute the placement string of its symmetry (weak symmetry, strong symmety is ignored in the game logic)
   *
   *  Author: Chunze Fu (u5885811)
   */
  public static String symmetry (String placement){
      String ans = null;

      switch (placement.charAt(0)){
          case 'c': case 'h':

              switch (placement.charAt(3)){
                  case '3': {ans = placement.substring(0,3) + '1';break;}
                  case '2': {ans = placement.substring(0,3) + '0';break;}
              }break;

           case 'b':

              switch (placement.charAt(3)){
                  case '2': {ans = placement.substring(0,3) + '0';break;}
                  case '3': {ans = placement.substring(0,3) + '1';break;}
                  case '6': {ans = placement.substring(0,3) + '4';break;}
                  case '7': {ans = placement.substring(0,3) + '5';break;}
              }break;

          case 'e':

              switch (placement.charAt(3)){
                  case '4': {ans = placement.substring(0,3) + '1';break;}
                  case '5': {ans = placement.substring(0,3) + '2';break;}
                  case '6': {ans = placement.substring(0,3) + '3';break;}
                  case '7': {ans = placement.substring(0,3) + '0';break;}
              }break;


          case 'f':

              switch (placement.charAt(3)){
                  case '4': {ans = placement.substring(0,3) + '2';break;}
                  case '5': {ans = placement.substring(0,3) + '3';break;}
                  case '6': {ans = placement.substring(0,3) + '0';break;}
                  case '7': {ans = placement.substring(0,3) + '1';break;}
              }break;
      }
      return ans;
  }


  // combine the selected piece into the original piece placement string
  public static String GetNewPlacement(String placement, String piece){
      String newplacement = "";
      if (piece.charAt(0) < placement.charAt(0)){
          newplacement = piece + placement;
      }
      else if(piece.charAt(0) > placement.charAt(placement.length()-4)){
          newplacement = placement + piece;
      }
      else {
          for (int i = 0; i<placement.length(); i+=4){
              if (placement.charAt(i) < piece.charAt(0) && placement.charAt(i+4) > piece.charAt(0)){
                  newplacement = placement.substring(0,i+4) + piece + placement.substring(i+4,placement.length());
              }
          }
      }
      return newplacement;
  }


  // check whether this placement is a solution
  public static boolean IsSolution(String placement){
      String temp="";
      for (int j = 0; j < placement.length() / 4; j++) {

          temp += placement.charAt(j * 4);
      }
      return temp.contains("abcdefgh");
  }


  // find all solutions for an input placement
  public static String[] Allsolution(String placement){

      //check whether this solution is right
      if (IsSolution(placement)){
          solutions.add(placement.substring(0,32));
      }

      //find the next step and store them in a set
      Set <String> next_step = getViablePiecePlacements(placement);
      ArrayList<String> SolutionNextStep = new ArrayList<>();

      // combine every next step piece and initial placement and store them as new placements
      if (next_step!=null){
          for (String i: next_step){
              SolutionNextStep.add(GetNewPlacement(placement,i));
          }
      }

      //recurse to get next step until there is no next step
      for(String j: SolutionNextStep){
          Allsolution(j);
      }

      // translate arraylist to String[]
      String[] solution = solutions.toArray(new String[0]);

      //eliminate duplicate solutions
      TreeSet<String> ans = new TreeSet<>();
      for (int i=0; i<solution.length;i++){
          ans.add(solution[i]);
      }
      String[] UniqueSolution = new String[ans.size()];
      for (int i=0; i<UniqueSolution.length; i++){
          UniqueSolution[i] = ans.pollFirst();
      }

      return UniqueSolution;

  }



  }




