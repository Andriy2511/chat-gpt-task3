package org.example.healthcaremanagementapplication.controller;

import org.example.healthcaremanagementapplication.model.Doctor;
import org.example.healthcaremanagementapplication.service.DoctorService;
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

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    void getAllDoctors() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(List.of(new Doctor()));

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void getDoctorById() throws Exception {
        Doctor doctor = new Doctor();
        when(doctorService.getDoctorById(1L)).thenReturn(Optional.of(doctor));

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void createDoctor() throws Exception {
        Doctor doctor = new Doctor();
        when(doctorService.saveDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                        .contentType("application/json")
                        .content("{\"field\": \"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDoctor() throws Exception {
        doNothing().when(doctorService).deleteDoctor(1L);

        mockMvc.perform(delete("/doctors/1"))
                .andExpect(status().isOk());
    }
}
