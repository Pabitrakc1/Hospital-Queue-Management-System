package com.hospital.management.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Patient {

    private Long id;
    private String name;
    private int age;
    private String symptoms;
    private int emergencyLevel;
    private LocalDateTime arrivalTime;

    public Patient(Long id, String name, int age, String symptoms, int emergencyLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.emergencyLevel = emergencyLevel;
        this.arrivalTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public int getEmergencyLevel() {
        return emergencyLevel;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    // ✅ REQUIRED FOR AGING LOGIC
    public long getWaitingTimeInMinutes() {
        return Duration.between(arrivalTime, LocalDateTime.now()).toMinutes();
    }
}
