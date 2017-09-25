import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    private static final Map<String, Integer> WORD_COUNT  = new HashMap<String, Integer>();

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
            if (WORD_COUNT.containsKey(word)) {
                WORD_COUNT.put(word, WORD_COUNT.get(word) + 1);
            } else {
                WORD_COUNT.put(word, 1);
            }
        }

        //System.out.println(contents);
        System.out.println(WORD_COUNT);
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
     * @return String nothing or an "s" for plurals
     * @param num number of items
     */
    private static String s(final int num) {
        if (num == 1) {
            return "";
        }
        return "s";
    }
}
