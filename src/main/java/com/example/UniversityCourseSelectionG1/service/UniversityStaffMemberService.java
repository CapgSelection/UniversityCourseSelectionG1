package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;
@Service
public interface UniversityStaffMemberService {
       
	public UniversityStaffMember addStaff(UniversityStaffMember usm);
	public UniversityStaffMember updateStaff(UniversityStaffMember usm);
	public UniversityStaffMember viewStaff(int id);
	public void removeStaff(int id);
	public List<UniversityStaffMember> viewAllStaffs();
	public Course addCourse(Course course);
	public Course removeCourse(Course course);
	public Course updateCourse(Course course);
}
