package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
@JsonPropertyOrder({ "competition_id", "competition_sport", "competition_stadium", "competition_date", "competition_time", "competition_olympicNumber1", "competition_olympicNumber2", "competition_totalTickets", "competition_ticketLeft", "competition_price" })
@NamedQueries({
	@NamedQuery(name = "Competition.findBySportId", query = "SELECT c FROM Competition c WHERE sport.id = :sportId")
})
public class Competition implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("competition_id")
	private Long id;

	@ManyToOne
	@Setter
	@Getter
	@JsonProperty("competition_sport")
	private Sport sport;

	@ManyToOne
	@Setter
	@Getter
	@JsonProperty("competition_stadium")
	private Stadium stadium;

	@NotNull
	@Getter
	@Setter
	@JsonProperty("competition_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotNull
	@Getter
	@Setter
	@JsonProperty("competition_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime time;

	@Column(unique = true)
	@NotNull(message = "{validition.empty}")
	@NotBlank(message = "{validition.empty}")
	@Getter
	@Setter
	@JsonProperty("competition_olympicNumber1")
	private String olympicNumber1;

	@NotNull(message = "{validition.empty}")
	@NotBlank(message = "{validition.empty}")
	@Getter
	@Setter
	@JsonProperty("competition_olympicNumber2")
	private String olympicNumber2;

	@ManyToMany
	@Getter
	@JsonIgnore
	private List<Discipline> disciplines = new ArrayList<>();

	@NotNull
	@Getter
	@Setter
	@JsonProperty("competition_totalTickets")
	@Range(min = 1, max = 50, message = "{competition.totalTickets.Range.message}")
	private int totalTickets;

	@NotNull
	@Getter
	@Setter
	@JsonProperty("competition_price")
	@DecimalMin(value = "0.01", message = "{ticket.price.min.message}")
	@DecimalMax(value = "149.99", message = "{ticket.price.max.message}")
	private double price;

	@NotNull
	@Getter
	@Setter
	@JsonProperty("competition_ticketLeft")
	@Range(min = 0, max = 50, message = "{competition.ticketLeft.Range.message}")
	private int ticketLeft;

	@OneToMany(mappedBy = "competition", fetch = FetchType.EAGER)
	@JsonIgnore
	@Getter
	private List<Ticket> tickets = new ArrayList<>();

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, int totalTickets,
			double price) {
		setDate(date);
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(totalTickets);
	}

	public Competition(LocalDate date, LocalTime time, String olympicNumber1, String olympicNumber2, int totalTickets,
			double price, int ticketLeft) {
		setDate(date);
		setOlympicNumber1(olympicNumber1);
		setOlympicNumber2(olympicNumber2);
		setTime(time);
		setPrice(price);
		setTotalTickets(totalTickets);
		setTicketLeft(ticketLeft);
	}

	public void addTickets(Ticket ticket) {
		tickets.add(ticket);
	}

	public void addDisciplines(Discipline discipline) {
		disciplines.add(discipline);
	}

}
