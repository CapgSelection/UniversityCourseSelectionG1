package com.example.UniversityCourseSelectionG1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.UniversityCourseSelectionG1.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission,Integer>{

	@Query("select c from Admission c where c.courseId=:cId")
	List<Admission> findByCourseId(@Param("cId")int cId);

}
