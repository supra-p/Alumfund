package com.ooad.alumfund.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, String>{


	
}
