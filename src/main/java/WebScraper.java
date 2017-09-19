import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Jack Henhapl
 * Scrapes content from the web
 */
public class WebScraper {

    /**
     * URL The URL to a website.
     */
    private static final String URL = "https://pastebin.com/raw/U7nPzRGJ";

    /**
     *
     * @param unused args from the main method
     */
    public static void main(final String[] unused) {
        String contents = urlToString(URL);

        System.out.println(contents);
        System.out.println(contents.split("\\s+").length + " words on page \"" + URL + "\"");
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
}
