package com.example.UniversityCourseSelectionG1.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {
	boolean loginAsApplicant(int id,String password);
	boolean loginAsAdmissionCommiteeMember(int id,String password);
	boolean loginAsUniversityStaffMember(int id,String password);
}
