package org.example.healthcaremanagementapplication.service;

import org.example.healthcaremanagementapplication.model.Prescription;
import org.example.healthcaremanagementapplication.repository.PrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrescriptionServiceTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionService prescriptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPrescriptions() {
        List<Prescription> prescriptions = List.of(new Prescription());
        when(prescriptionRepository.findAll()).thenReturn(prescriptions);

        List<Prescription> result = prescriptionService.getAllPrescriptions();

        assertEquals(1, result.size());
        verify(prescriptionRepository, times(1)).findAll();
    }

    @Test
    void getPrescriptionById() {
        Prescription prescription = new Prescription();
        when(prescriptionRepository.findById(1L)).thenReturn(Optional.of(prescription));

        Optional<Prescription> result = prescriptionService.getPrescriptionById(1L);

        assertTrue(result.isPresent());
        verify(prescriptionRepository, times(1)).findById(1L);
    }

    @Test
    void savePrescription() {
        Prescription prescription = new Prescription();
        when(prescriptionRepository.save(prescription)).thenReturn(prescription);

        Prescription result = prescriptionService.savePrescription(prescription);

        assertNotNull(result);
        verify(prescriptionRepository, times(1)).save(prescription);
    }

    @Test
    void deletePrescription() {
        doNothing().when(prescriptionRepository).deleteById(1L);

        prescriptionService.deletePrescription(1L);

        verify(prescriptionRepository, times(1)).deleteById(1L);
    }
}
