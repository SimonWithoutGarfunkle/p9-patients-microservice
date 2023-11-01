package com.medilabo.patients.controller;

import com.medilabo.patients.model.Patient;
import com.medilabo.patients.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("/patients")
    public List<Patient> getPatients () {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Integer id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/patients")
    public Patient addPatient(@Valid @ModelAttribute("patient") Patient patient) {
        return patientService.addPatient(patient);
    }

    @PostMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Integer id, @Valid @ModelAttribute("patient") Patient patient) {
        patient.setIdPatient(id);
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable(value = "id") Integer id) {
        patientService.deletePatient(patientService.getPatientById(id));

    }

}
