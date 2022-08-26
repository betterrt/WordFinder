// Name: Ruitao Jiang
// USC NetID: ruitaoji
// CS 455 PA4
// Fall 2021

import java.util.*;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   // This variable stores the rack that entered by user with all whitespaces filtered
   private String originalRack;
   // This variable stores the rack that entered by user with only letters remained
   private String filteredRack;

   public Rack(String rack) {
      String regex1 = "[\\s]";
      String regex2 = "[^a-zA-Z]";
      originalRack = rack.replaceAll(regex1, "");
      filteredRack = rack.replaceAll(regex2, "");

   }

   /**
    * Find all subsets of the multiset of letters that a given rack contains.
    *
    * @return an ArrayList which contains all subsets of the given rack
    */
   public ArrayList<String> getAllSubsets() {
      // Create a map to store all the letters in rack and the multiplicity of each letter
      Map<Character, Integer> lettersAndMultiplicity = new HashMap<>();
      // Find all letters in the rack and their multiplicity
      for (int i = 0; i < filteredRack.length(); i++) {
         char theChar = filteredRack.charAt(i);
         if (lettersAndMultiplicity.containsKey(theChar)) {
            int multiplicity = lettersAndMultiplicity.get(theChar) + 1;
            lettersAndMultiplicity.put(theChar, multiplicity);
            continue;
         }
         lettersAndMultiplicity.put(theChar, 1);
      }
      // The StringBuilder that only contains unique letters
      StringBuilder unique = new StringBuilder();
      // The array that indicates multiplicity of each letter
      int[] mult = new int[lettersAndMultiplicity.size()];
      int count = 0;
      Iterator<Map.Entry<Character, Integer>> iterator = lettersAndMultiplicity.entrySet().iterator();
      while (iterator.hasNext()) {
         Map.Entry<Character, Integer> entry = iterator.next();
         unique.append(entry.getKey());
         mult[count] = entry.getValue();
         count++;
      }
      ArrayList<String> allCombos = allSubsets(unique.toString(), mult, 0);
      return allCombos;
   }


   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    * unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    * 0 <= k <= unique.length()
    *
    * @param unique a string of unique letters
    * @param mult   the multiplicity of each letter from unique.
    * @param k      the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    * each subset is represented as a String that can have repeated characters in it.
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos.  Suppose that char is 'a'...

      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
            // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }

      return allCombos;
   }

   public String getOriginalRack() {
      return originalRack;
   }


}
