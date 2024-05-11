package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@NamedQueries({
		@NamedQuery(name = "Ticket.findByOwnerAndCompetitionGroupByCompetition", 
				query = "SELECT t.competition.sport, t.competition.date, t.competition.time, "
				+ "t.competition.stadium, t.competition.price, COUNT(t.competition.id)" + "FROM Ticket t "
				+ "WHERE t.owner = :owner "
				+ "GROUP BY t.competition.id "
				+ "ORDER BY t.competition.sport, t.competition.date, t.competition.time") })
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@Setter
	@Getter
	private Competition competition;

	@ManyToOne
	@Setter
	@Getter
	private MyUser owner;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Ticket(MyUser owner) {
		setOwner(owner);
	}
}
