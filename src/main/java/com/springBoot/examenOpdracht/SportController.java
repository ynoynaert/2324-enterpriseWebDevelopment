package com.springBoot.examenOpdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.CompetitionRepository;
import repository.SportRepository;
import service.SportService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/sports")
public class SportController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SportRepository sportRepository;
	
	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Autowired
	private SportService sportService;

	@GetMapping(value = "list")
	public String listSport(Model model) {
		model.addAttribute("sportList", sportRepository.findAll());
		return "overview";
	}
}
