package com.ooad.alumfund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.CreateEvent;

public interface CreateEventRepo extends JpaRepository<CreateEvent, Long> {

	List<CreateEvent> findAllByEmpId(String loginFacultyEmpId);


}
