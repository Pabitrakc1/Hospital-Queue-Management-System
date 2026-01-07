package com.hospital.management.model;

import java.util.List;

public class QueueStatusResponse {

    private int totalPatients;
    private List<Patient> nextPatients;

    public QueueStatusResponse(int totalPatients, List<Patient> nextPatients) {
        this.totalPatients = totalPatients;
        this.nextPatients = nextPatients;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public List<Patient> getNextPatients() {
        return nextPatients;
    }
}
