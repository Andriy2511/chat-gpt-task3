package org.example.healthcaremanagementapplication.service;

import org.example.healthcaremanagementapplication.model.Appointment;
import org.example.healthcaremanagementapplication.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAppointments() {
        List<Appointment> appointments = List.of(new Appointment());
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();

        assertEquals(1, result.size());
    }

    @Test
    void getAppointmentById() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getAppointmentById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void saveAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment result = appointmentService.saveAppointment(appointment);

        assertNotNull(result);
    }

    @Test
    void deleteAppointment() {
        doNothing().when(appointmentRepository).deleteById(1L);

        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}
