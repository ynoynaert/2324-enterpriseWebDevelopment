package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Sport;
import domain.Stadium;

@Repository
public interface StadiumRepository extends CrudRepository<Stadium, Long> {
	List<Stadium> findBySport(Sport s);
}
