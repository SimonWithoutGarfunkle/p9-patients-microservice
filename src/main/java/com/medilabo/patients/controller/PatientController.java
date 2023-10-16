package com.medilabo.patients.controller;

import com.medilabo.patients.model.Patient;
import com.medilabo.patients.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping
    public List<Patient> getPatients () {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Integer id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Patient addPatient(@ModelAttribute("patient") Patient patient) {
        return patientService.addPatient(patient);
    }

    @PostMapping("/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Integer id) {
        return patientService.updatePatient(patientService.getPatientById(id));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable(value = "id") Integer id) {
        patientService.deletePatient(patientService.getPatientById(id));

    }

}
