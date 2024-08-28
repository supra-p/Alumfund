package com.ooad.alumfund.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.PersonalInfo;

public interface PersonalInfoRepo extends JpaRepository<PersonalInfo, Integer>{

}
