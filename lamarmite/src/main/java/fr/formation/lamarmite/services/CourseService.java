package fr.formation.lamarmite.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.*;
import fr.formation.lamarmite.entities.Course;
import fr.formation.lamarmite.repositories.*;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;

    private final ICourseJpaRepository courseJpaRepository;

    @Autowired
    protected CourseService(ICourseRepository courseRepository,
	    ICourseJpaRepository courseJpaRepository) {
	this.courseRepository = courseRepository;
	this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public Course findById(Long id) {
	Optional<Course> optional = courseJpaRepository.findById(id);
	return optional.get();
    }

    @Override
    public void save(Course course) {
	courseJpaRepository.save(course);
    }

    @Override
    public boolean validateCode(Course course) {
	Long id = course.getId();
	String code = course.getCode();
	if (null == id) { // create
	    return !courseJpaRepository.existsByCodeIgnoreCase(code);
	}
	return !courseJpaRepository.existsByCodeIgnoreCaseAndIdNot(code, id); // update
    }

    @Override
    public List<CourseDTO> findAllAsDTO(AppLanguage lang) {
	return courseRepository.findAllAsDTO(lang);
    }

    @Override
    public List<CourseItemDTO> findAllAsDTO(AppLanguage lang, String typeCode) {
	return courseRepository.findAllAsDTO(lang, typeCode);
    }
}
