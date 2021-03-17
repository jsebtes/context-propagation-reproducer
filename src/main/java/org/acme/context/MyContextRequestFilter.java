package org.acme.context;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;
import java.util.UUID;

public class MyContextRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(MyContextRequestFilter.class);

    @ServerRequestFilter(preMatching = true)
    public void filter(ContainerRequestContext requestContext, HttpServerRequest httpServerRequest) throws IOException {
        String myValue = UUID.randomUUID().toString();
        MyContext.setMyValue(myValue);
        httpServerRequest.headers().add("myValue", myValue);
    }

}
