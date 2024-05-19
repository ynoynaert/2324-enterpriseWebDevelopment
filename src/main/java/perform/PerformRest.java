package perform;

import org.springframework.web.reactive.function.client.WebClient;

import domain.Competition;
import dtos.CompetitionDTO;
import reactor.core.publisher.Mono;

public class PerformRest {

	private final String SERVER_URI = "http://localhost:8080/rest";
	private WebClient webClient = WebClient.create();

	public PerformRest() throws Exception {
		System.out.println("\n------- GET ALL COMPETITIONS OF BASKETBALL-------");
		getallCompetitions(4L);
		
		System.out.println("\n------- GET TICKETS LEFT OF COMPETITION 9 -------");
		getCompetition(9L);

	}

	// GET ALL COMPETITIONS
	private void getallCompetitions(Long id) {
		webClient.get().uri(SERVER_URI + "/sports/" + id).retrieve().bodyToFlux(Competition.class).flatMap(comp -> {
			printCompDataVol(comp);
			return Mono.empty();
		}).blockLast();
	}
	
	// GET COMPETITION
	private void getCompetition(Long id) {
		webClient.get()
	    .uri(SERVER_URI + "/ticketsLeft/" + id)
	    .retrieve()
	    .bodyToMono(CompetitionDTO.class)
	    .doOnSuccess(c -> printCompDataTicketsLeft(c))
	    .block();
	}

	private void printCompDataVol(Competition comp) {
		if (comp != null) {
			System.out.printf(
					"ID: %s, Date: %s, time: %s, sport: %s, stadium: %s, olympic number 1: %s, olympic number 2: %s,"
							+ " totalTickets: %d, ticketsLeft: %d, price: €%.2f %n",
					comp.getId(), comp.getDate(), comp.getTime(), comp.getSport().getName(),
					comp.getStadium().getName(), comp.getOlympicNumber1(), comp.getOlympicNumber2(),
					comp.getTotalTickets(), comp.getTicketLeft(), comp.getPrice());
		} else {
			System.out.println("Competition is null");
		}
	}

	private void printCompDataTicketsLeft(CompetitionDTO comp) {
		if (comp != null) {
			System.out.printf(
					"ID: %s, tickets left: %d%n",
					comp.id(), comp.ticketsLeft());
		} else {
			System.out.println("Competition is null");
		}
	}
}
