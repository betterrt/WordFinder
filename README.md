# WordFinder

## Objective

When given letters that could comprise a Scrabble rack, this program will create a list of all legal words that can be formed from the letters on that rack.
For example, if your rack had the letters 'c' 'm' 'a' 'l' you could rearrange the letters to form the words 'calm' or 'clam', but you could also form shorter words from a subset of the letters, e.g., 'lam' or 'am'. It's generally difficult to figure out all such sequences of the letters that form real words. So we need this program to help us win the game of Scrabble.

## Brief description of each Class

ScoreTable Class: I use a HashMap<Character, Integer> to store scores for
all letters(in upper case). The getScore() method will first convert each
letter in the given word into upper case, and then find the corresponding
score for them.

Rack Class: I use two String variables to store the information of the rack.
First I filter all the whitespaces in the rack, and store this rack in
originalRack. Then I filter other symbols so that only letters are left, and
store this rack in filteredRack. In the getAllSubsets() method, I use a
HashMap<Character, Integer> to record the number of occurrences of each letter
in the filteredRack. And I use an ArrayList<String> to store all subsets given
rack

AnagramDictionary Class: I use a HashMap<String, HashSet<String>> to store all
anagram sets. In each entry of the map, the key is the sorted version of the
word, and the value is the HashSet that stores all anagrams of that word. For
each word in the dictionary, I firstly turn it into sorted version, then add it
into the corresponding HashSet(the anagram set). Because we are using a HashSet,
it is easy for us to know whether the dictionary contains duplicate words or not.
The getAnagramsOf() method also turns the given string into sorted version, then
get the corresponding anagram set in the map and return it.

WordFinder Class: This class contains the main method to drive the program. In
the findAllWords() method, I use an ArrayList<String[]> to store all words that
the rack can form and their scores. For each string array in the ArrayList, string[0]
is the word and string[1] is its score.
