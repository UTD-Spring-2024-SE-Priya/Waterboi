package com.Waterboi.API.entity;

import jakarta.persistence.*;

@Entity
public class AppuserProfile {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private Long appuserId;
    private double dailyGoal;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit preferredUnit;

    public AppuserProfile() {};
    public AppuserProfile(Long appuserId) {
        this.appuserId = appuserId;
        dailyGoal = 3;
    }
    public long getId() {
        return id;
    }
    public long appuserId() {
        return appuserId;
    }
    public double getDailyGoal() {
        return dailyGoal;
    }
    public void setDailyGoal(double dailyGoal) {
        this.dailyGoal = dailyGoal;
    }
}
