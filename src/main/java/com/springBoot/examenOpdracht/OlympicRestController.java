package com.springBoot.examenOpdracht;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Competition;
import domain.Discipline;
import domain.Sport;
import domain.Stadium;
import exceptions.ValueNotFoundError;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;

@RestController
@RequestMapping(value = "/rest")
public class OlympicRestController {

	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;
	@Autowired
	private StadiumRepository stadiumRepository;

	@GetMapping("/sports")
	public List<Sport> getSports() {
		Iterable<Sport> iterableSports = sportRepository.findAll();
		List<Sport> listSports = new ArrayList<>();
		for (Sport sport : iterableSports) {
			listSports.add(sport);
		}
		

		return listSports;
	}
	
	@GetMapping("/sports/{id}")
	public Sport getSport(@PathVariable("id") long id) {
		Optional <Sport> sportOptional = sportRepository.findById(id);
		if(!sportOptional.isPresent())
			throw new ValueNotFoundError("Sport", id);
		return sportOptional.get();
	}

	@GetMapping("/competitions")
	public List<Competition> getCompetitions() {
		return competitionRepository.findAll();
	}
	
	@GetMapping("/competitions/{id}")
	public Competition getCompetition(@PathVariable("id") long id) {
		Optional<Competition> competitionOptional = competitionRepository.findById(id);
		if(!competitionOptional.isPresent())
			throw new ValueNotFoundError("Competition", id);
		return competitionOptional.get();
	}

	@GetMapping("/stadiums")
	public List<Stadium> getStadiums() {
		Iterable<Stadium> iterableStadiums = stadiumRepository.findAll();
		List<Stadium> listStadium = new ArrayList<>();
		for (Stadium stadium : iterableStadiums) {
			listStadium.add(stadium);
		}

		return listStadium;
	}
	
	@GetMapping("/stadiums/{id}")
	public Stadium getStadium(@PathVariable("id") long id) {
		Optional<Stadium> stadiumOptional = stadiumRepository.findById(id);
		if(!stadiumOptional.isPresent())
			throw new ValueNotFoundError("Stadium", id);
		return stadiumOptional.get();
	}

	@GetMapping("/disciplines")
	public List<Discipline> getDisciplines() {
		Iterable<Discipline> iterableDiscipline = disciplineRepository.findAll();
		List<Discipline> listDiscipline = new ArrayList<>();
		for (Discipline discipline : iterableDiscipline) {
			listDiscipline.add(discipline);
		}

		return listDiscipline;
	}
	
	@GetMapping("/disciplines/{id}")
	public Discipline getDiscipline(@PathVariable("id") long id) {
		Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);
		if(!optionalDiscipline.isPresent())
			throw new ValueNotFoundError("Discipline", id);
		return optionalDiscipline.get();
	}
}
