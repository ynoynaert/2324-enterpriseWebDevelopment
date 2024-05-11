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

	public void makeTicket(Long competitionId, MyUser user) {
		Optional<Competition> competition = competitionRepository.findById(competitionId);
		Competition comp = competition.get();

		Ticket ticket = new Ticket(user);
		ticket.setCompetition(comp);
		comp.addTickets(ticket);
		comp.setTicketLeft(comp.getTicketLeft() - 1);
		competitionRepository.save(comp);
		user.addTicket(ticket);
		ticketRepository.save(ticket);

	}

}
