package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.MyUser;
import domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
	List<Object[]> findByOwnerAndCompetitionGroupByCompetition(@Param("owner") MyUser user);
}
