package domain;

import java.io.Serializable;
import java.time.LocalDate;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @EqualsAndHashCode(exclude = "id")
public class Competition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne @Setter @Getter
	private Sport sport;
	
	@ManyToOne @Setter @Getter
	private Stadium stadium;
	
	@NotNull @Getter @Setter
	//TODO: De  datum  moet  liggen  tussen  26  juli 2024 en 11 augustus 2024.
	private LocalDate date;

	@NotBlank @Getter @Setter
	@Pattern(regexp = "^([8-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid hour format. Use HH:mm")
	private String time;
	
	@Column(unique = true)
    @NotBlank(message = "Olympic number 1 is required") @Getter @Setter
    @Pattern(regexp = "^(?!0)(?!.*(.)\\1)[0-9]{5}$", message = "Invalid Olympic number format")
    private String olympicNumber1;
	
    @NotBlank(message = "Olympic number 2 is required") @Getter @Setter
    @Pattern(regexp = "^[0-9]+$", message = "Invalid Olympic number format")
	private String olympicNumber2;
	
    @Nullable @Getter @Setter
	private String discipline1;
    
    @Nullable @Getter @Setter
	private String discipline2;
    
    @NotNull(message = "Total tickets is required") @Getter @Setter
    @Range(min = 1, max = 50, message = "Total tickets must be between 1 and 50")
    private int totalTickets;
    
    @Getter @Setter
    private double price;
    
    @NotNull(message = "Remaining tickets is required") @Getter @Setter
    @Range(min = 0, max = 49, message = "Ticket left must be between 1 and 49")
    private int ticketLeft;

    @OneToMany(mappedBy = "competition")
    private List<Ticket> tickets = new ArrayList<>();

    
    public Competition (LocalDate date, String time, Stadium stadium, String olympicNumber1, String olympicNumber2, 
    		String discipline1, String discipline2, int totalTickets, double price, int ticketLeft) {
    	setDate(date);
    	setDiscipline1(discipline1);
    	setDiscipline2(discipline2);
    	setOlympicNumber1(olympicNumber1);
    	setOlympicNumber2(olympicNumber2);
    	setStadium(stadium);
    	setTime(time);
    	setPrice(price);
    	setTotalTickets(totalTickets);
    	setTicketLeft(ticketLeft);
    	addTickets(price, totalTickets);
    }
    
    private void addTickets(double price, int totalTickets) {
    	for(int i = 0; i <= totalTickets; i++) {
    		Ticket ticket = new Ticket(price);
    		tickets.add(ticket);
    	}
    }
}
