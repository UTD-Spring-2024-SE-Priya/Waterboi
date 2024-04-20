package com.Waterboi.API.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class WaterPost {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long appuserId;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private Long unitId;
    @Column(nullable = false)
    private LocalDateTime postTime;

    private WaterPost() {}
    public WaterPost(Long appuserID, double quantity, Long unitId, LocalDateTime postTime) {
        this.appuserId = appuserID;
        this.quantity = quantity;
        this.unitId = unitId;
        this.postTime = postTime;
    }

    public Long getId() {
        return id;
    }

    public Long getAppuserId() {
        return appuserId;
    }

    public double getQuantity() {
        return quantity;
    }

    public Long getUnitId() {
        return unitId;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }
}
