package fr.formation.lamarmite.services;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.MenuDTO;
import fr.formation.lamarmite.entities.Menu;

public interface IMenuService {

    public Menu findById(Long id);

    public String computeMenuCode(Menu menu);

    public void save(Menu menu);

    public List<MenuDTO> findAllAsDTO(AppLanguage lang);

    public void deleteById(Long id);

    public boolean validateCourseCombination(Long menuId, Long starterId,
	    Long mainCourseId, Long dessertId);
}
