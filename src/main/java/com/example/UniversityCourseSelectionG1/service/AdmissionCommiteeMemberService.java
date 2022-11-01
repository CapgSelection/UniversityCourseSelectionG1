package com.example.UniversityCourseSelectionG1.service;
import java.util.List;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;

public interface AdmissionCommiteeMemberService {

	AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember member);
	AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember member);
	AdmissionCommiteeMember viewCommiteeMember(int id);
	void removeCommiteeMember(int id);
	List<AdmissionCommiteeMember> viewAllCommiteeMembers();
	AdmissionStatus provideAdmissionResult(Applicant applicant, Admission admission);
}
