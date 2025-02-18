package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Competition;
import domain.Sport;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
	List<Competition> findBySportOrderByDateAscTimeAsc(Sport s);
	List<Competition> findBySportId(@Param("sportId") Long id);
	
}
