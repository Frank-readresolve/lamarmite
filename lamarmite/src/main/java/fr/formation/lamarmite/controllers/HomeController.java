package fr.formation.lamarmite.controllers;

import java.util.List;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    public String welcome(Model model) throws Exception {
	// executeJob();
	populateModel(model);
	return "welcome";
    }

    @Autowired
    private ApplicationContext applicationContext;

    private void executeJob() throws Exception {
	JobLauncher jobLauncher = (JobLauncher) applicationContext
		.getBean("jobLauncher");
	Job importUserJob = (Job) applicationContext.getBean("importUserJob");
	jobLauncher.run(importUserJob, new JobParameters());
    }

    private void populateModel(Model model) {
	List<CourseDTO> courses = courseService.findAllAsDTO(getAppLanguage());
	model.addAttribute("courses", courses);
	List<MenuDTO> menus = menuService.findAllAsDTO(getAppLanguage());
	model.addAttribute("menus", menus);
    }
}
