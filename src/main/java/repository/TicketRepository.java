package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Competition;
import domain.MyUser;
import domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
	List<Object[]> findByOwnerAndCompetitionGroupByCompetition(@Param("owner") MyUser user);
	Optional<Long> AmountOfTicketByOwnerAndCompetition(@Param("owner")MyUser user, @Param("comp")Competition comp);
	Optional<Long> AmountOfTicketByOwner(@Param("owner") MyUser user);
}
