package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Competition;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long>{

}
