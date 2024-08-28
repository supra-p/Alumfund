package com.ooad.alumfund.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.StudentRequestFormTable;

public interface StudentRequestFormTableRepo extends JpaRepository<StudentRequestFormTable, Long> {

	StudentRequestFormTable findByUserId(String studId);

}
