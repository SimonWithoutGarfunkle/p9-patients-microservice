package com.medilabo.patients.controller;

import com.medilabo.patients.model.Patient;
import com.medilabo.patients.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to access the back end Patient
 *
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    /**
     * Get the list of all patients in the database
     *
     * @return list of all patients in the database
     */
    @GetMapping
    public List<Patient> getPatients () {
        return patientService.getAllPatients();
    }

    /**
     * Get the info of the patient corresponding to the id from the database
     *
     * @param id of the patient
     * @return info of the patient
     */
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Integer id) {
        return patientService.getPatientById(id);
    }

    /**
     * Save the patient in database
     *
     * @param patient to save
     * @return saved patient
     */
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        logger.info("patient : "+patient.getPrenom()+" "+patient.getNom());
        return patientService.addPatient(patient);
    }

    /**
     * Update the info of the patient in the database
     *
     * @param patient with new data to update
     * @param id of the patient to update
     * @return updated patient
     */
    @PostMapping("/{id}/update")
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable(value = "id") Integer id) {
        patient.setIdPatient(id);
        return patientService.updatePatient(patient);
    }

    /**
     * Delete the patient from database
     *
     * @param id of the patient to delete
     */
    @PostMapping("/{id}/delete")
    public void deletePatient(@PathVariable(value = "id") Integer id) {
        logger.info("Deleting patient n°"+id);
        patientService.deletePatient(patientService.getPatientById(id));

    }

}
