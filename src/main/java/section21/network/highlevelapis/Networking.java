package section21.network.highlevelapis;

import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class Networking {
    public static void main(String[] args) {
        try {
            URI absoluteUri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            URI baseUri = new URI("http://username:password@myserver.com:5000");
            URI relativeUri = new URI("/catalogue/phones?os=android#samsung");
            URI httpUri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            URI fakeUri = new URI("hello");

            printUriParts(absoluteUri);
            printUriParts(fakeUri);

            URL url = httpUri.toURL();
            log.info(url.toString());

            try {
                URI resolvedUri = baseUri.resolve(relativeUri);
                log.info("resolved URI: {}", resolvedUri);
                relativeUri.toURL();
            } catch (IllegalArgumentException e) {
                log.error("Illegal arg! {}", e.getMessage());
            }
            url = absoluteUri.toURL();

        } catch (URISyntaxException e) {
            log.error("URI Syntax Exception: {}", e.getMessage());
        } catch (MalformedURLException e) {
            log.error("Malformed URL Exception: {}", e.getMessage());
        }
    }

    private static void printUriParts(URI uri) {
        log.info("scheme: {}", uri.getScheme());
        log.info("scheme-specific-part: {}", uri.getSchemeSpecificPart());
        log.info("authority: {}", uri.getAuthority());
        log.info("user-info: {}", uri.getUserInfo());
        log.info("host: {}", uri.getHost());
        log.info("port: {}", uri.getPort());
        log.info("path: {}", uri.getPath());
        log.info("query: {}", uri.getQuery());
        log.info("fragment: {}", uri.getFragment());
    }
}
