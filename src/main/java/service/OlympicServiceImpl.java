package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import domain.Competition;
import domain.Sport;
import domain.Ticket;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;

public class OlympicServiceImpl implements OlympicService {

	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private StadiumRepository stadiumRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public void makeTickets(Long sportId, Long competitionId, double price, int amount) {
		Optional<Sport> sport = sportRepository.findById(sportId);
		Optional<Competition> competition = competitionRepository.findById(competitionId);

		if (sport.isPresent() && competition.isPresent()) {
			for (int i = 0; i <= amount; i++) {
				Ticket ticket = new Ticket(price);
				ticket.setCompetition(competition.get());
				ticketRepository.save(ticket);
			}
		}

	}

}
