package com.rom.works.vocabular.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(of = "email", callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity {
    private String email;
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany
    @JoinTable(name = "USER_DEFINITION", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DEFINITION_ID", referencedColumnName = "ID"))
    private Set<Definition> definitions = new HashSet<>();

    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }
}
