// Name: Ruitao Jiang
// USC NetID: ruitaoji
// CS 455 PA4
// Fall 2021


import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {
   // Main method
   // Read in the user input and return all the words in the dictionary that rack can form
   public static void main(String[] args) {
      AnagramDictionary anagramDictionary = null;
      // Error checking
      try {
         if (args.length == 0) {
            anagramDictionary = new AnagramDictionary("sowpods.txt");
         } else {
            anagramDictionary = new AnagramDictionary(args[0]);
         }
      } catch (FileNotFoundException e) {
         System.out.println("ERROR: Dictionary file \"" + args[0] + "\" does not exist.");
         System.out.println("Exiting program.");
         return;
      } catch (IllegalDictionaryException e) {
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
         return;
      }
      Scanner in = new Scanner(System.in);
      System.out.println("Type . to quit.");
      while (true) {
         System.out.print("Rack? ");
         Rack rack = new Rack(in.nextLine());
         if (rack.getOriginalRack().equals(".")) {
            break;
         }
         ArrayList<String[]> allWordsAndScores = findAllWords(anagramDictionary, rack);
         sortTheWords(allWordsAndScores);
         System.out.println("We can make " + allWordsAndScores.size() + " words from \"" + rack.getOriginalRack() + "\"");
         // If the rack can form at least one word
         if (allWordsAndScores.size() > 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            for (String[] wordAndScore : allWordsAndScores) {
               System.out.println(wordAndScore[1] + ": " + wordAndScore[0]);
            }
         }
      }
      in.close();
   }

   /**
    * Find all the words that the given rack can form, and calculate the corresponding score of each word.
    * Then store them in an ArrayList<String[]>. For each string[] in the ArrayList, string[0] is the word
    * and string[1] is the score.
    *
    * @param anagramDictionary the anagram dictionary used to find all the anagrams of a word
    * @param rack the rack
    * @return an ArrayList that consists of string arrays which stores words and their scores
    */
   private static ArrayList<String[]> findAllWords(AnagramDictionary anagramDictionary, Rack rack) {
      ScoreTable scoreTable = new ScoreTable();
      // Store all words that the rack can form and their scores
      ArrayList<String[]> allWordsAndScores = new ArrayList<>();
      // Get all subsets of multiset of letters the rack contains
      ArrayList<String> allCombos = rack.getAllSubsets();
      // For each combo, we need to find all anagrams of it
      for (String combo : allCombos) {
         ArrayList<String> anagrams = anagramDictionary.getAnagramsOf(combo);
         // If we can find anagrams of given combo(some combos can not form any words),
         // store them and their scores in the ArrayList
         if (anagrams != null) {
            for (String word : anagrams) {
               int score = scoreTable.getScore(word);
               String[] wordAndScore = new String[]{word, String.valueOf(score)};
               allWordsAndScores.add(wordAndScore);
            }
         }
      }
      return allWordsAndScores;
   }

   /**
    * Sort the ArrayList so that the words with higher scores come first.
    * If two or more words have identical scores, then they are sorted in the alphabetical order.
    *
    * @param allWordsAndScores the ArrayList that stores all the words and their scores
    */
   private static void sortTheWords(ArrayList<String[]> allWordsAndScores) {
      Collections.sort(allWordsAndScores, new Comparator<String[]>() {
         public int compare(String[] string1, String[] string2) {
            // If string1 and string2 has same score, sort them in alphabetical order
            if (Integer.parseInt(string1[1]) == Integer.parseInt(string2[1])) {
               return string1[0].compareTo(string2[0]);
            }
            // If string1 and string2 have different scores, sort them in order of scores
            else {
               return Integer.parseInt(string2[1]) - Integer.parseInt(string1[1]);
            }
         }
      });

   }

}
