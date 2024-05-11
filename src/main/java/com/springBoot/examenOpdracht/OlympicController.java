package com.springBoot.examenOpdracht;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Competition;
import domain.Discipline;
import domain.MyUser;
import domain.Sport;
import domain.Stadium;
import domain.Ticket;
import jakarta.validation.Valid;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.OlympicService;
import validator.CompetitionValidator;
import validator.TicketValidator;

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
	private DisciplineRepository disciplineRepository;
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
	@Autowired
	private TicketValidator ticketValidator;

	@ModelAttribute("username")
	public String username(Principal principal) {
		return principal.getName();
	}

	@ModelAttribute("role")
	public String role(Principal principal) {
		return usersRepository.findByUsername(principal.getName()).getRole().toString();
	}

	@ModelAttribute("user")
	public MyUser user(Principal principal) {
		return usersRepository.findByUsername(principal.getName());
	}

	// OVERVIEW VAN SPORTEN
	@GetMapping
	public String listSport(Model model) {
		model.addAttribute("sportList", sportRepository.findAll());
		return "overview";
	}

	// OVERVIEW VAN COMPETITIES
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

	// FORM VOOR ADMIN COMP TOEVOEGEN
	@GetMapping("/{id}/addCompetition")
	public String showAddCompetitionToSport(@PathVariable("id") long id, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";

		List<Stadium> stad = stadiumRepository.findBySport(sport.get());
		List<Discipline> disc = disciplineRepository.findBySport(sport.get());
		model.addAttribute("competition", new Competition());
		model.addAttribute("sport", sport.get());
		model.addAttribute("stadiums", stad);
		model.addAttribute("disciplines", disc);
		return "addCompetition";
	}

	@PostMapping("/{id}/addCompetition")
	public String addCompetitionToSport(@RequestParam("id") long id, @Valid Competition competition,
			BindingResult bindingResult, @RequestParam Map<String, String> requestParams, Model model) {
		Optional<Sport> sport = sportRepository.findById(id);
		if (!sport.isPresent())
			return "redirect:/sports/{id}";
		Sport s = sport.get();
		List<Stadium> stad = stadiumRepository.findBySport(s);
		List<Discipline> disc = disciplineRepository.findBySport(s);
		model.addAttribute("sport", s);
		model.addAttribute("stadiums", stad);
		model.addAttribute("disciplines", disc);

		competitionValidator.validate(competition, bindingResult);

		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error",
			// messageSource.getMessage("", null, locale))); //TODO
			return "addCompetition";
		}

		Set<Discipline> selectedDisciplines = new HashSet<>();
		for (Map.Entry<String, String> entry : requestParams.entrySet()) {
			if (entry.getKey().startsWith("disciplines")) {
				long disciplineId = Long.parseLong(entry.getValue());
				Optional<Discipline> disciplineOptional = disciplineRepository.findById(disciplineId);
				disciplineOptional.ifPresent(selectedDisciplines::add);
			}
		}

		if (!selectedDisciplines.isEmpty()) {
			for (Discipline disci : selectedDisciplines)
				competition.addDisciplines(disci);
		}

		s.addCompetition(competition);

		competition.setSport(s);
		competition.setTicketLeft(competition.getTotalTickets());
		competitionRepository.save(competition);

		return "redirect:/sports/{id}";
	}

	// FORM VOOR USERS TICKETS KOPEN
	@GetMapping("/{id}/buyTickets/{compId}")
	public String showBuyTickets(@PathVariable("id") long sportId, @PathVariable("compId") long compId, Model model, Principal principal) {
		MyUser user = usersRepository.findByUsername(principal.getName());
		Optional<Sport> sport = sportRepository.findById(sportId);
		Optional<Competition> competition = competitionRepository.findById(compId);
		Optional<Long> ticketsBoughtOp = ticketRepository.AmountOfTicketByOwnerAndCompetition(user, competition.get());
		int ticketsBought = ticketsBoughtOp.map(Long::intValue).orElse(0);
		
		if (!sport.isPresent() && !competition.isPresent())
			return "redirect:/sports/{id}";
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("sport", sport.get());
		model.addAttribute("competition", competition.get());
		model.addAttribute("ticketsBought", ticketsBought);
		return "buyTickets";
	}

	@PostMapping("/{id}/buyTickets/{compId}")
	public String buyTickets(@RequestParam("id") long sportId, @RequestParam("compId") long compId,
			@Valid Ticket ticket, BindingResult bindingResult, Model model, Locale locale, Principal principal, RedirectAttributes redirectAttributes) {
		MyUser user = usersRepository.findByUsername(principal.getName());
		Optional<Competition> competition = competitionRepository.findById(compId);
		Competition comp = competition.get();
		Optional<Long> ticketsBoughtOp = ticketRepository.AmountOfTicketByOwnerAndCompetition(user, competition.get());
		int ticketsBought = ticketsBoughtOp.map(Long::intValue).orElse(0);
		
		ticket.setOwner(user);
		ticket.setCompetition(comp);
		ticketValidator.validate(ticket, bindingResult);

		model.addAttribute("sport", comp.getSport());
		model.addAttribute("competition", comp);
		model.addAttribute("ticketsBought", ticketsBought);
		
		if (bindingResult.hasErrors()) {
			// model.addAttribute("message", new Message("error",
			// messageSource.getMessage("", null, locale))); //TODO
			return "buyTickets";
		}
		
		ticketRepository.save(ticket);
		olympicService.addTicketToComp(ticket, user);

		int purchase = ticket.getAmount();
		redirectAttributes.addFlashAttribute("purchase", purchase);
		
		return "redirect:/sports/{id}";
	}

	// OVERVIEW VOOR USERS VAN GEKOCHTE TICKETS
	@GetMapping("/tickets")
	public String showTickets(Model model, Principal principal) {
		MyUser user = usersRepository.findByUsername(principal.getName());
		List<Object[]> tickets = ticketRepository.findByOwnerAndCompetitionGroupByCompetition(user);
		model.addAttribute("tickets", tickets);
		return "overviewTickets";
	}

}
