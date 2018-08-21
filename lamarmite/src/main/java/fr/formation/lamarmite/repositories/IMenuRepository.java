package fr.formation.lamarmite.repositories;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.MenuDTO;

public interface IMenuRepository {

    public List<MenuDTO> findAllAsDTO(AppLanguage lang);

    public boolean validateCourseCombination(Long menuId, Long starterId,
	    Long mainCourseId, Long dessertId);
}
