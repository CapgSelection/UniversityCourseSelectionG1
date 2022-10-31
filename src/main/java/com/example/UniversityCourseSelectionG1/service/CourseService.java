package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import com.example.UniversityCourseSelectionG1.entities.Course;

public interface CourseService {
	public Course addCourse(Course course);
	public Course removeCourse(int courseId);
	public Course updateCourse(Course course);
	public Course viewCourse(int courseId);
	public List<Course> viewAllCourses();
}
