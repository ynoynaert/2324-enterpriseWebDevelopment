package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Competition implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@Setter
	@Getter
	private Sport sport;

	@ManyToOne
	@Setter
	@Getter
	private Stadium stadium;

	@NotNull
	@Getter
	@Setter
	private LocalDate date;

	@NotNull
	@Getter
	@Setter
	private LocalTime time;

	@Column(unique = true)
	@NotNull
	@NotEmpty
	@NotBlank
	@Getter
	@Setter
	private String olympicNumber1;

	@NotNull
	@NotEmpty
	@NotBlank
	@Getter
	@Setter
	private String olympicNumber2;
	
	@ManyToMany
	@Getter
	private List<Discipline> disciplines = new ArrayList<>();

	@NotNull
	@Getter
	@Setter
	@Range(min = 1, max = 50, message = "{competition.totalTickets.Range.message}")
	private int totalTickets;

	@NotNull
	@Getter
	@Setter
	@DecimalMin(value = "0.01", message = "{ticket.price.min.message}")
	@DecimalMax(value = "149.99", message = "{ticket.price.max.message}")
	private double price;

	@NotNull
	@Getter
	@Setter
	@Range(min = 0, max = 50, message = "{competition.ticketLeft.Range.message}")
	private int ticketLeft;

	@OneToMany(mappedBy = "competition", fetch = FetchType.EAGER)
	@Getter
	private List<Ticket> tickets = new ArrayList<>();

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, int totalTickets, double price, int ticketLeft) {
		setDate(date);		
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(ticketLeft);
	}

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, int totalTickets, double price) {
		setDate(date);		
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(totalTickets);
	}
	
	public void addTickets(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public void addDisciplines(Discipline discipline) {
		disciplines.add(discipline);
	}


}
