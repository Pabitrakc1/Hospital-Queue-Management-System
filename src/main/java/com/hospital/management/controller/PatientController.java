package com.hospital.management.controller;

import com.hospital.management.model.Patient;
import com.hospital.management.model.PatientRequest;
import com.hospital.management.service.QueueService;
import org.springframework.web.bind.annotation.*;

import com.hospital.management.model.QueueStatusResponse;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private final QueueService queueService;

    public PatientController(QueueService queueService) {
        this.queueService = queueService;
    }

    // Register patient
    @PostMapping("/register")
    public Patient registerPatient(@RequestBody PatientRequest request) {
        return queueService.registerPatient(
                request.getName(),
                request.getAge(),
                request.getSymptoms(),
                request.getEmergencyLevel()
        );
    }

    // Get next patient (peek)
    @GetMapping("/next")
    public Patient getNextPatient() {
        return queueService.getNextPatient();
    }

    // Serve next patient
    @PostMapping("/serve")
    public Patient servePatient() {
        return queueService.serveNextPatient();
    }
    
    @GetMapping("/queue/status")
    public QueueStatusResponse getQueueStatus(
            @RequestParam(defaultValue = "5") int limit) {

        return new QueueStatusResponse(
                queueService.getQueueSize(),
                queueService.getTopNPatients(limit)
        );
    }

}
