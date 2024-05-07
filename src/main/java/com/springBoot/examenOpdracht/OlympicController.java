package com.springBoot.examenOpdracht;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Competition;
import domain.Role;
import domain.Sport;
import domain.Stadium;
import jakarta.validation.Valid;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.OlympicService;
import validator.CompetitionValidator;

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
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private OlympicService olympicService;
	@Autowired
	private CompetitionValidator competitionValidator;

	@ModelAttribute("username")
	public String username(Principal principal) {
		return principal.getName();
	}

	@ModelAttribute("role")
	public Role role(Principal principal) {
		return usersRepository.findByUsername(principal.getName()).getRole();
	}

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
		List<Competition> comp = competitionRepository.findBySportOrderByDateAscTimeAsc(sport.get());
		model.addAttribute("sport", s);
		model.addAttribute("competitions", comp);
		return "detailSport";
	}

	@GetMapping("/{id}/addCompetition")
	public String showAddCompetitionToSport(@PathVariable("id") long id, Model model, Authentication authentication) {
		Optional<Sport> sport = sportRepository.findById(id);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";

		List<Stadium> stad = stadiumRepository.findBySport(sport.get());
		model.addAttribute("competition", new Competition());
		model.addAttribute("sport", sport.get());
		model.addAttribute("stadiums", stad);
		return "addCompetition";
	}

	@PostMapping("/{id}/addCompetition")
	public String addCompetitionToSport(@RequestParam("id") long id, @Valid Competition competition,
			BindingResult bindingResult, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";
		Sport s = sport.get();
		model.addAttribute("sport", s);

		competitionValidator.validate(competition, bindingResult);

		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error",
			// messageSource.getMessage("", null, locale))); //TODO
			return "addCompetition";
		}

		s.addCompetition(competition);
		competition.setSport(s);
		competition.setTicketLeft(competition.getTotalTickets());
		competitionRepository.save(competition);
		olympicService.makeTickets(id, competition.getId(), competition.getPrice(), competition.getTotalTickets());

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

	@PostMapping("/{id}/buyTickets/{compId}")
	public String buyTickets(@RequestParam("id") long sportId, Competition comp, BindingResult bindingResult,
			Model model, Locale locale) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error",
			// messageSource.getMessage("", null, locale))); //TODO
			return "buyTickets";
		}
		Optional<Sport> sport = sportRepository.findById(sportId);
		Sport s = sport.get();
		// TODO: logica hier nog uitwerken

		return "redirect:/sports/{id}";
	}

	@GetMapping("/{id}/tickets")
	public String showTickets(@PathVariable("id") long sportId, Model model) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		if (!sport.isPresent())
			return "redirect:/sports";

		model.addAttribute("sport", sport.get());
		// TODO: verder uitwerken als account effectief bestaat
		return "overviewTickets";
	}

	// TODO: @PostMapping

}
