package com.rom.works.vocabular.repository;

import com.rom.works.vocabular.entity.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    Optional<Definition> findByWordAndExample(String word, String example);
}
