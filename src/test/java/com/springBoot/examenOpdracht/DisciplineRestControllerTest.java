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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Discipline;
import domain.Sport;
import exceptions.ValueNotFoundError;
import repository.DisciplineRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplineRestControllerTest {

	@Mock
	private DisciplineRepository mock;
	
	private OlympicRestController controller;
	private MockMvc mockMvc;
	
	private final String NAME = "Test";
	private final Long ID = 6L;
	private final Sport SPORT = new Sport("Dtest");
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new OlympicRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "disciplineRepository", mock);
	}
	
	private Optional<Discipline> aDiscipline(Long Id, String name, Sport sport) {
		Discipline d = new Discipline();
		d.setId(Id);
		d.setName(name);
		d.setSport(sport);
		
		return Optional.of(d);
	}

	private void performRest(String uri) throws Exception {
		mockMvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(ID))
		.andExpect(jsonPath("$.name").value(NAME))
		.andExpect(jsonPath("$.sport").value(SPORT));
	}
	
	@Test
	public void testGetDiscipline_isOk() throws Exception {
		Mockito.when(mock.findById(ID)).thenReturn(aDiscipline(ID, NAME, SPORT));
		performRest("/rest/disciplines/" + ID);
		Mockito.verify(mock).findById(ID);
	}
	
	@Test
	public void testGetDiscipline_notFound() throws Exception {
		Mockito.when(mock.findById(ID)).thenThrow(new ValueNotFoundError("Sport", ID));
		Exception exception = assertThrows(Exception.class, () -> {
			mockMvc.perform(get("/rest/disciplines/" + ID)).andReturn();
		});

		assertTrue(exception.getCause() instanceof ValueNotFoundError);
		Mockito.verify(mock).findById(ID);
	}
	
	@Test
	public void testGetAllDiscipline_emptyList() throws Exception {
		Mockito.when(mock.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/rest/disciplines"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty());
		
		Mockito.verify(mock).findAll();
	}

	@Test
	public void testGetAllDisciplines_noEmptyList() throws Exception {
		Optional<Discipline> discipline = aDiscipline(ID, NAME, SPORT);
		List<Discipline> listDiscipline = List.of(discipline.get());
		Mockito.when(mock.findAll()).thenReturn(listDiscipline);

		mockMvc.perform(get("/rest/disciplines")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].id").value(ID))
				.andExpect(jsonPath("$[0].name").value(NAME))
				.andExpect(jsonPath("$[0].sport").value(SPORT));
		
		Mockito.verify(mock).findAll();
	}
	
}
