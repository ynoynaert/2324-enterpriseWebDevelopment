package com.springBoot.examenOpdracht;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Competition;
import dtos.CompetitionDTO;
import exceptions.ValueNotFoundError;
import repository.CompetitionRepository;
import repository.SportRepository;

@RestController
@RequestMapping(value = "/rest")
public class OlympicRestController {

	@Autowired
	private CompetitionRepository competitionRepository;

	
	@GetMapping("/sports/{id}")
	public List<Competition> getCompetitions(@PathVariable("id") long id) {
		List<Competition> competitions = competitionRepository.findBySportId(id);
		return competitions;
	}
	
	@GetMapping("/ticketsLeft/{id}")
	public CompetitionDTO getCompetition(@PathVariable("id") long id) {
		Optional<Competition> competitionOptional = competitionRepository.findById(id);
		if(!competitionOptional.isPresent())
			throw new ValueNotFoundError("Competition", id);
		Competition comp = competitionOptional.get();
		return new CompetitionDTO(comp.getId(), comp.getTicketLeft());
	}
}
