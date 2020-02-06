package com.rom.works.vocabular.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
