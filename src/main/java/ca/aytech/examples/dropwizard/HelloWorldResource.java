package ca.aytech.examples.dropwizard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by amin on 2017-01-11.
 */
@Path("/")
public class HelloWorldResource {
    @GET
    public String hello(){
        return "hello world - dropwizard app engine flex";
    }

}
