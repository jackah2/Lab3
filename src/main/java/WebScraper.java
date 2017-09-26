import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Jack Henhapl
 * Scrapes content from the web
 */
public class WebScraper {

    /**
     * URL The URL to a website.
     * WORD A word to be counted
     */
    private static final String URL = "http://erdani.com/tdpl/hamlet.txt",
            WORD = "Prince";

    /**
     * A map to store words and how many times they show up.
     */
    private static final Set<String> UNIQUE_WORDS  = new HashSet<String>();

    /**
     *
     * @param unused args from the main method
     */
    public static void main(final String[] unused) {
        String contents = urlToString(URL);
        String[] words = contents.split("\\s+");

        int count = 0;
        for (String word:words) {
            if (word.toLowerCase().indexOf(WORD.toLowerCase()) >= 0) {
                count++;
            }
        }

        String current;
        for (int ind = 0; ind < words.length; ind++) {
            current = words[ind];
            current = current.replace(".", "");
            current = current.replace(",", "");
            current = current.replace("!", "");
            current = current.replace("?", "");
            current = current.replace("\"", "");
            current = current.replace("[", "");
            current = current.replace("]", "");
            current = current.replace("(", "");
            current = current.replace(")", "");
            words[ind] = current;
        }

        for (String word:words) {
            if (word.equals("")) {
                continue;
            }
            if (!UNIQUE_WORDS.contains(word)) {
                UNIQUE_WORDS.add(word);
            }
        }

        System.out.println(UNIQUE_WORDS.size() + " unique words");
        System.out.println(words.length + " word" + s(words.length) + " on page \"" + URL + "\"");
        System.out.println(WORD + " is found on the page " + count
                + " time" + s(count));
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * @return String gives an "s" if there is "num" is more than 1 (Used for plurals).
     * @param num number of items
     */
    private static String s(final int num) {
        if (num == 1) {
            return "";
        }
        return "s";
    }
}
