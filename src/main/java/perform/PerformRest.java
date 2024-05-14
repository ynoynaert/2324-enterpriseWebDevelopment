package perform;

import java.util.stream.IntStream;

import org.springframework.web.reactive.function.client.WebClient;

import domain.Competition;
import domain.Discipline;
import domain.Sport;
import domain.Stadium;
import reactor.core.publisher.Mono;

public class PerformRest {

	private final String SERVER_URI = "http://localhost:8080/rest";
	private WebClient webClient = WebClient.create();

	// TODO: nakijken of dit wel goed werkt
	public PerformRest() throws Exception {
		System.out.println("\n------- GET ALL SPORTS -------");
		getallSports();
		
		System.out.println("\n------- GET SPORT 3 -------");
		getSport(3L);

		System.out.println("\n------- GET ALL COMPETITIONS -------");
		getallCompetitions();
		
		System.out.println("\n------- GET COMPETITION 9 -------");
		getCompetition(9L);

		System.out.println("\n------- GET ALL STADIUMS -------");
		getallStadiums();

		System.out.println("\n------- GET STADIUMS 6 -------");
		getStadium(6L);

		System.out.println("\n------- GET ALL DISCIPLINES -------");
		getallDisciplines();

		System.out.println("\n------- GET DISCIPLINES 10 -------");
		getDiscipline(10L);

	}

	// GET ALL SPORTS
	private void getallSports() {
		webClient.get().uri(SERVER_URI + "/sports").retrieve().bodyToFlux(Sport.class).flatMap(sport -> {
			printSportData(sport);
			return Mono.empty();
		}).blockLast();
	}

	// GET ALL COMPETITIONS
	private void getallCompetitions() {
		webClient.get().uri(SERVER_URI + "/competitions").retrieve().bodyToFlux(Competition.class).flatMap(comp -> {
			printCompData(comp);
			return Mono.empty();
		}).blockLast();
	}

	// GET ALL STADIUMS
	private void getallStadiums() {
		webClient.get().uri(SERVER_URI + "/stadiums").retrieve().bodyToFlux(Stadium.class).flatMap(stad -> {
			printStadiumData(stad);
			return Mono.empty();
		}).blockLast();
	}

	// GET ALL DISCIPLINES
	private void getallDisciplines() {
		webClient.get().uri(SERVER_URI + "/disciplines").retrieve().bodyToFlux(Discipline.class).flatMap(dis -> {
			printDisciplineData(dis);
			return Mono.empty();
		}).blockLast();
	}

	// GET SPORT
	private void getSport(Long id) {
		webClient.get()
	    .uri(SERVER_URI + "/sports/" + id)
	    .retrieve()
	    .bodyToMono(Sport.class)
	    .doOnSuccess(s -> printSportData(s))
	    .block();
	}
	
	// GET COMPETITION
	private void getCompetition(Long id) {
		webClient.get()
	    .uri(SERVER_URI + "/competitions/" + id)
	    .retrieve()
	    .bodyToMono(Competition.class)
	    .doOnSuccess(c -> printCompData(c))
	    .block();
	}
	
	// GET STADIUM
	private void getStadium(Long id) {
		webClient.get()
	    .uri(SERVER_URI + "/stadiums/" + id)
	    .retrieve()
	    .bodyToMono(Stadium.class)
	    .doOnSuccess(s -> printStadiumData(s))
	    .block();
	}
	
	// GET DISCIPLINE
	private void getDiscipline(Long id) {
		webClient.get()
	    .uri(SERVER_URI + "/disciplines/" + id)
	    .retrieve()
	    .bodyToMono(Discipline.class)
	    .doOnSuccess(d -> printDisciplineData(d))
	    .block();
	}
	
	private void printSportData(Sport sport) {
		if (sport != null) {
			System.out.printf("ID: %d, sport: %s%n", sport.getId(), sport.getName());
		} else {
			System.out.println("Sport is null");
		}
	}

	private void printCompData(Competition comp) {
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

	private void printStadiumData(Stadium stadium) {
		if (stadium != null) {
			System.out.printf("ID: %d, name: %s, sport: %s%n", stadium.getId(), stadium.getName(),
					stadium.getSport().getName());
		} else {
			System.out.println("Stadium is null");
		}
	}

	private void printDisciplineData(Discipline discipline) {
		if (discipline != null) {
			System.out.printf("ID: %d, name: %s, sport: %s%n", discipline.getId(), discipline.getName(),
					discipline.getSport().getName());
		} else {
			System.out.println("Discipline is null");
		}
	}
}
