package com.example.UniversityCourseSelectionG1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UniversityCourseSelectionG1.entities.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer>{

	@Query(value = "select * from Applicant where status=?1", nativeQuery = true)
	List<Applicant> viewAllApplicantByStatus(int status);
	
	@Query(value="select * from Applicant where Applicant ID=?1 and Password=?2",nativeQuery = true)
	Applicant verifyApplicantCredentials(int id, String password);

	

}
