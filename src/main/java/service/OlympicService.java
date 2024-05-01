package service;

import jakarta.transaction.Transactional;

public interface OlympicService {
	@Transactional
	public void makeTickets(Long sportId, Long competitionId, double price, int amount);
}
