package service;

import domain.MyUser;
import jakarta.transaction.Transactional;

public interface OlympicService {
	@Transactional
	public void makeTicket(Long competitionId, MyUser user);
}
