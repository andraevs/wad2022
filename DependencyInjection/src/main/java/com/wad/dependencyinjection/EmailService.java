package com.wad.dependencyinjection;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Primary
@Service
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        System.out.println("Email sent to "+rec+ " with message="+msg);}}