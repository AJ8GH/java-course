package section21.network.highlevelapis;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Slf4j
public class Connections {
    private static final String CAT_URL = "https://api.flickr.com/services/feeds/photos_public.gne?tags=cats";
    private static final String EXAMPLE_URL = "http://example.org";
    private static final String BAD_URL = "http://example.org/somepage.html";
    private static final URI BASE_URI = getBaseUri();

    public static void main(String[] args) {
        resolveUris();

        try {
            URL url = new URL(EXAMPLE_URL);
            URL badUrl = new URL(BAD_URL);
            URL catUrl = new URL(CAT_URL);
            readWebPage(url);

            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            readWebPageWithUrlConnection(urlConnection);

            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            printHeaders(headerFields);

            HttpURLConnection connection = (HttpsURLConnection) catUrl.openConnection();connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(10000);
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                log.error("Error reading web page: " + responseCode + " " + connection.getResponseMessage());
                return;
            }
            readWebPageWithUrlConnection(connection);

        } catch (MalformedURLException e) {
            log.error("Malformed URL Exception: {}", e.getMessage());
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage());
        }
    }

    private static void readWebPage(URL url) {
        try {
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line = "";
            log.info("Reading {}", url);
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage());
        }
    }

    private static void readWebPageWithUrlConnection(URLConnection urlConnection) {
        try {
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            log.info("Reading {}", urlConnection.getURL());
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage());
        }
    }

    private static void resolveUris() {
        try {
            URI relativeUri1 = new URI("/catalogue/phones?os=android#samsung");
            URI relativeUri2 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI relativeUri3 = new URI("/stores/locations?zip=12345");

            URI resolvedUri1 =  BASE_URI.resolve(relativeUri1);
            URI resolvedUri2 =  BASE_URI.resolve(relativeUri2);
            URI resolvedUri3 =  BASE_URI.resolve(relativeUri3);

            URL url1 =  resolvedUri1.toURL();
            URL url2 =  resolvedUri2.toURL();
            URL url3 =  resolvedUri3.toURL();

            log.info("URL 1: {}", url1);
            log.info("URL 2: {}", url2);
            log.info("URL 3: {}", url3);

            URI relativisedUri = BASE_URI.relativize(resolvedUri2);
            log.info("relative URI: {}", relativisedUri);

        } catch (URISyntaxException e) {
            log.error("URI Syntax Exception: {}", e.getMessage());
        } catch (MalformedURLException e) {
            log.error("Malformed URL Exception: {}", e.getMessage());
        }
    }

    private static void printHeaders(Map<String, List<String>> headers) {
        System.out.println("==== Headers ====");
        headers.forEach((key, value) -> {
            System.out.print("Header - " + key + ": " );
            value.forEach(v -> System.out.print(v + "\n"));
        });
    }

    private static URI getBaseUri() {
        try {
            return new URI("http://username:password@myserver.com:5000");
        } catch (URISyntaxException e) {
            log.error("URI Syntax Exception: {}", e.getMessage());
        }
        return null;
    }
}
