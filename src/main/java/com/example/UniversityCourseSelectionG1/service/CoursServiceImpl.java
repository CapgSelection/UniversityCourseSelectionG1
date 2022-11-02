package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.exception.DateNotCorrectException;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;

@Service
public class CoursServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		if(course.getCourseEndDate().compareTo(course.getCourseStartDate())<0) {
			throw new DateNotCorrectException("Enter the Correct Course Date with Course id: "+course.getCourseId());
		}else {
		return courseRepo.save(course);
	}
	}

	@Override
	public Course removeCourse(int courseId) {
		
		Course deletedCourse = null;
		if(courseRepo.existsById(courseId)) {
			deletedCourse = courseRepo.findById(courseId).get();
			deletedCourse.setStatus("INACTIVE");
			courseRepo.save(deletedCourse);
			return deletedCourse;
		}
		else {
			throw new NotFoundException("Course with id: "+courseId+" not found!!!");
		}
	
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		if(courseRepo.existsById(course.getCourseId())) {
			return courseRepo.save(course);
		}
		else {
			throw new NotFoundException("Course with id: "+course.getCourseId()+" not found!!!");
		}
	}

	@Override
	public Course viewCourse(int courseId) {
		// TODO Auto-generated method stub
		if(courseRepo.existsById(courseId)) {
			return courseRepo.findById(courseId).get();
		}
		else {
			throw new NotFoundException("Course with id: "+courseId+" not found!!!");
		}
	}

	@Override
	public List<Course> viewAllCourses() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

}
