package com.example.UniversityCourseSelectionG1.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.Course;

public interface AdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer>
{
	@Query("SELECT c FROM Course c where c.courseId = ?1")
	Course getCourseById(int id);
	
	@Query(value="select * from admission_commitee_member where admin_id=?1 and admin_password=?2", nativeQuery=true)
	AdmissionCommiteeMember verifyAdmissionCommiteeMember(int id, String password);
	
	
	@Transactional
	@Modifying
	@Query(value="ALTER SEQUENCE admission_commitee_member_admin_id_seq RESTART WITH 1", nativeQuery = true)
	void resetCommitteeIdSequence();

}
