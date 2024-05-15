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
import io.netty.util.internal.SystemPropertyUtil;
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

	
	@GetMapping("/sports/{id}")
	public List<Competition> getCompetitions(@PathVariable("id") long id) {
		List<Competition> competitions = competitionRepository.findBySportId(id);
		return competitions;
	}
	
	@GetMapping("/ticketsLeft/{id}")
	public Competition getCompetition(@PathVariable("id") long id) {
		Optional<Competition> competitionOptional = competitionRepository.findById(id);
		if(!competitionOptional.isPresent())
			throw new ValueNotFoundError("Competition", id);
		return competitionOptional.get();
	}
}
