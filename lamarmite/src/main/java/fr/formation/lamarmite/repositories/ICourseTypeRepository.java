package fr.formation.lamarmite.repositories;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CourseTypeDTO;

public interface ICourseTypeRepository {

    public List<CourseTypeDTO> findAllAsDTO(AppLanguage lang);
}
