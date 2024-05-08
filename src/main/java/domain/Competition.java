package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
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

	@Nullable
	@Getter
	@Setter
	private String discipline1;

	@Nullable
	@Getter
	@Setter
	private String discipline2;

	@NotNull
	@Getter
	@Setter
	@Range(min = 1, max = 50, message = "{competition.totalTickets.Range.message}")
	private int totalTickets;

	@Getter
	@Setter
	private double price;

	@NotNull
	@Getter
	@Setter
	@Range(min = 0, max = 49, message = "{competition.ticketLeft.Range.message}")
	private int ticketLeft;

	@OneToMany(mappedBy = "competition")
	private List<Ticket> tickets = new ArrayList<>();

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, String discipline1,
			String discipline2, int totalTickets, double price, int ticketLeft) {
		setDate(date);
		setDiscipline1(discipline1);
		setDiscipline2(discipline2);
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(ticketLeft);
		addTickets(price, totalTickets);
	}

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, String discipline1,
			String discipline2, int totalTickets, double price) {
		setDate(date);
		setDiscipline1(discipline1);
		setDiscipline2(discipline2);
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(totalTickets);
		addTickets(price, totalTickets);
	}

	private void addTickets(double price, int totalTickets) {
		for (int i = 0; i <= totalTickets; i++) {
			Ticket ticket = new Ticket(price);
			tickets.add(ticket);
		}
	}
}
