package com.example.UniversityCourseSelectionG1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.UniversityCourseSelectionG1.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission,Integer>{

	@Query("select c from Admission c where c.courseId=:cId")
	List<Admission> findByCourseId(@Param("cId")int cId);

	@Query("select c from Admission c where c.admissionDate=:localdate")
	List<Admission> findAllAdmissionByAdmissionDate(@Param("localdate")LocalDate localdate);

}
