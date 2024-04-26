package service;

import java.util.List;

import domain.Sport;

public interface SportService {

	public List<Sport> findAll();
	
	public Sport findById(Long id);
	
	public Sport save(Sport contact);	
	
}