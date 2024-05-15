package com.springBoot.examenOpdracht;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Competition;
import domain.Sport;
import domain.Stadium;
import exceptions.ValueNotFoundError;
import repository.CompetitionRepository;
import repository.SportRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class OlympicRestControllerTest {

	@Mock
	private CompetitionRepository mock;
	
	@Autowired
	private MockMvc mockMvc;

	private OlympicRestController controller;

	private final Long ID = 6L;
	private final LocalDate DATE = LocalDate.of(2024, 8, 5);
	private final String OLYMPICNUMBER1 = "12002";
	private final String OLYMPICNUMBER2 = "12000";
	private final double PRICE = 15;
	private final Sport SPORT = new Sport(ID, "Ctest");
	private final Stadium STADIUM = new Stadium("Ctest", SPORT);
	private final int TICKETLEFT = 5;
	private final LocalTime TIME = LocalTime.of(14, 0);
	private final int TOTALTICKETS = 45;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new OlympicRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "competitionRepository", mock);
	}

	private Optional<Competition> aCompetition(Long Id, LocalDate date, String olympcNumber1, String olympcNumber2,
			double price, Sport sport, Stadium stadium, int ticketLeft, LocalTime time, int totalTickets) {
		Competition c = new Competition();
		c.setId(Id);
		c.setDate(date);
		c.setOlympicNumber1(olympcNumber1);
		c.setOlympicNumber2(olympcNumber2);
		c.setPrice(price);
		c.setSport(sport);
		c.setStadium(stadium);
		c.setTicketLeft(ticketLeft);
		c.setTime(time);
		c.setTotalTickets(totalTickets);

		return Optional.of(c);
	}

	private void performRest(String uri) throws Exception {		
		mockMvc.perform(get(uri))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.sport").value(SPORT))
				.andExpect(jsonPath("$.stadium").value(STADIUM))
				.andExpect(jsonPath("$.olympicNumber1").value(OLYMPICNUMBER1))
				.andExpect(jsonPath("$.olympicNumber2").value(OLYMPICNUMBER2))
				.andExpect(jsonPath("$.price").value(PRICE))
				.andExpect(jsonPath("$.totalTickets").value(TOTALTICKETS))
				.andExpect(jsonPath("$.ticketLeft").value(TICKETLEFT));
		;
	}

	@Test
	public void testGetTicketsLeft_isOk() throws Exception {
		Mockito.when(mock.findById(ID)).thenReturn(aCompetition(ID, DATE, OLYMPICNUMBER1, OLYMPICNUMBER2, PRICE, SPORT,
				STADIUM, TICKETLEFT, TIME, TOTALTICKETS));
		performRest("/rest/ticketsLeft/" + ID);
		Mockito.verify(mock).findById(ID);
	}

	@Test
	public void testGetTicketsLeft_notFound() throws Exception {
		Mockito.when(mock.findById(ID)).thenThrow(new ValueNotFoundError("Competition", ID));
		Exception exception = assertThrows(Exception.class, () -> {
			mockMvc.perform(get("/rest/ticketsLeft/" + ID)).andReturn();
		});

		assertTrue(exception.getCause() instanceof ValueNotFoundError);
		Mockito.verify(mock).findById(ID);
	}

	@Test
	public void testGetAllCompetitions_emptyList() throws Exception {
		Mockito.when(mock.findAll()).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/rest/sports/" + SPORT.getId())).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty());

		Mockito.verify(mock).findBySportId(6L);
	}

	@Test
	public void testGetAllCompetitions_noEmptyList() throws Exception {
		Optional<Competition> competition = aCompetition(ID, DATE, OLYMPICNUMBER1, OLYMPICNUMBER2, PRICE, SPORT,
				STADIUM, TICKETLEFT, TIME, TOTALTICKETS);
		List<Competition> listCompetitions = List.of(competition.get());
		Mockito.when(mock.findBySportId(6L)).thenReturn(listCompetitions);

		mockMvc.perform(get("/rest/sports/" + SPORT.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].id").value(ID))
				.andExpect(jsonPath("$[0].sport").value(SPORT))
				.andExpect(jsonPath("$[0].stadium").value(STADIUM))
				.andExpect(jsonPath("$[0].olympicNumber1").value(OLYMPICNUMBER1))
				.andExpect(jsonPath("$[0].olympicNumber2").value(OLYMPICNUMBER2))
				.andExpect(jsonPath("$[0].price").value(PRICE))
				.andExpect(jsonPath("$[0].totalTickets").value(TOTALTICKETS))
				.andExpect(jsonPath("$[0].ticketLeft").value(TICKETLEFT));

		Mockito.verify(mock).findBySportId(6L);
	}

}
