package com.example.UniversityCourseSelectionG1.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;

@Repository
public interface UniversityStaffMemberRepository extends JpaRepository<UniversityStaffMember, Integer> {

	@Query(value="select * from university_staff_member where staff_Id=?1 and password=?2",nativeQuery = true)
	UniversityStaffMember verifyUniversityStaffMemberCredentials(int id,String password);
	
	@Transactional
	@Modifying
	@Query(value="ALTER SEQUENCE staff_sequence RESTART WITH 1", nativeQuery = true)
	void resetStaffIdSequence();
}
