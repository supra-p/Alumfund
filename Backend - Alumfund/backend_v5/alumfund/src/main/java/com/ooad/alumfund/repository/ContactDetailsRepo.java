package com.ooad.alumfund.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.ContactDetails;

public interface ContactDetailsRepo extends JpaRepository<ContactDetails, Integer>{

}
