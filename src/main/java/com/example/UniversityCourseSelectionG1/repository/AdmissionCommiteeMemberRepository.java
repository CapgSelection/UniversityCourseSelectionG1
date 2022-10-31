package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;

public interface AdmissionCommiteeMemberRepository extends JpaRepository<AdmissionCommiteeMember, Integer>
{

	@Query("Select * from admission_commitee_member where admin_id=:id and admin_password=:password")
	AdmissionCommiteeMember verifyAdmissionCommiteeMember(@Param("id") int id, @Param("password") String password);
}
