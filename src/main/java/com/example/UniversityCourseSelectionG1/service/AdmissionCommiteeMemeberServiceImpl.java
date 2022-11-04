package com.example.UniversityCourseSelectionG1.service;
import java.time.LocalDate;

import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.repository.AdmissionCommiteeMemberRepository;
import com.example.UniversityCourseSelectionG1.repository.AdmissionRepository;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;

@Service
public class AdmissionCommiteeMemeberServiceImpl implements AdmissionCommiteeMemberService {

	@Autowired
	private AdmissionCommiteeMemberRepository repo;
	@Autowired
	private ApplicantRepository applicantRepo;
	@Autowired
	private AdmissionRepository admissionRepo;
	@Autowired
	private CourseRepository courseRepo;	
	
	
	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember member) 
	{
		AdmissionCommiteeMember mem;
		mem=repo.save(member);
		return mem;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember member) {
		
		if(repo.existsById(member.getAdminId()))
		{
			return repo.save(member);
		}
		else
		{
			throw new NotFoundException("AdmissionCommiteeMember not available");
		}
	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(int id) {
		AdmissionCommiteeMember mem=null;
		if(repo.existsById(id))
		{
			mem=repo.findById(id).get();
		}
		else
		{
			throw new NotFoundException("AdmissionCommiteeMember not found");
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
		else
		{
			throw new NotFoundException("No member exists with id: "+id);
		}
	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeMembers() {
		List<AdmissionCommiteeMember> allMembers=repo.findAll();
		if(allMembers.isEmpty())
		{
			throw new NotFoundException("No AdmissionCommiteeMember found");
		}
		return allMembers;
	}

	@Override
	public AdmissionStatus provideAdmissionResult(Applicant app, Admission adm) {

		Applicant applicant;
		Admission admission;
		Course course;
		
		//checking if course id is present in admission object
		if(courseRepo.existsById(adm.getCourseId()))
		{
			course=courseRepo.findById(adm.getCourseId()).get();
		}
		else
		{
			throw new NotFoundException("Course id:"+adm.getCourseId()+" does not exist");
		}
		
		//checking if applicant id is present in applicant object
		if(applicantRepo.existsById(app.getApplicantId()))
		{
			applicant=applicantRepo.findById(app.getApplicantId()).get();
		}
		else
		{
			throw new NotFoundException("Applicant id:"+app.getApplicantId()+" does not exist");
		}
		
		//checking if admission id is present in admission object
		if(admissionRepo.existsById(adm.getAdmissionId()))
		{
			admission=admissionRepo.findById(adm.getAdmissionId()).get();
		}
		else
		{
			throw new NotFoundException("Admission id:"+adm.getAdmissionId()+" does not exist");
		}
		
		//criteria 1: admission date < course starting date
		LocalDate admissionDate=admission.getAdmissionDate();
		LocalDate courseDate=course.getCourseStartDate();
		
		if(admissionDate.isAfter(courseDate))
		{
			applicant.setStatus(AdmissionStatus.REJECTED);
			admission.setStatus(AdmissionStatus.REJECTED);
			return applicant.getStatus();
		}
		
		//criteria 2: cutoff percentage
		double courseCriteria = course.getCourseCriteria();
		double marks = applicant.getApplicantGraduation();

		if (marks < courseCriteria) {
			applicant.setStatus(AdmissionStatus.PENDING);
			admission.setStatus(AdmissionStatus.PENDING);
		} else {
			admission.setStatus(AdmissionStatus.CONFIRMED);
			applicant.setStatus(AdmissionStatus.CONFIRMED);
		}
		
		return applicant.getStatus();
	}

}
