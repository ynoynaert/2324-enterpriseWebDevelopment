package com.springBoot.examenOpdracht;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import domain.Competition;
import domain.MyUser;
import domain.Sport;
import domain.Stadium;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class OlympicControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StadiumRepository stadiumRepository;
	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private CompetitionRepository competitionRepository;

	// ACCESS TESTS
	// MAIN PAGE
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testUserHasAccesToSportPage() throws Exception {
		MyUser user = userRepository.findByUsername("user");
		assertNotNull(user);

		mockMvc.perform(get("/sports")).andExpect(status().isOk()).andExpect(view().name("overview"))
				.andExpect(model().attributeExists("user")).andExpect(model().attributeExists("role"))
				.andExpect(model().attributeExists("username")).andExpect(model().attributeExists("sportList"));
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAminHasAccesToSportPage() throws Exception {
		MyUser user = userRepository.findByUsername("admin");
		assertNotNull(user);

		mockMvc.perform(get("/sports")).andExpect(status().isOk()).andExpect(view().name("overview"))
				.andExpect(model().attributeExists("user")).andExpect(model().attributeExists("role"))
				.andExpect(model().attributeExists("username")).andExpect(model().attributeExists("sportList"));
	}

	// SPORT DETAIL PAGE
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testUserHasAccesToSportDetailPage() throws Exception {
		MyUser user = userRepository.findByUsername("user");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1")).andExpect(status().isOk()).andExpect(view().name("detailSport"))
				.andExpect(model().attributeExists("user")).andExpect(model().attributeExists("role"))
				.andExpect(model().attributeExists("username")).andExpect(model().attributeExists("sport"))
				.andExpect(model().attributeExists("competitions")).andExpect(model().attributeExists("ticketsBought"));
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAminHasAccesToSportDetailPage() throws Exception {
		MyUser user = userRepository.findByUsername("admin");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1")).andExpect(status().isOk()).andExpect(view().name("detailSport"))
				.andExpect(model().attributeExists("user")).andExpect(model().attributeExists("role"))
				.andExpect(model().attributeExists("username")).andExpect(model().attributeExists("sport"))
				.andExpect(model().attributeExists("competitions"));
	}

	// ADD COMPETITION
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testUserHasNoAccesToAddCompetitionPage() throws Exception {
		MyUser user = userRepository.findByUsername("user");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1/addCompetition")).andExpect(status().isForbidden());
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAdminHasAccesToAddCompetitionPage() throws Exception {
		MyUser user = userRepository.findByUsername("admin");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1/addCompetition")).andExpect(status().isOk());
	}

	// GET TICKETS
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testUserHasAccesToGetTicketsPage() throws Exception {
		MyUser user = userRepository.findByUsername("user");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1/addCompetition")).andExpect(status().isForbidden());
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAdminHasNoAccesToTicketsPage() throws Exception {
		MyUser user = userRepository.findByUsername("admin");
		assertNotNull(user);

		mockMvc.perform(get("/sports/tickets")).andExpect(status().isForbidden());
	}

	// BUY TICKETS
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testUserHasAccesToBuyTicketsPage() throws Exception {
		MyUser user = userRepository.findByUsername("user");
		assertNotNull(user);

		mockMvc.perform(get("/sports/tickets")).andExpect(status().isOk());
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAdminHasNoAccesToBuyTicketsPage() throws Exception {
		MyUser user = userRepository.findByUsername("admin");
		assertNotNull(user);

		mockMvc.perform(get("/sports/1/buyTickets/1")).andExpect(status().isForbidden());
	}

	// FORM TESTS
	// ADD COMPETITION
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testGetAddCompetition() throws Exception {
		mockMvc.perform(get("/sports/1/addCompetition"))
			.andExpect(view().name("addCompetition"))
			.andExpect(model().attributeExists("competition"))
			.andExpect(model().attributeExists("sport"))
			.andExpect(model().attributeExists("stadiums"))
			.andExpect(model().attributeExists("disciplines"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetition() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    MvcResult result = mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 4).toString())
	            .param("time", "15:00")
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"))
	            .andReturn();

	    String redirectedUrl = result.getResponse().getRedirectedUrl();
	    assertEquals("/sports/" + s.getId(), redirectedUrl);

	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongDateTooEarly() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 5, 15).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongDateTooLate() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 15).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongTime() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(6, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongStadium() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", "80L")
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber1_FirstAndLastDigitEqual() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11001")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber1_StartsWithZero() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "01001")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber1_TooLong() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "110020")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber1_Letters() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "abdqd")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber1_AlreadyInDatabase() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "13579")
	            .param("olympicNumber2", "13000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber2_RangeTooBig() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "13000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber2_RangeTooLow() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "10000")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber2_Letters() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "qsdf")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongOlympicNumber2_TooLong() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "1234568")
	            .param("price", "20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongPrice() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "-20.00")
	            .param("totalTickets", "45"));
	}
	
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void testAddCompetitionWrongAmountOfTickets() throws Exception {
		Sport s = sportRepository.findByName("Basketball");
	    assertNotNull(s);
	    
	    Stadium st = stadiumRepository.findBySport(s).getFirst();
	    assertNotNull(st);
			    
	    mockMvc.perform(post("/sports/{id}/addCompetition", s.getId())
	    		.with(csrf())
				.param("id", s.getId().toString())
	    		.param("date", LocalDate.of(2024, 8, 5).toString())
	            .param("time", LocalTime.of(14, 0).toString())
	            .param("stadium", String.valueOf(st.getId()))
	            .param("olympicNumber1", "11009")
	            .param("olympicNumber2", "12000")
	            .param("price", "20.00")
	            .param("totalTickets", "100"));
	}

	// BUY TICKETS
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testGetBuyTickets() throws Exception {
		mockMvc.perform(get("/sports/1/buyTickets/1"))
		.andExpect(view().name("buyTickets"))
		.andExpect(model().attributeExists("competition"))
		.andExpect(model().attributeExists("sport"))
		.andExpect(model().attributeExists("ticketsBought"));
	}

	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testBuyTickets() throws Exception {
		Sport s = sportRepository.findByName("Athletics");
		assertNotNull(s);
		
		Competition c = competitionRepository.findBySportId(s.getId()).getFirst();
		assertNotNull(c);
		
		MvcResult result = mockMvc.perform(post("/sports/{id}/buyTickets/{compId}", s.getId(), c.getId())
				.with(csrf())
				.param("id", s.getId().toString())
				.param("compId", c.getId().toString())
				.param("amount", "4"))
				.andReturn();
		
		String redirectedUrl = result.getResponse().getRedirectedUrl();
	    assertEquals("/sports/" + s.getId(), redirectedUrl);
	}
	
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testBuyTicketsWrongAmount() throws Exception {
		Sport s = sportRepository.findByName("Athletics");
		assertNotNull(s);
		
		Competition c = competitionRepository.findBySportId(s.getId()).getFirst();
		assertNotNull(c);
		
		mockMvc.perform(post("/sports/{id}/buyTickets/{compId}", s.getId(), c.getId())
				.with(csrf())
				.param("id", s.getId().toString())
				.param("compId", c.getId().toString())
				.param("amount", "24"));
	}
	
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testBuyTicketsWrongSportID() throws Exception {
		Sport s = sportRepository.findByName("Athletics");
		assertNotNull(s);
		
		Competition c = competitionRepository.findBySportId(s.getId()).getFirst();
		assertNotNull(c);
		
		mockMvc.perform(post("/sports/{id}/buyTickets/{compId}", 10L, c.getId())
				.with(csrf())
				.param("id", "10L")
				.param("compId", c.getId().toString())
				.param("amount", "4"));
	}
	
	@WithMockUser(username = "user", roles = { "USER" })
	@Test
	public void testBuyTicketsWrongCompID() throws Exception {
		Sport s = sportRepository.findByName("Athletics");
		assertNotNull(s);
		
		mockMvc.perform(post("/sports/{id}/buyTickets/{compId}", s.getId(), 50L)
				.with(csrf())
				.param("id", s.getId().toString())
				.param("compId", "50L")
				.param("amount", "4"));
	}
}
