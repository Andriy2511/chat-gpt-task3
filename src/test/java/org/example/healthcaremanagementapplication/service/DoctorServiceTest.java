package org.example.healthcaremanagementapplication.service;

import org.example.healthcaremanagementapplication.model.Doctor;
import org.example.healthcaremanagementapplication.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDoctors() {
        List<Doctor> doctors = List.of(new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> result = doctorService.getAllDoctors();

        assertEquals(1, result.size());
    }

    @Test
    void getDoctorById() {
        Doctor doctor = new Doctor();
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Optional<Doctor> result = doctorService.getDoctorById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void saveDoctor() {
        Doctor doctor = new Doctor();
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor result = doctorService.saveDoctor(doctor);

        assertNotNull(result);
    }

    @Test
    void deleteDoctor() {
        doNothing().when(doctorRepository).deleteById(1L);

        doctorService.deleteDoctor(1L);

        verify(doctorRepository, times(1)).deleteById(1L);
    }
}
