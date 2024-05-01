package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{

}
