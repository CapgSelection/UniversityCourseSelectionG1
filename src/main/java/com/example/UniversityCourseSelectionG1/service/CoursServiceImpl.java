package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;

@Service
public class CoursServiceImpl implements CourseService {

	CourseRepository courseRepo;
	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepo.save(course);
	}

	@Override
	public Course removeCourse(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		if(courseRepo.existsById(course.getCourseId())) {
			return courseRepo.save(course);
		}
		return null;
	}

	@Override
	public Course viewCourse(int courseId) {
		// TODO Auto-generated method stub
		if(courseRepo.existsById(courseId)) {
			return courseRepo.findById(courseId).get();
		}
		return null;
	}

	@Override
	public List<Course> viewAllCourses() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

}
