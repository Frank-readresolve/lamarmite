package fr.formation.lamarmite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CourseTypeDTO;
import fr.formation.lamarmite.repositories.ICourseTypeRepository;

@Service
public class CourseTypeService implements ICourseTypeService {

    private final ICourseTypeRepository courseTypeRepository;

    @Autowired
    protected CourseTypeService(ICourseTypeRepository courseTypeRepository) {
	this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public List<CourseTypeDTO> findAllAsDTO(AppLanguage lang) {
	return courseTypeRepository.findAllAsDTO(lang);
    }
}
