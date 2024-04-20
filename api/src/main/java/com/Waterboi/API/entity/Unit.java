package com.Waterboi.API.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Unit {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private double literMultiple;
    private Unit() {}

    public Unit(String name, double literMultiple) {
        this.name = name.toLowerCase();
        this.literMultiple = literMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLiterMultiple() {
        return literMultiple;
    }


}
