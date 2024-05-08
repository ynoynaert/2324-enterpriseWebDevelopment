package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Discipline;
import domain.Sport;

public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
	List<Discipline> findBySport(Sport sport);
}
