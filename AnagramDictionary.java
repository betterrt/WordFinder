// Name: Ruitao Jiang
// USC NetID: ruitaoji
// CS 455 PA4
// Fall 2021

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
    // The map is the dictionary of all anagram sets.
    // The key is sorted version of the word
    // The value is the set that contains all anagrams of that word
    Map<String, Set<String>> anagramMap;

    /**
     * Create an anagram dictionary from the list of words given in the file
     * indicated by fileName.
     *
     * @param fileName the name of the file to read from
     * @throws FileNotFoundException      if the file is not found
     * @throws IllegalDictionaryException if the dictionary has any duplicate words
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException,
            IllegalDictionaryException {
        anagramMap = new HashMap<>();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNext()) {
            String word = in.next();
            // Turn the word into canonical form
            String sortedString = sortTheWord(word);
            if (!anagramMap.containsKey(sortedString)) {
                anagramMap.put(sortedString, new HashSet<>());
            }
            // Add the word into the set
            // And check if the dictionary has duplicate words
            boolean isLegal = anagramMap.get(sortedString).add(word);
            if (!isLegal) {
                in.close();
                throw new IllegalDictionaryException("ERROR: Illegal dictionary: " +
                        "dictionary file has a duplicate word: " + word);
            }
        }
        in.close();
    }

    /**
     * Get all anagrams of the given string. This method is case-sensitive.
     * E.g. "CARE" and "race" would not be recognized as anagrams.
     *
     * @param string string to process
     * @return a list of the anagrams of string
     */
    public ArrayList<String> getAnagramsOf(String string) {
        String sortedString = sortTheWord(string);
        ArrayList<String> result = null;
        // If the dictionary contains anagrams of the given string, return all of them
        // Otherwise, return null
        if (anagramMap.containsKey(sortedString)) {
            result = new ArrayList<>(anagramMap.get(sortedString));
        }
        return result;
    }

    /**
     * Find the canonical form(i.e. sorted version) of the characters in the given word.
     *
     * @param word the given word
     * @return a string that consists of sorted characters in the original word
     */
    private String sortTheWord(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }


}
