package com.springBoot.examenOpdracht;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Competition;
import domain.Sport;
import repository.CompetitionRepository;
import repository.SportRepository;
import service.SportService;

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

	@GetMapping
	public String listSport(Model model) {
		model.addAttribute("sportList", sportRepository.findAll());
		return "overview";
	}
	
	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);
		Sport s;
		List<Competition> comp;
		if (!sport.isPresent())
			return "redirect:/sports/list";
		else {
			s = sport.get();
			comp = sportRepository.findAllCompetitions(s.getId());
		}
			
		model.addAttribute("sport", s);
		model.addAttribute("competitions", comp);
		return "detailSport";
	}
	
}
