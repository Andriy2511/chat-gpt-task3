package org.example.healthcaremanagementapplication.service;

import org.example.healthcaremanagementapplication.model.Patient;
import org.example.healthcaremanagementapplication.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatients() {
        List<Patient> patients = List.of(new Patient());
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(1, result.size());
    }

    @Test
    void getPatientById() {
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void savePatient() {
        Patient patient = new Patient();
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = patientService.savePatient(patient);

        assertNotNull(result);
    }

    @Test
    void deletePatient() {
        doNothing().when(patientRepository).deleteById(1L);

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }
}
