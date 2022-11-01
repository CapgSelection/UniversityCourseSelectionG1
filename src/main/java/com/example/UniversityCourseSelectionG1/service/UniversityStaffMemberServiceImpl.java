package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.repository.UniversityStaffMemberRepository;

@Service
public class UniversityStaffMemberServiceImpl implements UniversityStaffMemberService {

	@Autowired
	private UniversityStaffMemberRepository staffRepo;
	@Autowired
	private CourseService courseService;

	@Override
	public UniversityStaffMember addStaff(UniversityStaffMember usm) {
		return staffRepo.save(usm);
	}

	@Override
	public UniversityStaffMember updateStaff(UniversityStaffMember usm) {
		if (staffRepo.existsById(usm.getStaffId())) {
			UniversityStaffMember updatedUSM = staffRepo.save(usm);
			updatedUSM.setPassword("******");
			return updatedUSM;
		} 
		else {
			throw new NotFoundException("Staff with id: " + usm.getStaffId() + " not found!");
		}
	}

	@Override
	public UniversityStaffMember viewStaff(int id) {
		if (staffRepo.existsById(id)) {
			UniversityStaffMember usm = staffRepo.getReferenceById(id);
			usm.setPassword("******");
			return usm;
		} 
		else {
			throw new NotFoundException("Staff with id: " + id + " not found!");
		}
	}

	@Override
	public void removeStaff(int id) {
		if (staffRepo.existsById(id)) {
			if (staffRepo.count() == 1) {
				throw new NotFoundException("Cannot delete last record");
			} 
			else {
				staffRepo.deleteById(id);
			}
		} 
		else {
			throw new NotFoundException("Staff with id: " + id + " not found!");
		}
	}

	@Override
	public List<UniversityStaffMember> viewAllStaffs() {
		List<UniversityStaffMember> usmList = staffRepo.findAll();
		if (usmList.isEmpty()) {
			throw new NotFoundException("No staff records found!");
		}
		usmList.forEach(usm -> usm.setPassword("******"));
		return usmList;
	}

	@Override
	public Course addCourse(Course course) {
		return courseService.addCourse(course);
	}

	@Override
	public Course removeCourse(Course course) {
		return courseService.removeCourse(course.getCourseId());
	}

	@Override
	public Course updateCourse(Course course) {
		return courseService.updateCourse(course);
	}

}
