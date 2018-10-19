package pjiang.tutorial.ignite;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  14:04 2018/10/18.
 */
@SpringBootApplication
public class IgniteServerApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(IgniteServerApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
