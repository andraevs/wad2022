package com.wad.dependencyinjection;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"prod"})
@Service
public class SMSService implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        System.out.println("SMS sent to "+rec+ " with message="+msg);}}


