package org.acme.context;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import java.io.IOException;
import java.util.UUID;

public class MyContextRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(MyContextRequestFilter.class);

    @ServerRequestFilter(preMatching = true)
    public void filterResquestPreMatching(ContainerRequestContext requestContext, HttpServerRequest httpServerRequest) throws IOException {
        String myValue = UUID.randomUUID().toString();
        MyContext.setMyValue(myValue);
        httpServerRequest.headers().add("myValue", myValue);
    }

    @ServerResponseFilter
    public void filterResponse(ContainerResponseContext responseContext, HttpServerResponse httpServerResponse) {
        MyContext.clear();
    }

}
