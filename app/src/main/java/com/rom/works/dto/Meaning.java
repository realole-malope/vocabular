package com.rom.works.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meaning implements BaseDTO {
    @SerializedName("noun")
    @Expose
    private Noun[] noun;
    @SerializedName("verb")
    @Expose
    private Verb[] verb;
    @SerializedName("abbreviation")
    @Expose
    private Abbreviation[] abbreviation;
    @SerializedName("exclamation")
    @Expose
    private Exclamation[] exclamation;
    @SerializedName("adjective")
    @Expose
    private Adjective[] adjective;

    public Exclamation[] getExclamation() {
        return exclamation;
    }

    public void setExclamation(Exclamation[] exclamation) {
        this.exclamation = exclamation;
    }

    public Noun[] getNoun() {
        return noun;
    }

    public void setNoun(Noun[] noun) {
        this.noun = noun;
    }

    public Verb[] getVerb() {
        return verb;
    }

    public void setVerb(Verb[] verb) {
        this.verb = verb;
    }

    public Abbreviation[] getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation[] abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Adjective[] getAdjective() {
        return adjective;
    }

    public void setAdjective(Adjective[] adjective) {
        this.adjective = adjective;
    }
}
