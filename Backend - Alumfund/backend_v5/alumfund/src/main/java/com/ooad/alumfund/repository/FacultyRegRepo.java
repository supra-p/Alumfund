package com.ooad.alumfund.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.FacultyRegistration;

public interface FacultyRegRepo extends JpaRepository<FacultyRegistration, Long> {

	Optional<FacultyRegistration> findByEmpId(String empId);

}
