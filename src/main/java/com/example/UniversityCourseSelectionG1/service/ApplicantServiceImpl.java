package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;


@Service
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	ApplicantRepository aplRepository;
	
	@Autowired
	CourseRepository courseRepo;

	@Override
	public Applicant applyForCourse(Applicant applicant) {
		System.out.println(applicant);
		if(applicant.getAdmission()==null || applicant.getAdmission().getCourseId()==0) {
			throw new NotFoundException("Please Enter admission details!");
		}
		if(!courseRepo.existsById(applicant.getAdmission().getCourseId()))
			throw new NotFoundException("Course not found");
		else {
			Course course=courseRepo.findById(applicant.getAdmission().getCourseId()).get();
			if(course.getStatus().equals("INACTIVE")) {
				throw new NotFoundException("Course is not available");
			}
		}
		
		Applicant temp=aplRepository.save(applicant);
		temp.getAdmission().setApplicantId(temp.getApplicantId());
		return aplRepository.save(temp);
	}


	@Override
	public Applicant updateApplicant(Applicant app) {
		
		if(app==null ||!aplRepository.existsById(app.getApplicantId()))
			throw new NotFoundException("Applicant does'nt exist!");
		if(app.getAdmission()==null || app.getAdmission().getCourseId()==0) {
			throw new NotFoundException("Please Enter admission details!");
		}
		if(!courseRepo.existsById(app.getAdmission().getCourseId()))
			throw new NotFoundException("Course not found");
		else {
			Course course=courseRepo.findById(app.getAdmission().getCourseId()).get();
			if(course.getStatus().equals("INACTIVE")) {
				throw new NotFoundException("Course is not available");
			}
		}
		
		return aplRepository.save(app);
	}

	@Override
	public void delApplicant(Applicant app) {
		
		if(app==null ||!aplRepository.existsById(app.getApplicantId()))
			throw new NotFoundException("Applicant does'nt exist!");
		
			aplRepository.delete(app);
	}
	

	@Override
	public Optional<Applicant> getById(int id) {
		
		if(!aplRepository.existsById(id))
			throw new NotFoundException("Applicant does'nt exist!");
	   Optional<Applicant> res= aplRepository.findById(id);
	   return res;
	}

	@Override
	public List<Applicant> viewAllApplicantsByStatus(int status) {
		return aplRepository.viewAllApplicantByStatus(status);
	}

}
