package com.sosbicho.domain;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Bicho {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String size;
    private String species;
    private String race;
    private Integer age;
    private boolean adopted;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> interested;
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getInterested() {
        return interested;
    }

    public String getInterestedString() {
        String people = interested.stream().map(User::getUsername).collect(Collectors.joining(", "));
        return people.isEmpty() ? "-" : people;
    }

    public void setInterested(List<User> interested) {
        this.interested = interested;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
}
