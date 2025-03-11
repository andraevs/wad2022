package com.wad.dependencyinjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"dev"})
@Configuration
public class DIConfig {

    @Bean
    public MessageService emailService(){
        return new EmailService();
    }
    @Bean
    public MyDIApplication myDIApplication(MessageService service){
        return new MyDIApplication(service);
    }
}
