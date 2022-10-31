package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;

public interface AdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer>
{

	@Query(value="Select * from admission_committee_member where admin_id=?1 and admin_password=?2", nativeQuery=true)
	AdmissionCommiteeMember verifyAdmissionCommiteeMember(int id, String password);
}
