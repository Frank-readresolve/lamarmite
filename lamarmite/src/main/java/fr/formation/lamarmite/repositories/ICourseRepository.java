package fr.formation.lamarmite.repositories;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.*;
import fr.formation.lamarmite.entities.Menu;

public interface ICourseRepository {

    public List<CourseDTO> findAllAsDTO(AppLanguage lang);

    public List<CourseItemDTO> findAllAsDTO(AppLanguage lang, String typeCode);

    public String computeMenuCode(Menu menu);
}
