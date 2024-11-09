package org.example.healthcaremanagementapplication.controller;

import org.example.healthcaremanagementapplication.model.Prescription;
import org.example.healthcaremanagementapplication.service.PrescriptionService;
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

@WebMvcTest(PrescriptionController.class)
class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionService prescriptionService;

    @Test
    void getAllPrescriptions() throws Exception {
        when(prescriptionService.getAllPrescriptions()).thenReturn(List.of(new Prescription()));

        mockMvc.perform(get("/prescriptions"))
                .andExpect(status().isOk());
    }

    @Test
    void getPrescriptionById() throws Exception {
        Prescription prescription = new Prescription();
        when(prescriptionService.getPrescriptionById(1L)).thenReturn(Optional.of(prescription));

        mockMvc.perform(get("/prescriptions/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void createPrescription() throws Exception {
        Prescription prescription = new Prescription();
        when(prescriptionService.savePrescription(any(Prescription.class))).thenReturn(prescription);

        mockMvc.perform(post("/prescriptions")
                        .contentType("application/json")
                        .content("{\"field\": \"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePrescription() throws Exception {
        doNothing().when(prescriptionService).deletePrescription(1L);

        mockMvc.perform(delete("/prescriptions/1"))
                .andExpect(status().isOk());
    }
}
