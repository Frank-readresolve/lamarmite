package fr.formation.lamarmite.services;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.*;
import fr.formation.lamarmite.entities.Course;

public interface ICourseService {

    public Course findById(Long id);

    public boolean validateCode(Course course);

    public void save(Course course);

    public List<CourseDTO> findAllAsDTO(AppLanguage lang);

    public List<CourseItemDTO> findAllAsDTO(AppLanguage lang, String typeCode);
}
