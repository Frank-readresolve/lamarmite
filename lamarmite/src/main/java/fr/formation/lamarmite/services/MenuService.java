package fr.formation.lamarmite.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.MenuDTO;
import fr.formation.lamarmite.entities.Menu;
import fr.formation.lamarmite.repositories.*;

@Service
public class MenuService implements IMenuService {

    private final ICourseRepository courseRepository;

    private final IMenuRepository menuRepository;

    private final IMenuJpaRepository menuJpaRepository;

    @Autowired
    protected MenuService(ICourseRepository courseRepository,
	    IMenuRepository menuRepository,
	    IMenuJpaRepository menuJpaRepository) {
	this.courseRepository = courseRepository;
	this.menuRepository = menuRepository;
	this.menuJpaRepository = menuJpaRepository;
    }

    @Override
    public Menu findById(Long id) {
	Optional<Menu> optional = menuJpaRepository.findById(id);
	return optional.get();
    }

    @Override
    public void save(Menu menu) {
	menuJpaRepository.save(menu);
    }

    @Override
    public List<MenuDTO> findAllAsDTO(AppLanguage lang) {
	return menuRepository.findAllAsDTO(lang);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void deleteById(Long id) {
	menuJpaRepository.deleteById(id);
    }

    @Override
    public boolean validateCourseCombination(Long menuId, Long starterId,
	    Long mainCourseId, Long dessertId) {
	return menuRepository.validateCourseCombination(menuId, starterId,
		mainCourseId, dessertId);
    }

    @Override
    public String computeMenuCode(Menu menu) {
	return courseRepository.computeMenuCode(menu);
    }
}
