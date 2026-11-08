package matthewyeend.spellcheck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author matthewyeend
 */
public final class SpellCheck {

    private static final String DEFAULT_DICTIONARY = "words";
    private static final String DEFAULT_DOCUMENT = "alice10.txt";
    private static final long MAX_FILE_SIZE_BYTES = 50L * 1024 * 1024;

    private SpellCheck() {}

    public static void main(String[] args) {
        String dictionaryName = args.length > 0 ? args[0] : DEFAULT_DICTIONARY;
        String documentName = args.length > 1 ? args[1] : DEFAULT_DOCUMENT;

        try {
            Set<String> dictionaryWords = readWords(dictionaryName);
            Set<String> documentWords = readWords(documentName);

            // TreeSet gives a sorted, deterministic output
            Set<String> misspelt = new TreeSet<>(documentWords);
            misspelt.removeAll(dictionaryWords);
            misspelt.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Unable to read input file: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Reads all words from a file.
     *
     * @param filename the name of the file
     * @return a set with all lower-cased words in the file. Here, a word is a
     * sequence of upper and lower case letters.
     * @throws IOException if the file is missing, unreadable, not a regular
     * file, or larger than the permitted size
     */
    public static Set<String> readWords(String filename) throws IOException {
        // toRealPath resolves symlinks and throws if the file does not exist
        Path path = Path.of(filename).toRealPath();

        if (!Files.isRegularFile(path) || !Files.isReadable(path)) {
            throw new IOException("Not a readable regular file: " + path);
        }
        if (Files.size(path) > MAX_FILE_SIZE_BYTES) {
            throw new IOException("File exceeds the permitted size: " + path);
        }

        Set<String> words = new HashSet<>();
        try (Scanner in = new Scanner(path, StandardCharsets.UTF_8)) {
            // Use any characters other than a-z or A-Z as delimiters
            in.useDelimiter("[^a-zA-Z]+");
            while (in.hasNext()) {
                words.add(in.next().toLowerCase(Locale.ROOT));
            }
        }
        return words;
    }
}