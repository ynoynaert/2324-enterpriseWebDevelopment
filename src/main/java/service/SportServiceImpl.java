package service;

import org.springframework.beans.factory.annotation.Autowired;

import repository.SportRepository;

public class SportServiceImpl implements SportService {
	
	@Autowired
    private SportRepository sportRepository;	

}
