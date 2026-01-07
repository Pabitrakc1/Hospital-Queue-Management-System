package com.hospital.management.service;

import com.hospital.management.entity.PatientEntity;
import com.hospital.management.model.Patient;
import com.hospital.management.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class QueueService {

    private final Queue<Patient> patientQueue;
    private final PatientRepository patientRepository;

    public QueueService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

        // ✅ PriorityQueue with Emergency + Aging + Arrival-time logic
        this.patientQueue = new PriorityQueue<>((p1, p2) -> {

            long p1Score = (p1.getEmergencyLevel() * 10) + p1.getWaitingTimeInMinutes();
            long p2Score = (p2.getEmergencyLevel() * 10) + p2.getWaitingTimeInMinutes();

            if (p1Score != p2Score) {
                return Long.compare(p2Score, p1Score); // higher score first
            }

            // tie-breaker: earlier arrival first
            return p1.getArrivalTime().compareTo(p2.getArrivalTime());
        });
    }

    // ✅ LOAD DATA FROM DB ON APP START
    @PostConstruct
    public void loadPatientsFromDatabase() {
        List<PatientEntity> entities = patientRepository.findAll();

        for (PatientEntity entity : entities) {
            Patient patient = new Patient(
                    entity.getId(),
                    entity.getName(),
                    entity.getAge(),
                    entity.getSymptoms(),
                    entity.getEmergencyLevel()
            );
            patientQueue.add(patient);
        }

        System.out.println("Loaded " + patientQueue.size() + " patients from DB");
    }

    // ✅ REGISTER PATIENT
    public Patient registerPatient(String name, int age, String symptoms, int emergencyLevel) {

        PatientEntity entity = new PatientEntity(name, age, symptoms, emergencyLevel);
        PatientEntity saved = patientRepository.save(entity);

        Patient patient = new Patient(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getSymptoms(),
                saved.getEmergencyLevel()
        );

        patientQueue.add(patient);
        return patient;
    }

    // ✅ GET NEXT PATIENT (PEEK)
    public Patient getNextPatient() {
        return patientQueue.peek();
    }

    // ✅ SERVE NEXT PATIENT (ONLY ONE METHOD – NO DUPLICATE)
    public Patient serveNextPatient() {
        if (patientQueue.isEmpty()) {
            return null;
        }
        return patientQueue.poll();
    }

    // ✅ QUEUE SIZE
    public int getQueueSize() {
        return patientQueue.size();
    }

    // ✅ TOP N PATIENTS PREVIEW
    public List<Patient> getTopNPatients(int n) {

        List<Patient> result = new ArrayList<>();
        PriorityQueue<Patient> tempQueue = new PriorityQueue<>(patientQueue);

        while (!tempQueue.isEmpty() && result.size() < n) {
            result.add(tempQueue.poll());
        }

        return result;
    }

    // ✅ EXPLAIN DECISION
    public String explainNextPatient(Patient patient) {
        if (patient == null) {
            return "No patients in queue";
        }

        return "Selected due to emergency level "
                + patient.getEmergencyLevel()
                + " and waiting time "
                + patient.getWaitingTimeInMinutes()
                + " minutes";
    }
}
