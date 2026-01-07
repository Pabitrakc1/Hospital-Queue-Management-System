package com.hospital.management.model;

public class PatientRequest {

    private String name;
    private int age;
    private String symptoms;
    private int emergencyLevel;

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
}
