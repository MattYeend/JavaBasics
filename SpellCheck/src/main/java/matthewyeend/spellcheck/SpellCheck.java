/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.spellcheck;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author matthewyeend
 */
public class SpellCheck {
    public static void main(String[] args) throws FileNotFoundException {
        // Read the dictionary and the documents
        Set<String> dictionaryWords = readWords("words");
        Set<String> documentWords = readWords("alice10.txt");
        
        // Print all words that are in the document but not in dictionary
        for (String word : documentWords) {
            if (!dictionaryWords.contains(word)) {
                System.out.println(word);
            }
        }
    }
        
    /**
     * Reads all words from a file.
     * @param filename the name of the file
     * @return a set with all lower cased words in the file. Here, a word
     * is a sequence of upper and lower case letters.
     * @throws FileNotFoundException if the file cannot be found
     */
    public static Set<String> readWords(String filename) 
        throws FileNotFoundException {
        Set<String> words = new HashSet<String>();
        Scanner in = new Scanner(new File(filename));
        // User any characters other that a-z or A-Z as delimiters
        in.useDelimiter("[^a-zA-Z]+");
        while (in.hasNext()) {
            words.add(in.next().toLowerCase());
        }
        return words;
    }
}
