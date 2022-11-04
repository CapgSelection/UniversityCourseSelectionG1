package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;

@Repository
public class LoginRepositoryImpl implements LoginRepository{
	@Autowired
	private ApplicantRepository appRepo;
	
	@Autowired
	private AdmissionCommiteeMemberRepository addRepo;
	
	@Autowired
	private UniversityStaffMemberRepository uniRepo;
	
	private void insertDefaultRecord(String type) {
		if (type.equalsIgnoreCase("staff")) {
			UniversityStaffMember usm = new UniversityStaffMember(1,"staff1","password", "role");
			uniRepo.resetStaffIdSequence();
			uniRepo.save(usm);
		}
		if(type.equalsIgnoreCase("commitee")) {
			AdmissionCommiteeMember acm = new AdmissionCommiteeMember(0,"adminName","1234567890","username","password");					
			addRepo.resetCommitteeIdSequence();
			addRepo.save(acm);
		}
	}

	@Override
	public boolean verifyApplicantCredentials(int id, String password) {
		Applicant app = appRepo.verifyApplicantCredentials(id, password);
		if(app!=null)
			return true;
		else return false;
	}

	@Override
	public boolean verifyAdmissionCommiteeMemberCredentials(int id, String password) {
		if(addRepo.count()==0) {
			insertDefaultRecord("commitee");
		}
		
		AdmissionCommiteeMember acm= addRepo.verifyAdmissionCommiteeMember(id, password);
		if(acm!=null)
			return true;
		return false;
	}

	@Override
	public boolean verifyUniversityStaffMemberCredentials(int id, String password) {
		if(uniRepo.count()==0) {
			insertDefaultRecord("staff");
		}
		
		UniversityStaffMember ucm=uniRepo.verifyUniversityStaffMemberCredentials(id, password);
		if(ucm!=null) {
			return true;
		}
		return false;
	}

}
