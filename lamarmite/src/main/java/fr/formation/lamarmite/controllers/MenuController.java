package fr.formation.lamarmite.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.formation.lamarmite.dto.CourseItemDTO;
import fr.formation.lamarmite.entities.*;
import fr.formation.lamarmite.services.*;

@Controller
@RequestMapping("/menus")
public class MenuController extends BaseController {

    private final ICourseService courseService;

    private final IMenuService menuService;

    @Autowired
    protected MenuController(ICourseService courseService,
	    IMenuService menuService) {
	this.courseService = courseService;
	this.menuService = menuService;
    }

    @GetMapping("/toCreate")
    public String toCreate(@ModelAttribute("menu") Menu menu, Model model) {
	menu.setStartDate(LocalDate.now());
	populateModel(model);
	return "menuCreate";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("menu") Menu menu,
	    BindingResult result, Model model) {
	if (validateAndSave(menu, result)) {
	    Menu newMenu = new Menu();
	    newMenu.setStartDate(LocalDate.now());
	    model.addAttribute("menu", newMenu);
	}
	populateModel(model);
	return "menuCreate";
    }

    @GetMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") Long id, Model model) {
	Menu menu = menuService.findById(id);
	model.addAttribute("menu", menu);
	populateModel(model);
	return "menuUpdate";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("menu") Menu menu,
	    BindingResult result, Model model) {
	if (validateAndSave(menu, result)) {
	    return "redirect:/home/welcome";
	}
	populateModel(model);
	return "menuUpdate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
	menuService.deleteById(id);
	return "redirect:/home/welcome";
    }

    private boolean validateAndSave(Menu menu, BindingResult result) {
	validate(menu, result);
	if (!result.hasErrors()) {
	    String menuCode = menuService.computeMenuCode(menu);
	    menu.setCode(menuCode);
	    Course starter = menu.getStarter();
	    if (Long.valueOf(0L).equals(starter.getId())) {
		menu.setStarter(null);
	    }
	    Course mainCourse = menu.getMainCourse();
	    if (Long.valueOf(0L).equals(mainCourse.getId())) {
		menu.setMainCourse(null);
	    }
	    Course dessert = menu.getDessert();
	    if (Long.valueOf(0L).equals(dessert.getId())) {
		menu.setDessert(null);
	    }
	    menuService.save(menu);
	    return true;
	}
	return false;
    }

    private void validate(Menu menu, BindingResult result) {
	int selected = 3;
	Course starter = menu.getStarter();
	boolean rejectStarter = false;
	if (Long.valueOf(0L).equals(starter.getId())) {
	    selected--;
	    rejectStarter = true;
	}
	Course mainCourse = menu.getMainCourse();
	boolean rejectMainCourse = false;
	if (Long.valueOf(0L).equals(mainCourse.getId())) {
	    selected--;
	    rejectMainCourse = true;
	}
	Course dessert = menu.getDessert();
	boolean rejectDessert = false;
	if (Long.valueOf(0L).equals(dessert.getId())) {
	    selected--;
	    rejectDessert = true;
	}
	validateCourses(selected, rejectStarter, rejectMainCourse,
		rejectDessert, menu, result);
	validateDates(menu, result);
    }

    private void validateCourses(int selected, boolean rejectStarter,
	    boolean rejectMainCourse, boolean rejectDessert, Menu menu,
	    BindingResult result) {
	if (2 > selected) {
	    String errorCode = "error.entities.menu.badCourseCombination";
	    rejectIfTrue(rejectStarter, "starter.id", errorCode, result);
	    rejectIfTrue(rejectMainCourse, "mainCourse.id", errorCode, result);
	    rejectIfTrue(rejectDessert, "dessert.id", errorCode, result);
	} else if (!menuService.validateCourseCombination(menu.getId(),
		menu.getStarter().getId(), menu.getMainCourse().getId(),
		menu.getDessert().getId())) {
	    // Selection okay, checked uniqueness
	    String errorCode = "error.entities.menu.duplicateCourseCombination";
	    rejectIfFalse(rejectStarter, "starter.id", errorCode, result);
	    rejectIfFalse(rejectMainCourse, "mainCourse.id", errorCode, result);
	    rejectIfFalse(rejectDessert, "dessert.id", errorCode, result);
	}
    }

    private static void validateDates(Menu menu, BindingResult result) {
	LocalDate startDate = menu.getStartDate();
	LocalDate endDate = menu.getEndDate();
	if (null != startDate && null != endDate) {
	    LocalDate minEndDate = startDate.plusDays(7);
	    if (endDate.isBefore(minEndDate)) {
		result.rejectValue("endDate",
			"error.entities.menu.endDate.badMinDate");
	    }
	}
    }

    private void populateModel(Model model) {
	List<CourseItemDTO> starters = courseService
		.findAllAsDTO(getAppLanguage(), CourseType.STARTER_CODE);
	model.addAttribute("starters", starters);
	List<CourseItemDTO> mainCourses = courseService
		.findAllAsDTO(getAppLanguage(), CourseType.MAIN_COURSE_CODE);
	model.addAttribute("mainCourses", mainCourses);
	List<CourseItemDTO> desserts = courseService
		.findAllAsDTO(getAppLanguage(), CourseType.DESSERT_CODE);
	model.addAttribute("desserts", desserts);
    }
}
