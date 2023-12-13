package com.medilabo.patients.service;

import com.medilabo.patients.model.Patient;
import com.medilabo.patients.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public List<Patient> getAllPatients() {
        logger.debug("returning all patients");
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        logger.debug("returning patient n° "+id);
        return patientRepository.findById(id).orElse(null);
    }

    public Patient addPatient(Patient patient) {
        logger.debug("Adding patient "+patient.getNom());
        patient.setDateNaissance(formatDate(patient.getDateNaissance()));
        return patientRepository.save(patient);
    }

    public void deletePatient(Patient patient) {
        logger.debug("Deleting patient "+patient.getNom());
        patientRepository.delete(patient);
    }

    public Patient updatePatient(Patient patient) {
        logger.debug("Updating patient "+patient.getNom());
        patient.setDateNaissance(formatDate(patient.getDateNaissance()));
        return patientRepository.save(patient);
    }


    public String formatDate(String dateToCheck) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = dateFormatter.parse(dateToCheck);
            logger.info("Date already formated : " + date);
            return dateToCheck;
        } catch (ParseException e) {
            logger.info("Wrong date format, conversion started ...");
            Date dateConverted = new Date(Long.parseLong(dateToCheck));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String formattedDate = formatter.format(dateConverted);
            logger.info("Date converted : " + formattedDate);
            return formattedDate;
        }

    }


}
