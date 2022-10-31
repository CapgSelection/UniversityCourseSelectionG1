package com.example.UniversityCourseSelectionG1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UniversityCourseSelectionG1.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{

}
