package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@NamedQueries({
		@NamedQuery(name = "Ticket.findByOwnerGroupByCompetition", query = "SELECT t.competition.sport, t.competition.date, t.competition.time, "
				+ "t.competition.stadium, t.competition.price, SUM(t.amount) " + "FROM Ticket t "
				+ "WHERE t.owner = :owner " + "GROUP BY t.competition.id "
				+ "ORDER BY t.competition.sport, t.competition.date, t.competition.time"),
		@NamedQuery(name = "Ticket.AmountOfTicketByOwnerAndCompetition", query = "SELECT SUM(t.amount) FROM Ticket t "
				+ "WHERE t.owner = :owner and t.competition = :comp " + "GROUP BY t.competition.id"),
		@NamedQuery(name = "Ticket.AmountOfTicketByOwner", query = "SELECT SUM(t.amount) FROM Ticket t "
				+ "WHERE t.owner = :owner GROUP BY t.owner") })
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@Setter
	@Getter
	private Competition competition;

	@ManyToOne
	@Setter
	@Getter
	private MyUser owner;

	@NotNull
	@Min(value = 1, message = "{ticket.amount.min}")
	@Setter
	@Getter
	private int amount;

	public Ticket(int amount) {
		setAmount(amount);
	}
}
