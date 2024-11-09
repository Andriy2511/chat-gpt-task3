package org.example.healthcaremanagementapplication.controller;

import org.example.healthcaremanagementapplication.model.Appointment;
import org.example.healthcaremanagementapplication.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    void getAllAppointments() throws Exception {
        when(appointmentService.getAllAppointments()).thenReturn(List.of(new Appointment()));

        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentById() throws Exception {
        Appointment appointment = new Appointment();
        when(appointmentService.getAppointmentById(1L)).thenReturn(Optional.of(appointment));

        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void createAppointment() throws Exception {
        Appointment appointment = new Appointment();
        when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/appointments")
                        .contentType("application/json")
                        .content("{\"field\": \"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAppointment() throws Exception {
        doNothing().when(appointmentService).deleteAppointment(1L);

        mockMvc.perform(delete("/appointments/1"))
                .andExpect(status().isOk());
    }
}
