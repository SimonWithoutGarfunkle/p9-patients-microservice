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
@RequestMapping("/api/patients")
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
    public Patient addPatient(@RequestBody Patient patient) {
        logger.info("patient : "+patient.getPrenom()+" "+patient.getNom());
        return patientService.addPatient(patient);
    }

    @PostMapping("/{id}/update")
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable(value = "id") Integer id) {
        patient.setIdPatient(id);
        return patientService.updatePatient(patient);
    }

    @PostMapping("/{id}/delete")
    public void deletePatient(@PathVariable(value = "id") Integer id) {
        logger.info("Deleting patient n°"+id);
        patientService.deletePatient(patientService.getPatientById(id));

    }

}
