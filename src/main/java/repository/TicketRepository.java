package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Competition;
import domain.Ticket;
import domain.MyUser;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
	List<Ticket> findByCompetition(Competition c);
	//List<Ticket> findByOwner(MyUser a);
}
