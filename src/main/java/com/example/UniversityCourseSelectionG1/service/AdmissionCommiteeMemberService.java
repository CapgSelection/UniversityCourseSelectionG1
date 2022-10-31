package com.example.UniversityCourseSelectionG1.service;
import java.util.List;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;

public interface AdmissionCommiteeMemberService {

	AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember member);
	AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember member);
	AdmissionCommiteeMember viewCommiteeMember(int id);
	void removeCommiteeMember(int id);
	List<AdmissionCommiteeMember> viewAllCommiteeManager();
//	AdmissionCommiteeMember provide
}
