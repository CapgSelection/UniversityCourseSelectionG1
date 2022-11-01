package com.example.UniversityCourseSelectionG1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository logRepo;
	
	@Override
	public boolean loginAsApplicant(int id, String password) {
		
		return logRepo.verifyApplicantCredentials(id, password);
	}

	@Override
	public boolean loginAsAdmissionCommiteeMember(int id, String password) {
		// TODO Auto-generated method stub
		return logRepo.verifyAdmissionCommiteeMemberCredentials(id, password);
	}

	@Override
	public boolean loginAsUniversityStaffMember(int id, String password) {
		// TODO Auto-generated method stub
		return logRepo.verifyUniversityStaffMemberCredentials(id, password);
	}

}

