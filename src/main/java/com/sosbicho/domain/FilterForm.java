package com.sosbicho.domain;

public class FilterForm {

    private String species;
    private String size;
    private Integer ageGreater;
    private Integer ageLess;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAgeGreater() {
        return ageGreater;
    }

    public void setAgeGreater(Integer ageGreater) {
        this.ageGreater = ageGreater;
    }

    public Integer getAgeLess() {
        return ageLess;
    }

    public void setAgeLess(Integer ageLess) {
        this.ageLess = ageLess;
    }
}
