package com.example.UniversityCourseSelectionG1.service;

import java.time.LocalDate;
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
	public void addAddmission(Admission admission){
		// TODO Auto-generated method stub
		if((!courseRepo.existsById(admission.getCourseId())) || (applicantRepo.existsById(admission.getApplicantId()))) {
			throw new NotFoundException("Can't save admission details");
		}
		else
		{
			admissionRepo.save(admission);
		}
		
	}

	@Override
	public List<Admission> getAdmissions() {
		// TODO Auto-generated method stub
		List<Admission> list=admissionRepo.findAll();
		return list;
	}

	@Override
	public String delAdmissions() {
		// TODO Auto-generated method stub
		admissionRepo.deleteAll();
		return "Deleted Successfully";
	}

	@Override
	public String delAdmissionsById(int id){
		// TODO Auto-generated method stub
		if(admissionRepo.existsById(id)) {
			admissionRepo.deleteById(id);
			return "Deleted";
		}
		throw new NotFoundException("Id not present in the database");
	}

	@Override
	public List<Admission> getAdmissionbyCourse(int cId) {
		// TODO Auto-generated method stub
		List<Admission> list = admissionRepo.findByCourseId(cId);
		if(list.isEmpty())
			throw new NotFoundException("Couse Id not found");
		else
			return list;
	}

	@Override
	public List<Admission> showAllAdmissionbyDate(LocalDate localdate) {
		// TODO Auto-generated method stub
		List<Admission> admissionlist = admissionRepo.findAllAdmissionByAdmissionDate(localdate);	
		if(admissionlist.isEmpty())
			throw new NotFoundException("No student admitted on this date");
		return admissionlist;
	}

	@Override
	public Admission updateAdmission(Admission admission) {
		// TODO Auto-generated method stub
		if((!courseRepo.existsById(admission.getCourseId())) || (applicantRepo.existsById(admission.getApplicantId()))) {
			throw new NotFoundException("Can't update admission details");
		}
		else
		{
			admissionRepo.save(admission);
			return admission;
		}
	
	}

	

	
	
}
