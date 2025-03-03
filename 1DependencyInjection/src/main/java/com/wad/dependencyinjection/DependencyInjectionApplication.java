package com.wad.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

    public static void main(String[] args) {
        ApplicationContext ctx =SpringApplication.run(DependencyInjectionApplication.class, args);
        MyDIApplication myApplication = (MyDIApplication) ctx.getBean("myDIApplication");
        myApplication.processMessages("sender","receiver");

    }

}
