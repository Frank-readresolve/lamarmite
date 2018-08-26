package fr.formation.lamarmite.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.formation.lamarmite.dto.CivilityDTO;
import fr.formation.lamarmite.entities.*;
import fr.formation.lamarmite.services.*;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final ICivilityService civilityService;

    private final IUserService userService;

    @Autowired
    protected UserController(ICivilityService civilityService,
	    IUserService userService) {
	this.civilityService = civilityService;
	this.userService = userService;
    }

    @SuppressWarnings("unused")
    @GetMapping("/toCreate")
    public String toCreate(@ModelAttribute("user") User user, Model model) {
	populateModel(model);
	return "userCreate";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user,
	    BindingResult result, Model model) {
	if (validateAndSave(user, result)) {
	    model.addAttribute("user", new User());
	    return "redirect:/security/login";
	}
	populateModel(model);
	return "userCreate";
    }

    @GetMapping("/toUpdate")
    public String toUpdate(Model model) {
	User user = userService.findById(getUser().getId());
	model.addAttribute("user", user);
	populateModel(model);
	return "userUpdate";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") User user,
	    BindingResult result, Model model) {
	user.setId(getUser().getId());
	if (validateAndSave(user, result)) {
	    return "redirect:/home/welcome";
	}
	populateModel(model);
	return "userUpdate";
    }

    private boolean validateAndSave(User user, BindingResult result) {
	validate(user, result);
	if (!result.hasErrors()) {
	    userService.save(user);
	    return true;
	}
	return false;
    }

    private void validate(User user, BindingResult result) {
	Civility civility = user.getCivility();
	if (Long.valueOf(0L).equals(civility.getId())) {
	    result.rejectValue("civility.id", "error.commons.required");
	}
	if (!userService.validateEmail(user)) {
	    result.rejectValue("email", "error.entities.user.duplicateEmail");
	}
    }

    private void populateModel(Model model) {
	List<CivilityDTO> civilities = civilityService
		.findAllAsDTO(getAppLanguage());
	model.addAttribute("civilities", civilities);
	model.addAttribute("roles", User.Role.values());
    }
}
