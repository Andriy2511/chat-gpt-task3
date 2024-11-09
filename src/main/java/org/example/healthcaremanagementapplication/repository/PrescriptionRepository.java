package org.example.healthcaremanagementapplication.repository;

import org.example.healthcaremanagementapplication.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
