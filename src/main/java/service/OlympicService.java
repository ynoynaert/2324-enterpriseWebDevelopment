package service;

import domain.MyUser;
import domain.Ticket;
import jakarta.transaction.Transactional;

public interface OlympicService {
	@Transactional
	public void addTicketToComp(Ticket ticket, MyUser user);
}
