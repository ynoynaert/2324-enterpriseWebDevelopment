package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import domain.Competition;
import domain.MyUser;
import domain.Sport;
import domain.Ticket;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;

public class OlympicServiceImpl implements OlympicService {

	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public void addTicketToComp(Ticket ticket, MyUser user) {
		Optional<Competition> competition = competitionRepository.findById(ticket.getCompetition().getId());
		Competition comp = competition.get();
		System.out.println(ticket.getAmount());

		comp.addTickets(ticket);
		comp.setTicketLeft(comp.getTicketLeft() - ticket.getAmount());
		user.addTicket(ticket);
		competitionRepository.save(comp);
	}

}
