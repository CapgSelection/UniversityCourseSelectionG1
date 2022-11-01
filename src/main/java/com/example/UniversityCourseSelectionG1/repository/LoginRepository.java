package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {
	
	
	boolean verifyApplicantCredentials(int id,String password);
	boolean verifyAdmissionCommiteeMemberCredentials(int id,String password);
	boolean verifyUniversityStaffMemberCredentials(int id,String password);
}
