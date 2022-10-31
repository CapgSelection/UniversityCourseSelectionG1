package com.example.UniversityCourseSelectionG1.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.repository.AdmissionCommiteeMemberRepository;

public class AdmissionCommiteeMemeberServiceImpl implements AdmissionCommiteeMemberService {

	@Autowired
	AdmissionCommiteeMemberRepository repo;
	
	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember member) {

		AdmissionCommiteeMember mem=repo.save(member);
		return mem;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember member) {
		return repo.save(member);
	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(int id) {
		AdmissionCommiteeMember mem=null;
		if(repo.existsById(id))
		{
			mem=repo.findById(id).get();
		}
		return mem;
	}

	@Override
	public void removeCommiteeMember(int id) 
	{
		if(repo.existsById(id))
		{
			repo.deleteById(id);
		}
	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
