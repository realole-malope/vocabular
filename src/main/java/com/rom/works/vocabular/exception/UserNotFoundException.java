package com.rom.works.vocabular.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("Sorry pal, user not found.");
    }
}
