package com.ooad.alumfund.repository;

import java.awt.Image;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.ImageIncome;

public interface ImageIncomeRepo extends JpaRepository<ImageIncome, Long> {

	Optional<ImageIncome> findByImageName(String imageName);

}
