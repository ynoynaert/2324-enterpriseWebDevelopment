package com.springBoot.examenOpdracht;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

import domain.Sport;
import exceptions.ValueNotFoundError;
import repository.SportRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SportRestControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private SportRepository mock;
	
	private OlympicRestController controller;
	
	private final String NAME = "Test";
	private final Long ID = 6L;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new OlympicRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "sportRepository", mock);
	}
	
	private Optional<Sport> aSport(Long Id, String name) {
		Sport s = new Sport();
		s.setName(name);
		s.setId(Id);
		
		return Optional.of(s);
	}
	
	private void performRest(String uri) throws Exception {
		mockMvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(ID))
		.andExpect(jsonPath("$.name").value(NAME));
	}
	
	@Test
	public void testGetSport_isOk() throws Exception {
		Mockito.when(mock.findById(ID)).thenReturn(aSport(ID, NAME));
		performRest("/rest/sports/" + ID);
		Mockito.verify(mock).findById(ID);
	}
	
	@Test
	public void testGetSport_notFound() throws Exception {
		Mockito.when(mock.findById(ID)).thenThrow(new ValueNotFoundError("Sport", ID));
		Exception exception = assertThrows(Exception.class, () -> {
			mockMvc.perform(get("/rest/sports/" + ID)).andReturn();
		});

		assertTrue(exception.getCause() instanceof ValueNotFoundError);
		Mockito.verify(mock).findById(ID);
	}
	
	@Test
	public void testGetAllSports_emptyList() throws Exception {
		Mockito.when(mock.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/rest/sports"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty());
		
		Mockito.verify(mock).findAll();
	}

	@Test
	public void testGetAllSports_noEmptyList() throws Exception {
		Optional<Sport> sport = aSport(ID, NAME);
		List<Sport> listSport = List.of(sport.get());
		Mockito.when(mock.findAll()).thenReturn(listSport);

		mockMvc.perform(get("/rest/sports"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].id").value(ID))
				.andExpect(jsonPath("$[0].name").value(NAME));
		
		Mockito.verify(mock).findAll();
	}
	
}
