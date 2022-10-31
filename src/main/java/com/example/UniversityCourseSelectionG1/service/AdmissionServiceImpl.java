package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.repository.AdmissionRepository;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;

@Service
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	private AdmissionRepository admissionRepo;
	@Autowired
	private ApplicantRepository applicantRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Override
	public void addAddmission(Admission admission) {
		// TODO Auto-generated method stub
		if((!courseRepo.existsById(admission.getCourseId())) || (!applicantRepo.existsById(admission.getApplicantId()))) {
			throw new NotFoundException();
		}
		else
		{
			admissionRepo.save(admission);
		}
		
	}

	@Override
	public List<Admission> getAdmissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delAdmissions() {
		// TODO Auto-generated method stub
		return null;
	}

}
