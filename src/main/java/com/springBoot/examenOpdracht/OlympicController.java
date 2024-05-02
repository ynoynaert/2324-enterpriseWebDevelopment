package com.springBoot.examenOpdracht;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import domain.Stadium;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import service.OlympicService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/sports")
public class OlympicController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SportRepository sportRepository;	
	@Autowired
	private CompetitionRepository competitionRepository;	
	@Autowired
	private StadiumRepository stadiumRepository;
	@Autowired
	private TicketRepository ticektRepository;	
	@Autowired
	private OlympicService olympicService;

	@GetMapping
	public String listSport(Model model) {
		model.addAttribute("sportList", sportRepository.findAll());
		return "overview";
	}
	
	@GetMapping(value = "/{id}")
	public String showCompetitions(@PathVariable("id") long id, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);
		if (!sport.isPresent())
			return "redirect:/sports/list";
		
		Sport s = sport.get();
		List<Competition> comp = sportRepository.findAllCompetitions(s.getId());
		comp.sort(Comparator.comparing(Competition::getDate).thenComparing(Competition::getTime));
		model.addAttribute("sport", s);
		model.addAttribute("competitions", comp);
		return "detailSport";
	}
	
	@GetMapping("/addCompetition/{id}")
	public String showAddCompetitionToSport(@PathVariable("id") long sportId, Model model) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";
		List<Stadium> stad = sportRepository.findAllStadiums(sport.get().getId());
		model.addAttribute("stadiums", stad);
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
		olympicService.makeTickets(sportId, comp.getId(), comp.getPrice(), comp.getTotalTickets());
		
		return "redirect:/sports/{id}";
	}
	
	@GetMapping("/{id}/buyTickets/{compId}")
	public String showBuyTickets(@PathVariable("id") long sportId, @PathVariable("compId") long compId, Model model) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		Optional<Competition> competition = competitionRepository.findById(compId);
		if (!sport.isPresent() && !competition.isPresent())
			return "redirect:/sports/{id}";
		model.addAttribute("sport", sport.get());
	    model.addAttribute("competition", competition.get());
		return "buyTickets";
	}
	
	//TODO: @PostMapping
	@PostMapping("/{id}/buyTickets/{compId}")
	public String buyTickets(@PathVariable("id") long sportId, Competition comp, BindingResult bindingResult, Model model, Locale locale) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error", messageSource.getMessage("", null, locale)));  //TODO
			return "buyTickets";
		}
		Optional<Sport> sport = sportRepository.findById(sportId);
		Sport s = sport.get();
		//TODO: logica hier nog uitwerken
		
		return "redirect:/sports/{id}";
	}
	
	@GetMapping("/{id}/tickets")
	public String showTickets(@PathVariable("id") long sportId, Model model) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (!sport.isPresent())
			return "redirect:/sports";

		model.addAttribute("sport", sport.get());
		//TODO: verder uitwerken als account effectief bestaat
		return "overviewTickets";
	}
	
}
