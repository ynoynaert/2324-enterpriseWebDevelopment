package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Competition;
import domain.MyUser;
import domain.Ticket;
import repository.CompetitionRepository;
import repository.TicketRepository;
import repository.UserRepository;

public class OlympicServiceImpl implements OlympicService {

	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;

	public void addTicketToComp(Ticket ticket, MyUser user) {
		Optional<Competition> competition = competitionRepository.findById(ticket.getCompetition().getId());
		Competition comp = competition.get();

		comp.addTickets(ticket);
		comp.setTicketLeft(comp.getTicketLeft() - ticket.getAmount());
		user.addTicket(ticket);
		
		ticketRepository.save(ticket);
		competitionRepository.save(comp);
		userRepository.save(user);
	}

}
