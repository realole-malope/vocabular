package com.rom.works.vocabular.dto;

import lombok.Data;

@Data
public class Meaning implements BaseDTO{
    private Exclamation[] exclamation;
    private Noun[] noun;
    private Verb[] verb;
    private Abbreviation[] abbreviation;
}
