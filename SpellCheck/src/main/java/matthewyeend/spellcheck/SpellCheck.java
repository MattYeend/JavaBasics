package matthewyeend.spellcheck;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author matthewyeend
 */
public final class SpellCheck {

    private static final String DICTIONARY_RESOURCE = "/words";
    private static final String DOCUMENT_RESOURCE = "/alice10.txt";
    private static final int MAX_RESOURCE_BYTES = 50 * 1024 * 1024;

    private SpellCheck() {
        // Utility class, not intended to be instantiated
    }

    public static void main(String[] args) {
        try {
            Set<String> dictionaryWords = readWords(DICTIONARY_RESOURCE);
            Set<String> documentWords = readWords(DOCUMENT_RESOURCE);

            // TreeSet gives a sorted, deterministic output
            Set<String> misspelt = new TreeSet<>(documentWords);
            misspelt.removeAll(dictionaryWords);
            misspelt.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Unable to read input: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Reads all words from a class path resource.
     *
     * @param resourceName the absolute class path name, for example "/words"
     * @return a set with all lower-cased words in the resource. Here, a word
     * is a sequence of upper and lower case letters.
     * @throws IOException if the resource is missing, unreadable or larger
     * than the permitted size
     */
    static Set<String> readWords(String resourceName) throws IOException {
        try (InputStream resource = SpellCheck.class.getResourceAsStream(resourceName)) {
            if (resource == null) {
                throw new FileNotFoundException(
                        "Resource not found on classpath: " + resourceName);
            }

            // Read one byte past the limit so oversized input can be detected
            byte[] data = resource.readNBytes(MAX_RESOURCE_BYTES + 1);
            if (data.length > MAX_RESOURCE_BYTES) {
                throw new IOException("Resource exceeds the permitted size: " + resourceName);
            }

            Set<String> words = new HashSet<>();
            try (Scanner in = new Scanner(new ByteArrayInputStream(data),
                    StandardCharsets.UTF_8)) {
                // Use any characters other than a-z or A-Z as delimiters
                in.useDelimiter("[^a-zA-Z]+");
                while (in.hasNext()) {
                    words.add(in.next().toLowerCase(Locale.ROOT));
                }
            }
            return words;
        }
    }
}
