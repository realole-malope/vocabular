package com.rom.works.helper;

import com.rom.works.dto.Abbreviation;
import com.rom.works.dto.Adjective;
import com.rom.works.dto.BaseMeaning;
import com.rom.works.dto.Exclamation;
import com.rom.works.dto.Meaning;
import com.rom.works.dto.Noun;
import com.rom.works.dto.Verb;

import lombok.NonNull;

public class DefinitionResponseMeaningResolver {

    @NonNull
    public static BaseMeaning getFirstAvailableMeaning(Meaning meaning) {
        BaseMeaning baseMeaning = new BaseMeaning();
        if (meaning == null) return baseMeaning;
        Noun[] nouns = meaning.getNoun();
        Verb[] verbs = meaning.getVerb();
        Exclamation[] exclamations = meaning.getExclamation();
        Abbreviation[] abbreviations = meaning.getAbbreviation();
        Adjective[] adjectives = meaning.getAdjective();
        if (nouns != null && nouns[0] != null) return nouns[0];
        if (verbs != null && verbs[0] != null) return verbs[0];
        if (adjectives != null && adjectives[0] != null) return adjectives[0];
        if (exclamations != null && exclamations[0] != null) return exclamations[0];
        if (abbreviations != null && abbreviations[0] != null) return abbreviations[0];
        return baseMeaning;
    }
}
