package com.consol.citrus.http.endpoint.builder.jakarta;

import com.consol.citrus.endpoint.builder.jakarta.ClientServerEndpointBuilder;
import com.consol.citrus.http.client.jakarta.HttpClientBuilder;
import com.consol.citrus.http.server.jakarta.HttpServerBuilder;

/**
 * @author Christoph Deppisch
 */
public final class HttpEndpoints extends ClientServerEndpointBuilder<HttpClientBuilder, HttpServerBuilder> {
    /**
     * Private constructor setting the client and server builder implementation.
     */
    private HttpEndpoints() {
        super(new HttpClientBuilder(), new HttpServerBuilder());
    }

    /**
     * Static entry method for Http client and server endpoint builder.
     * 
     * @return
     */
    public static HttpEndpoints http() {
        return new HttpEndpoints();
    }
}
