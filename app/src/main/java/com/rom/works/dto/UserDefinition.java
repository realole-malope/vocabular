package com.rom.works.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"userEmail", "word", "meaning"})
public class UserDefinition implements BaseDTO {
    private String userEmail;
    private String word;
    private String meaning;
    private String example;
}
