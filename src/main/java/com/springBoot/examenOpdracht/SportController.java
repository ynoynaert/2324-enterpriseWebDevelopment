package com.springBoot.examenOpdracht;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
			comp.sort(Comparator.comparing(Competition::getDate).thenComparing(Competition::getTime));
		}
			
		model.addAttribute("sport", s);
		model.addAttribute("competitions", comp);
		return "detailSport";
	}
	
	@GetMapping("/addCompetition/{id}")
	public String showForm(@PathVariable("id") long sportId, Model model) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";
		return "addCompetition";
	}
	
	@PostMapping("/addCompetition/{id}")
	public String addCompetitionToSport(@PathVariable("id") long sportId, Competition comp, BindingResult bindingResult, Model model, Locale locale) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error", messageSource.getMessage("", null, locale)));  //TODO

			return "addCompetition";
		}
		Optional<Sport> sport = sportRepository.findById(sportId);
		Sport s = sport.get();
		
		s.addCompetition(comp);
		competitionRepository.save(comp);
		
		return "redirect:/sports/{id}";
	}
	
}
