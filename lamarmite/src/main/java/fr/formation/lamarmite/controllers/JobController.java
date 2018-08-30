package fr.formation.lamarmite.controllers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
@Secured("ROLE_ADMIN")
public class JobController {

    private final ApplicationContext context;

    @Autowired
    protected JobController(ApplicationContext context) {
	this.context = context;
    }

    @GetMapping("/importUsers")
    public String importUsers() throws Exception {
	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job importUserJob = (Job) context.getBean("importUsersJob");
	jobLauncher.run(importUserJob, new JobParameters());
	return "redirect:/home/welcome";
    }

    @GetMapping("/importCivilities")
    public String importCivilities() throws Exception {
	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job importUserJob = (Job) context.getBean("importCivilitiesJob");
	jobLauncher.run(importUserJob, new JobParameters());
	return "redirect:/home/welcome";
    }
}
