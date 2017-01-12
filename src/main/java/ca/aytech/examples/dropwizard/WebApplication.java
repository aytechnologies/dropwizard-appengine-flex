package ca.aytech.examples.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by amin on 11/01/2017.
 */
public class WebApplication extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new WebApplication().run(args);
    }


    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new HelloWorldResource());
    }
}
