package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.Course;

public interface AdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer>
{
	@Query("SELECT c FROM Course c where c.courseId = ?1")
	Course getCourseById(int id);
	
	@Query(value="Select * from admission_committee_member where admin_id=?1 and admin_password=?2", nativeQuery=true)
	AdmissionCommiteeMember verifyAdmissionCommiteeMember(int id, String password);
}
