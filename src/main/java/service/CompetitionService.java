package service;

import java.util.List;

import domain.Competition;

public interface CompetitionService {
	public List<Competition> findAll();
	
	public Competition findById(Long id);
	
	public Competition save(Competition contact);
}
