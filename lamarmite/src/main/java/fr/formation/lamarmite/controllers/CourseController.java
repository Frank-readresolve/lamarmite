package fr.formation.lamarmite.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.formation.lamarmite.dto.CourseTypeDTO;
import fr.formation.lamarmite.entities.*;
import fr.formation.lamarmite.services.*;

@Controller
@RequestMapping("/courses")
public class CourseController extends BaseController {

    private final ICourseService courseService;

    private final ICourseTypeService courseTypeService;

    @Autowired
    protected CourseController(ICourseService courseService,
	    ICourseTypeService courseTypeService) {
	this.courseService = courseService;
	this.courseTypeService = courseTypeService;
    }

    @SuppressWarnings("unused")
    @GetMapping("/toCreate")
    public String toCreate(@ModelAttribute("course") Course course,
	    Model model) {
	populateModel(model);
	return "courseCreate";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("course") Course course,
	    BindingResult result, Model model) {
	if (validateAndSave(course, result)) {
	    model.addAttribute("course", new Course());
	}
	populateModel(model);
	return "courseCreate";
    }

    @GetMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") Long id, Model model) {
	Course course = courseService.findById(id);
	model.addAttribute("course", course);
	populateModel(model);
	return "courseUpdate";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("course") Course course,
	    BindingResult result, Model model) {
	if (validateAndSave(course, result)) {
	    return "redirect:/home/welcome";
	}
	populateModel(model);
	return "courseUpdate";
    }

    private boolean validateAndSave(Course course, BindingResult result) {
	validate(course, result);
	if (!result.hasErrors()) {
	    courseService.save(course);
	    return true;
	}
	return false;
    }

    private void validate(Course course, BindingResult result) {
	CourseType type = course.getType();
	if (Long.valueOf(0L).equals(type.getId())) {
	    result.rejectValue("type.id", "error.commons.required");
	}
	if (!courseService.validateCode(course)) {
	    result.rejectValue("code", "error.entities.course.duplicateCode");
	}
    }

    private void populateModel(Model model) {
	List<CourseTypeDTO> courseTypes = courseTypeService
		.findAllAsDTO(getAppLanguage());
	model.addAttribute("courseTypes", courseTypes);
    }
}
