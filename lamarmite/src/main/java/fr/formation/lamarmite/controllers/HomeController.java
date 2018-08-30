package fr.formation.lamarmite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fr.formation.lamarmite.dto.*;
import fr.formation.lamarmite.services.*;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    private final ICourseService courseService;

    private final IMenuService menuService;

    @Autowired
    protected HomeController(ICourseService courseService,
	    IMenuService menuService) {
	this.courseService = courseService;
	this.menuService = menuService;
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
	populateModel(model);
	return "welcome";
    }

    private void populateModel(Model model) {
	List<CourseDTO> courses = courseService.findAllAsDTO(getAppLanguage());
	model.addAttribute("courses", courses);
	List<MenuDTO> menus = menuService.findAllAsDTO(getAppLanguage());
	model.addAttribute("menus", menus);
    }
}
