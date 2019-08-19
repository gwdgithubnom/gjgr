package org.gjgr.github.extras;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import org.gjgr.github.HttpConnector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * {@link HttpConnector} for {@link OkHttpClient}.
 *
 * Unlike {@link #DEFAULT}, OkHttp does response caching.
 * Making a conditional request against GitHubAPI and receiving a 304
 * response does not count against the rate limit.
 * See http://developer.github.com/v3/#conditional-requests
 *
 * @author Roberto Tyley
 * @author Kohsuke Kawaguchi
 */
public class OkHttpConnector implements HttpConnector {
    private final OkUrlFactory urlFactory;

    public OkHttpConnector(OkUrlFactory urlFactory) {
        this.urlFactory = urlFactory;
    }

    public HttpURLConnection connect(URL url) throws IOException {
        return urlFactory.open(url);
    }
}
