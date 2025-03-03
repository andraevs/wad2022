package com.wad.dependencyinjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"prod"})
@Component
public class MyDIApplication {
    private final MessageService service;

    public MyDIApplication(MessageService service) {
        this.service = service;
    }

    public void processMessages(String msg, String rec){
        this.service.sendMessage(msg, rec);}}


