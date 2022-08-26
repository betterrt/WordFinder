// Name: Ruitao Jiang
// USC NetID: ruitaoji
// CS 455 PA4
// Fall 2021

import java.util.*;

/**
 * This class calculate the score of a given word
 */

public class ScoreTable {
   // The map to store all the letters and their corresponding scores.
   private final Map<Character, Integer> scoresForLetters;
   // Constants to store the letters that have same scores.
   public static final char[] onePointLetters = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R'};
   public static final char[] twoPointsLetters = {'D', 'G'};
   public static final char[] threePointsLetters = {'B', 'C', 'M', 'P'};
   public static final char[] fourPointsLetters = {'F', 'H', 'V', 'W', 'Y'};
   public static final char[] fivePointsLetters = {'K'};
   public static final char[] eightPointsLetters = {'J', 'X'};
   public static final char[] tenPointsLetters = {'Q', 'Z'};

   // The constructor that initializes the map scoresForLetters.
   public ScoreTable() {
      scoresForLetters = new HashMap<>();
      ArrayList<char[]> letters = new ArrayList<>();
      letters.add(onePointLetters);
      letters.add(twoPointsLetters);
      letters.add(threePointsLetters);
      letters.add(fourPointsLetters);
      letters.add(fivePointsLetters);
      int point = 0;
      // Add letters and their scores to the map.
      for (char[] array : letters) {
         point++;
         for (char letter : array) {
            scoresForLetters.put(letter, point);
         }
      }
      for (char letter : eightPointsLetters) {
         scoresForLetters.put(letter, 8);
      }
      for (char letter : tenPointsLetters) {
         scoresForLetters.put(letter, 10);
      }
   }

   /**
    * Calculate the score of the given word. Both upper and lower case versions of a letter
    * will have the same score.
    *
    * @param word The given word
    * @return The score of the given word
    */
   public int getScore(String word) {
      int score = 0;
      for (int i = 0; i < word.length(); i++) {
         char theChar = Character.toUpperCase(word.charAt(i));
         score += scoresForLetters.get(theChar);
      }
      return score;
   }

}
