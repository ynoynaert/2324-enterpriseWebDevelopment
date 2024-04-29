package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Competition;
import domain.Sport;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long>{
    Sport findByName(String name);
    List<Competition> findAllCompetitions();
}
