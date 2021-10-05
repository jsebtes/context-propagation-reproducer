package org.acme;

import io.smallrye.mutiny.Uni;
import org.acme.context.MyContext;
import org.jboss.resteasy.reactive.RestHeader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/hello-resteasy-reactive")
public class ReactiveGreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello(@RestHeader("myValue") String myValue) {
        return Uni.createFrom().item(helloInternal(myValue));
    }

    @GET
    @Path("/blocking")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloBlocking(@RestHeader("myValue") String myValue) {
        return helloInternal(myValue);
    }

    private String helloInternal(String myValue) {
        if (Objects.equals(myValue, MyContext.getMyValue())) {
            return "Hello RESTEasy Reactive";
        }
        else {
            throw new RuntimeException("Context propagation does not work");
        }
    }

}