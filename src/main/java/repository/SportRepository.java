package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Sport;

public interface SportRepository extends CrudRepository<Sport, Long>{

}
