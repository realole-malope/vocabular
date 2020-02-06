package com.rom.works.vocabular.dto;

import lombok.Data;

@Data
public class DefinitionResponse implements BaseDTO {
    private String word;
    private Meaning meaning;
}
