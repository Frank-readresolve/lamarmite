package fr.formation.lamarmite.services;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CourseTypeDTO;

public interface ICourseTypeService {

    public List<CourseTypeDTO> findAllAsDTO(AppLanguage lang);
}
