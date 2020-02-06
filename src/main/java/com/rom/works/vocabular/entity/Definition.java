package com.rom.works.vocabular.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"word", "meaning"}, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Definition extends BaseEntity {
    private String word;
    private String meaning;
    private String example;
}
