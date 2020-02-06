package com.rom.works.vocabular.exception;

public class DefinitionNotFoundException extends BusinessException {
    public DefinitionNotFoundException() {
        super("Sorry pal, we couldn't find definitions for the word you were looking for.");
    }
}
