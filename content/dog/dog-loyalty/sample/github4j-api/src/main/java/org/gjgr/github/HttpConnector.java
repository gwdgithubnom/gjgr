package org.gjgr.github;

import org.gjgr.github.extras.ImpatientHttpConnector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Pluggability for customizing HTTP request behaviors or using altogether different library.
 *
 * <p>
 * For example, you can implement this to st custom timeouts.
 *
 * @author Kohsuke Kawaguchi
 */
public interface HttpConnector {
    /**
     * Opens a connection to the given URL.
     */
    HttpURLConnection connect(URL url) throws IOException;

    /**
     * Default implementation that uses {@link URL#openConnection()}.
     */
    HttpConnector DEFAULT = new ImpatientHttpConnector(new HttpConnector() {
        public HttpURLConnection connect(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    });

    /**
     * Stub implementation that is always off-line.
     */
    HttpConnector OFFLINE = new HttpConnector() {
        public HttpURLConnection connect(URL url) throws IOException {
            throw new IOException("Offline");
        }
    };
}
