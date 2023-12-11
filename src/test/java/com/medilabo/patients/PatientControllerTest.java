package com.medilabo.patients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.patients.model.Genre;
import com.medilabo.patients.model.Patient;
import com.medilabo.patients.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PatientControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getPatientsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom", is("TestBorderline")));
    }

    @Test
    public void getPatientByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPatient", is(1)));
    }

    @Test
    public void addPatientTest() throws Exception {
        Patient patient = new Patient( "Doe", "John", Genre.HOMME, "Rue A", "01-01-1990", "12345", "City A", "1234567890");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(patient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        Patient addedPatient = objectMapper.readValue(responseContent, Patient.class);

        assertEquals(addedPatient.getNom(), patient.getNom());

    }

    @Test
    public void updatePatientTest() throws Exception {
        // Request to add a new patient
        Patient patient = new Patient("Doe", "John", Genre.HOMME, "Rue A", "01-01-1990", "12345", "City A", "1234567890");
        Patient addedPatient = patientService.addPatient(patient);

        // Request to update prenom
        patient.setPrenom("NewPrenom");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients/"+addedPatient.getIdPatient()+"/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patient)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/"+addedPatient.getIdPatient()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", is("NewPrenom")));

    }


    @Test
    public void deletePatientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients/1/delete"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertNull(patientService.getPatientById(1));
    }






}
