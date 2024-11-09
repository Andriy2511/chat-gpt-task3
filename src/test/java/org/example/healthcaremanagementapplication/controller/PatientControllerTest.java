package org.example.healthcaremanagementapplication.controller;

import org.example.healthcaremanagementapplication.model.Patient;
import org.example.healthcaremanagementapplication.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    void getAllPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(List.of(new Patient()));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById() throws Exception {
        Patient patient = new Patient();
        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void createPatient() throws Exception {
        Patient patient = new Patient();
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                        .contentType("application/json")
                        .content("{\"field\": \"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatient() throws Exception {
        doNothing().when(patientService).deletePatient(1L);

        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());
    }
}
